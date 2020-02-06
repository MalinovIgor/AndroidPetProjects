package com.example.rickandmorty.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Character.CharactersDataSource;
import com.example.rickandmorty.Data.Network.Character.CharactersDataSourceFactory;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
//checked
public class ListCharactersViewModel extends ViewModel {

    public LiveData<PagedList<TheCharacter>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, TheCharacter>> liveDataSource;

    public ListCharactersViewModel() {

        CharactersDataSourceFactory itemDataSourceFactory = new CharactersDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getCharacterLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(CharactersDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
