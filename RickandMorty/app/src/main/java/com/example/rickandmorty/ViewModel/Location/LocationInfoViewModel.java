package com.example.rickandmorty.ViewModel.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.Data.Network.LocationOrEpisodeResidents;

import java.util.List;

public class LocationInfoViewModel extends ViewModel {
    private MutableLiveData<List<TheCharacter>> residents = new MutableLiveData<>();

    public LocationInfoViewModel(Location location) {
        new LocationOrEpisodeResidents<>(location, residents);
    }

    public LiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
}
