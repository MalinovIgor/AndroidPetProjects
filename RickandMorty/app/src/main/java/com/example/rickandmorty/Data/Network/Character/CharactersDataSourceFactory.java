package com.example.rickandmorty.Data.Network.Character;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;


public class CharactersDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        CharactersDataSource itemDataSource = new CharactersDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, TheCharacter>> getCharacterLiveDataSource() {
        return itemLiveDataSource;
    }
}
