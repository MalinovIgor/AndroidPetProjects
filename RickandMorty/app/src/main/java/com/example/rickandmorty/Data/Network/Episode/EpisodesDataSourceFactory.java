package com.example.rickandmorty.Data.Network.Episode;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import java.util.Dictionary;

public class EpisodesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Episode>> itemLiveDataSource = new MutableLiveData<>();
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;

    public EpisodesDataSourceFactory(Dictionary<String, String> query, MutableLiveData<Integer> count){
        this.query = query;
        this.count = count;
    }


    @Override
    public DataSource create() {
        EpisodesDataSource itemDataSource = new EpisodesDataSource(query, count);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Episode>> getEpisodeLiveDataSource() {
        return itemLiveDataSource;
    }
}
