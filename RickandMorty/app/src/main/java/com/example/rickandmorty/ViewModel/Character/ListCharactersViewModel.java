package com.example.rickandmorty.ViewModel.Character;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Character.CharactersDataSource;
import com.example.rickandmorty.Data.Network.Character.CharactersDataSourceFactory;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.NetworkStates;

import java.util.Dictionary;
import java.util.Hashtable;

//checked
public class ListCharactersViewModel extends ViewModel {

    public LiveData<PagedList<TheCharacter>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, TheCharacter>> liveDataSource;
    private final MutableLiveData<Dictionary<String, String>> query =
            new MutableLiveData<>();
    private MutableLiveData<Integer> count = new MutableLiveData<>();
    private MutableLiveData<NetworkStates> state = new MutableLiveData<>();


    public ListCharactersViewModel() {
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(CharactersDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = Transformations.switchMap(query, (Function<Dictionary<String, String>, LiveData<PagedList<TheCharacter>>>) input -> {
            state.setValue(NetworkStates.LOADING);
            CharactersDataSourceFactory itemDataSourceFactory = new CharactersDataSourceFactory(input, count, state);
            liveDataSource = itemDataSourceFactory.getCharacterLiveDataSource();
            return new LivePagedListBuilder(itemDataSourceFactory, config).build();
        });

    }

    public void loadAgain() {
        Dictionary<String, String> temp = query.getValue();
        query.setValue(temp);
    }

    public void successLoaded() {
        state.setValue(NetworkStates.LOADED);
    }

    public void init() {
        query.setValue(new Hashtable<String, String>() {{
            put("name", "");
            put("species", "");
            put("gender", "");
            put("status", "");
        }});
    }

    public LiveData<Dictionary<String, String>> getQuery() {
        return query;
    }

    public LiveData<Integer> getCount() {
        return count;
    }

    public LiveData<NetworkStates> getState() {
        return state;
    }

    public void setQuery(Dictionary<String, String> query){
        this.query.setValue(query);
    }

}