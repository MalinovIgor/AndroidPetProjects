package com.example.rickandmorty.Data.Network.Episode;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class EpisodesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Episode>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        EpisodesDataSource itemDataSource = new EpisodesDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Episode>> getEpisodeLiveDataSource() {
        return itemLiveDataSource;
    }
}
