package com.example.rickandmorty.ViewModel.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.Data.Network.LocationOrEpisodeResidents;

import java.util.List;

public class LocationInfoViewModel extends ViewModel {
    private LiveData<List<TheCharacter>> residents = new MutableLiveData<>();
    private MutableLiveData<Location> location = new MutableLiveData<>();

    public LocationInfoViewModel() {
        residents = Transformations.switchMap(this.location, input -> new LocationOrEpisodeResidents<>(input).getResidents());
    }

    public LiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
    public void setLocation(Location location){
        this.location.setValue(location);
    }
}
