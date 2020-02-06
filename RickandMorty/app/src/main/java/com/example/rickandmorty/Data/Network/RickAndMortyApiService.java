package com.example.rickandmorty.Data.Network;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickAndMortyApiService {
    @GET("character")
    Call<ResponseResult<TheCharacter>> getCharacters(@Query("page") int page);

    @GET("location")
    Call<ResponseResult<Location>> getLocations(@Query("page") int page);

    @GET("episode")
    Call<ResponseResult<Episode>> getEpisodes(@Query("page") int page);
//
//    @GET("character/{id}")
//    Call<TheCharacter> getCharacter(@Path("id") int id);
//
//    @GET("location/{id}")
//    Call<Location> getLocation(@Path("id") int id);
//
//    @GET("episode/{id}")
//    Call<Episode> getEpisode(@Path("id") int id);
}
