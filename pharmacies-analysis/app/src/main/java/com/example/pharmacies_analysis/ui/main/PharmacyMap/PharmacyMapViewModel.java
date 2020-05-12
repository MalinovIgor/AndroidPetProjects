package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.data.network.models.Location;
import com.example.pharmacies_analysis.data.network.models.PharmaciesResponse;
import com.example.pharmacies_analysis.data.repositories.RepositoryImpl;
import com.example.pharmacies_analysis.data.sharedPreference.SharedPreferenceIntegerLiveData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PharmacyMapViewModel extends ViewModel {
    private RepositoryImpl repository;

    public PharmacyMapViewModel(@NonNull Context context) {
        repository = RepositoryImpl.getRepository(context);

    }

    public Observable<PharmaciesResponse> getPharmacies(Location location, int distance, List<Medicine> medicines){
        return repository.getPharmacies(location, distance, medicines);
    }

    public Flowable<List<Medicine>> getAllMedicines(){
        return repository.getAll();
    }

    public SharedPreferenceIntegerLiveData getRadius(){
        return repository.getRadius();
    }

    public void saveRadius(int radius){
        repository.saveRadius(radius);
    }

}