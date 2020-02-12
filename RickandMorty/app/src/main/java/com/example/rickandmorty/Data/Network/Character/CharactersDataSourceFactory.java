package com.example.rickandmorty.Data.Network.Character;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import java.util.Dictionary;


public class CharactersDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> itemLiveDataSource = new MutableLiveData<>();
    private Dictionary<String, String> query;

    public CharactersDataSourceFactory(Dictionary<String, String> query){
        this.query = query;
    }


    @Override
    public DataSource create() {
        CharactersDataSource itemDataSource = new CharactersDataSource(query);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> getCharacterLiveDataSource() {
        return itemLiveDataSource;
    }
}
