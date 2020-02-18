package com.example.rickandmorty.ViewModel.Episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.LocationOrEpisodeResidents;

import java.util.List;

public class EpisodeInfoViewModel extends ViewModel {

    private MutableLiveData<List<TheCharacter>> residents = new MutableLiveData<>();

    public EpisodeInfoViewModel(Episode episode) {
        new LocationOrEpisodeResidents<>(episode, residents);
    }

    public LiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
}
