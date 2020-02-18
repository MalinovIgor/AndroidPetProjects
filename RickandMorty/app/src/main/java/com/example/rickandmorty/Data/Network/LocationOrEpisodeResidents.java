package com.example.rickandmorty.Data.Network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LocationOrEpisodeResidents<T> {
    private T location;
    private MutableLiveData<List<TheCharacter>> residents;

    public LocationOrEpisodeResidents(T location, MutableLiveData<List<TheCharacter>> residents){
        this.location = location;
        this.residents = residents;
        loadResidents(location);
    }

    private void loadResidents(T location) {
        String residentsIds = null;
        if (location instanceof Location){
            residentsIds = ((Location) location).getResidentsIds();
        }
        else if (location instanceof Episode){
            residentsIds = ((Episode) location).getCharactersIds();
        }
        ApiClient.getInstance()
                .getApi()
                .getCharactersById(residentsIds)
                .enqueue(new Callback<List<TheCharacter>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<List<TheCharacter>> call, Response<List<TheCharacter>> response) {
                        if (response.body() != null) {
                            residents.setValue(response.body());
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<List<TheCharacter>> call, Throwable t) {
                        Log.e("LOAD RESIDENTS", "onFailure: ", t);
                    }
                });
    }

    public MutableLiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
}
