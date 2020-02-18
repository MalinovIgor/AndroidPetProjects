package com.example.rickandmorty.Data.Network.Character;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.rickandmorty.Data.Network.NetworkStates;

import java.util.Dictionary;


public class CharactersDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> itemLiveDataSource = new MutableLiveData<>();
    private Dictionary<String, String> query;
    public MutableLiveData<Integer> count;
    public MutableLiveData<NetworkStates> state;

    public CharactersDataSourceFactory(Dictionary<String, String> query, MutableLiveData<Integer> count, MutableLiveData<NetworkStates> state){
        this.query = query;
        this.count = count;
        this.state = state;
    }


    @Override
    public DataSource create() {
        CharactersDataSource itemDataSource = new CharactersDataSource(query, count, state);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> getCharacterLiveDataSource() {
        return itemLiveDataSource;
    }

}
