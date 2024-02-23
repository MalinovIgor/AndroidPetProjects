package com.example.rickandmorty.UI.Locations;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;
import com.example.rickandmorty.UI.Characters.CharacterInfoFragment;
import com.example.rickandmorty.UI.LocationOrEpisodeResidentsAdapter;
import com.example.rickandmorty.UI.MainActivity;
import com.example.rickandmorty.ViewModel.Location.LocationInfoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationInfoFragment extends Fragment {
    private Location location;

    public LocationInfoFragment() {
        // Required empty public constructor
    }

    public static LocationInfoFragment getInstance(Location location) {
        LocationInfoFragment fragment = new LocationInfoFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putParcelable("location", location);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location_info, container, false);
        if (getArguments() != null) {
            location = getArguments().getParcelable("location");
        }
        LocationInfoViewModel viewModel = new ViewModelProvider(requireActivity()).get(LocationInfoViewModel.class);
        viewModel.setLocation(location);
        RecyclerView recyclerView = v.findViewById(R.id.residents_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocationOrEpisodeResidentsAdapter adapter = new LocationOrEpisodeResidentsAdapter(viewModel.getResidents().getValue(), character -> {
            ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
            CharacterInfoFragment fragment =
                    CharacterInfoFragment.getInstance(character);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);
        viewModel.getResidents().observe(getViewLifecycleOwner(), residents ->{
            adapter.setResidents(residents);
            adapter.notifyDataSetChanged();
        });
        setupView(v);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(location.getName());
        return v;
    }

    private void setupView(View v) {

        ((TextView) v.findViewById(R.id.type)).setText(location.getType());
        ((TextView) v.findViewById(R.id.dimension)).setText(location.getDimension());


    }

    @Override
    public void onStop() {
        super.onStop();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).show();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(location.getName());
    }

}
