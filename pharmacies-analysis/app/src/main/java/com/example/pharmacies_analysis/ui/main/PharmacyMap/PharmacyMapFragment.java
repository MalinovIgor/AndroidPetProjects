package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.network.models.Location;
import com.example.pharmacies_analysis.data.network.models.PharmaciesResponse;
import com.example.pharmacies_analysis.data.network.models.Pharmacy;
import com.example.pharmacies_analysis.databinding.PharmacyMapFragmentBinding;
import com.example.pharmacies_analysis.ui.ViewModelFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static android.content.Context.LOCATION_SERVICE;

@RuntimePermissions
public class PharmacyMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private PharmacyMapViewModel mViewModel;
    PharmaciesResponse pharmaciesResponse;
    private GoogleMap mMap;
    private PharmacyMapFragmentBinding pharmacyMapFragmentBinding;
    private Location location;
    private List<Medicine> medicines;
    private Integer distance;
    SupportMapFragment mapFragment;

    public static PharmacyMapFragment newInstance() {
        return new PharmacyMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pharmacyMapFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.pharmacy_map_fragment, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        pharmacyMapFragmentBinding.settingsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapSettingsBottomSheet mapSettingsBottomSheet = new MapSettingsBottomSheet();
                mapSettingsBottomSheet.show(getChildFragmentManager(), mapSettingsBottomSheet.getTag());
            }
        });
        ViewModelFactory viewModelFactory = new ViewModelFactory(getContext());
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(PharmacyMapViewModel.class);
        PharmacyMapFragmentPermissionsDispatcher.getLocationWithPermissionCheck(this);


        return pharmacyMapFragmentBinding.getRoot();
    }

    private void handleResult(PharmaciesResponse pharmaciesResponse) {
        this.pharmaciesResponse = pharmaciesResponse;
        if (pharmaciesResponse.getPharmacies().size() == 0){
            Toast.makeText(getContext(), R.string.nothing_to_show, Toast.LENGTH_SHORT).show();
        }
        mapFragment.getMapAsync(this);
    }

    private void handleError(Throwable throwable) {
        pharmacyMapFragmentBinding.map.setVisibility(View.GONE);
        pharmacyMapFragmentBinding.error.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        pharmacyMapFragmentBinding.loading.setVisibility(View.GONE);
    }

    private void showLoading(Disposable disposable) {
        pharmacyMapFragmentBinding.loading.setVisibility(View.VISIBLE);
    }

    private void sendRequest(List<Medicine> medicines) {
        this.medicines = medicines;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        distance = Integer.parseInt(sharedPreferences.getString("RADIUS", "1000"));
        mViewModel.getPharmacies(location, distance, medicines)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::showLoading)
                .doOnTerminate(this::hideLoading)
                .subscribe(this::handleResult, this::handleError);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (Pharmacy pharmacy :
                pharmaciesResponse.getPharmacies()) {
            LatLng sydney = new LatLng(pharmacy.getCoordinates().getLat(), pharmacy.getCoordinates().getLon());
            Marker marker = googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title(pharmacy.getName()));
            marker.setTag(pharmacy);
        }
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        PharmacyBottomSheet pharmacyBottomSheet = PharmacyBottomSheet.newInstance((Pharmacy) marker.getTag());
        pharmacyBottomSheet.show(getChildFragmentManager(), pharmacyBottomSheet.getTag());
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PharmacyMapFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void getLocation() {
        LocationManager locMan = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        android.location.Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        location = new Location(loc.getLatitude(), loc.getLongitude());
        mViewModel.getAllMedicines()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendRequest);
        pharmacyMapFragmentBinding.settingsFab.show();
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    public void getDefaultLocation() {
        pharmacyMapFragmentBinding.settingsFab.hide();
        location = new Location(0.0, 0.0);
        mViewModel.getAllMedicines()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendRequest);
    }
}
