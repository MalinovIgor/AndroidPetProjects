package com.example.rickandmorty.Data.Network.Location;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.CharactersList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LocationResidents {
    private Location location;
    private MutableLiveData<List<TheCharacter>> residents;

    public LocationResidents(Location location, MutableLiveData<List<TheCharacter>> residents){
        this.location = location;
        this.residents = residents;
        loadResidents(location);
    }

    private void loadResidents(Location location) {
        ApiClient.getInstance()
                .getApi()
                .getCharactersById(location.getResidentsIds())
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
                        System.out.println(t.getMessage());
                    }
                });
    }

    public MutableLiveData<List<TheCharacter>> getResidents() {
        return residents;
    }
}
