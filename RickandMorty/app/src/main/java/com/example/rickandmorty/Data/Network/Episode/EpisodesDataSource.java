package com.example.rickandmorty.Data.Network.Episode;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.ResponseResult;

import java.util.Dictionary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class EpisodesDataSource extends PageKeyedDataSource<Integer, Episode> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;

    public  EpisodesDataSource(Dictionary<String, String> query, MutableLiveData<Integer> count){
        this.query = query;
        this.count = count;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Episode> callback) {

        ApiClient.getInstance()
                .getApi()
                .getEpisodes(FIRST_PAGE, query.get("name"), query.get("episode"))
                .enqueue(new Callback<ResponseResult<Episode>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<Episode>> call, Response<ResponseResult<Episode>> response) {
                        if (response.body() != null) {
                            count.setValue(response.body().getInfo().getCount());
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<Episode>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Episode> callback) {


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Episode> callback) {

        ApiClient.getInstance()
                .getApi()
                .getEpisodes(params.key, query.get("name"), query.get("episode"))
                .enqueue(new Callback<ResponseResult<Episode>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<Episode>> call, Response<ResponseResult<Episode>> response) {

                        if (response.body() != null) {
                            Integer key = params.key + 1;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<Episode>> call, Throwable t) {

                    }
                });


    }
}
