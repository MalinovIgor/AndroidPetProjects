package com.example.rickandmorty.ViewModel.Episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.LocationOrEpisodeResidents;

import java.util.List;

public class EpisodeInfoViewModel extends ViewModel {

    private LiveData<List<TheCharacter>> residents = new MutableLiveData<>();
    private MutableLiveData<Episode> location = new MutableLiveData<>();

    public EpisodeInfoViewModel() {
        residents = Transformations.switchMap(this.location, input -> new LocationOrEpisodeResidents<>(input).getResidents());
    }

    public LiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
    public void setEpisode(Episode episode){
        this.location.setValue(episode);
    }
}
