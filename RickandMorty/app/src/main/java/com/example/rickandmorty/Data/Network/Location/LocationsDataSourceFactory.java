package com.example.rickandmorty.Data.Network.Location;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSource;

public class LocationsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Location>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        LocationsDataSource itemDataSource = new LocationsDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Location>> getLocationLiveDataSource() {
        return itemLiveDataSource;
    }
}
