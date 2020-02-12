package com.example.rickandmorty.ViewModel;

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

import java.util.Dictionary;
import java.util.Hashtable;

//checked
public class ListCharactersViewModel extends ViewModel {

    public LiveData<PagedList<TheCharacter>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, TheCharacter>> liveDataSource;

    public final MutableLiveData<Dictionary<String, String>> query =
            new MutableLiveData<>();


    public ListCharactersViewModel() {
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(CharactersDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = Transformations.switchMap(query, (Function<Dictionary<String, String>, LiveData<PagedList<TheCharacter>>>) input -> {
            CharactersDataSourceFactory itemDataSourceFactory = new CharactersDataSourceFactory(input);
            liveDataSource = itemDataSourceFactory.getCharacterLiveDataSource();

            return new LivePagedListBuilder(itemDataSourceFactory, config).build();
        });

    }

//    public void search(Dictionary<String, String> query) {
//        PagedList.Config config =
//                (new PagedList.Config.Builder())
//                        .setEnablePlaceholders(false)
//                        .setPageSize(CharactersDataSource.PAGE_SIZE)
//                        .build();
////        this.query.setValue(new Hashtable<String, String>(){{
////            put("name", query.get("name"));
////            put("species", query.get("species"));
////            put("gender", query.get("gender"));
////            put("status", query.get("status"));
////        }});
//        CharactersDataSourceFactory itemDataSourceFactory = new CharactersDataSourceFactory(query);
//        liveDataSource = itemDataSourceFactory.getCharacterLiveDataSource();
//
//        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory, config).build();
//    }

}
