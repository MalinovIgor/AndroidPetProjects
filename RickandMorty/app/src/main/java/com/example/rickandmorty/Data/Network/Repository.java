package com.example.rickandmorty.Data.Network;

import androidx.lifecycle.LiveData;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;

//don't use
public interface Repository {
    LiveData<ApiResponse<TheCharacter>> getCharacters(int page);
//    LiveData<ApiResponse<Location>> getLocations();
//    LiveData<ApiResponse<Episode>> getLocations();
//    LiveData<ApiResponse<TheCharacter>> getCharacter(int id);
//    LiveData<ApiResponse<Location>> getLocation(int id);
//    LiveData<ApiResponse<Episode>> getLocations(int d);
}
