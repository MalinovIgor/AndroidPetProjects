package com.example.rickandmorty.Data.Network.Character;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Episode.Episode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CharacterEpisodes {
    private MutableLiveData<List<Episode>> episodes = new MutableLiveData<>();
    public CharacterEpisodes(TheCharacter character){
        loadResidents(character);
    }

    private void loadResidents(TheCharacter character) {
        String residentsIds = character.getEpisodesIds();

        ApiClient.getInstance()
                .getApi()
                .getEpisodesById(residentsIds)
                .enqueue(new Callback<List<Episode>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
                        if (response.body() != null) {
                            episodes.setValue(response.body());
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<List<Episode>> call, Throwable t) {
                        Log.e("LOAD RESIDENTS", "onFailure: ", t);
                    }
                });
    }

    public LiveData<List<Episode>> getEpisodes() {
        return episodes;
    }
}
