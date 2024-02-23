package com.example.rickandmorty.Data.Network.Character;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Character.CharacterResult;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.NetworkStates;
import com.example.rickandmorty.Data.Network.ResponseResult;

import java.util.ArrayList;
import java.util.Dictionary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CharactersDataSource extends PageKeyedDataSource<Integer, TheCharacter> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;
    public MutableLiveData<NetworkStates> state;

    public CharactersDataSource(Dictionary<String, String> query, MutableLiveData<Integer> count, MutableLiveData<NetworkStates> state) {
        this.query = query;
        this.count = count;
        this.state = state;
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
                            count.setValue(response.body().getInfo().getCount());
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                            state.setValue(NetworkStates.LOADED);

                        } else if(response.raw().code() == 404){
                            state.setValue(NetworkStates.EMPTY_RESPONSE);
                        } else if(response.raw().code() == 503){
                            state.setValue(NetworkStates.SERVER_ERROR);
                        }
                        Log.d("RESPONSE_CODE", String.format("onResponse: %d", response.raw().code()));
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<TheCharacter>> call, Throwable t) {
                        Log.e("FAILURE", String.format("onFailure: %s", t.getMessage()));
                        Log.e("FAILURE", String.format("onFailure: %s", t.getStackTrace()));
                        state.setValue(NetworkStates.CONNECTION_ERROR);
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
