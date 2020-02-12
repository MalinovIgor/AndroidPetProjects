package com.example.rickandmorty.Data.Network.Character;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Character.CharacterResult;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.ResponseResult;

import java.util.Dictionary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CharactersDataSource extends PageKeyedDataSource<Integer, TheCharacter> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private Dictionary<String, String> query;

    public  CharactersDataSource(Dictionary<String, String> query){
        this.query = query;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, TheCharacter> callback) {

        ApiClient.getInstance()
                .getApi()
                .getCharacters(FIRST_PAGE, query.get("name"), query.get("species"),
                        query.get("gender"), query.get("status"))
                .enqueue(new Callback<ResponseResult<TheCharacter>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<TheCharacter>> call, Response<ResponseResult<TheCharacter>> response) {
                        if (response.body() != null) {

                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<TheCharacter>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TheCharacter> callback) {


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TheCharacter> callback) {

        ApiClient.getInstance()
                .getApi()
                .getCharacters(params.key, query.get("name"), query.get("species"),
                        query.get("gender"), query.get("status"))
                .enqueue(new Callback<ResponseResult<TheCharacter>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<TheCharacter>> call, Response<ResponseResult<TheCharacter>> response) {

                        if (response.body() != null) {
                            Integer key = params.key + 1;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<TheCharacter>> call, Throwable t) {

                    }
                });


    }
}
