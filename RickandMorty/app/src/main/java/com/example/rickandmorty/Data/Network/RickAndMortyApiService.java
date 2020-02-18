package com.example.rickandmorty.Data.Network;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RickAndMortyApiService {
    @GET("character")
    Call<ResponseResult<TheCharacter>> getCharacters(@Query("page") int page, @Query("name") String name,
                                                     @Query("species") String species, @Query("gender") String gender,
                                                     @Query("status") String status);

    @GET("location")
    Call<ResponseResult<Location>> getLocations(@Query("page") int page, @Query("name") String name,
                                                @Query("type") String type,
                                                @Query("dimension") String dimension);

    @GET("episode")
    Call<ResponseResult<Episode>> getEpisodes(@Query("page") int page, @Query("name") String name,
                                              @Query("episode") String episode);
    @GET("character/{ids}")
    Call<List<TheCharacter>> getCharactersById(@Path("ids") String ids);
}
