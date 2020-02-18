package com.example.rickandmorty.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.ViewModel.Character.CharacterInfoViewModel;
import com.example.rickandmorty.ViewModel.Episode.EpisodeInfoViewModel;
import com.example.rickandmorty.ViewModel.Location.LocationInfoViewModel;

public class InfoViewModelFactory<K> implements ViewModelProvider.Factory {

    private K mExtra;

    public InfoViewModelFactory(K extra) {
        mExtra = extra;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterInfoViewModel.class)) {
            //noinspection unchecked
            return (T) new CharacterInfoViewModel((TheCharacter) mExtra);
        } else if (modelClass.isAssignableFrom(LocationInfoViewModel.class)) {
            //noinspection unchecked
            return (T) new LocationInfoViewModel((Location) mExtra);
        } else if (modelClass.isAssignableFrom(EpisodeInfoViewModel.class)) {
            //noinspection unchecked
            return (T) new EpisodeInfoViewModel((Episode) mExtra);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
