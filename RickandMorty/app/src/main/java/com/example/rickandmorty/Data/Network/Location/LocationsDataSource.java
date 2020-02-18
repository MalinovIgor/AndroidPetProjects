package com.example.rickandmorty.Data.Network.Location;


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

public class LocationsDataSource extends PageKeyedDataSource<Integer, Location> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;

    public  LocationsDataSource(Dictionary<String, String> query, MutableLiveData<Integer> count){
        this.query = query;
        this.count = count;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Location> callback) {

        ApiClient.getInstance()
                .getApi()
                .getLocations(FIRST_PAGE, query.get("name"), query.get("species"),
                        query.get("dimension"))
                .enqueue(new Callback<ResponseResult<Location>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<Location>> call, Response<ResponseResult<Location>> response) {
                        if (response.body() != null) {
                            count.setValue(response.body().getInfo().getCount());
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<Location>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Location> callback) {


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Location> callback) {

        ApiClient.getInstance()
                .getApi()
                .getLocations(params.key, query.get("name"), query.get("type"),
                        query.get("dimension"))
                .enqueue(new Callback<ResponseResult<Location>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<ResponseResult<Location>> call, Response<ResponseResult<Location>> response) {

                        if (response.body() != null) {
                            Integer key = params.key + 1;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<ResponseResult<Location>> call, Throwable t) {

                    }
                });
    }
}
