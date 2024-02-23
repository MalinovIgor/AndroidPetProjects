package com.example.rickandmorty.Data.Network.Location;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSource;

import java.util.Dictionary;

public class LocationsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Location>> itemLiveDataSource = new MutableLiveData<>();
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;

    public LocationsDataSourceFactory(Dictionary<String, String> query, MutableLiveData<Integer> count){
        this.query = query;
        this.count = count;
    }


    @Override
    public DataSource create() {
        LocationsDataSource itemDataSource = new LocationsDataSource(query, count);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Location>> getLocationLiveDataSource() {
        return itemLiveDataSource;
    }
}
