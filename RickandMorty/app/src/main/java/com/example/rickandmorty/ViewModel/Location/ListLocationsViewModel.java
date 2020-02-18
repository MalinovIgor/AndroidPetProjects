package com.example.rickandmorty.ViewModel.Location;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.Data.Network.Location.LocationsDataSource;
import com.example.rickandmorty.Data.Network.Location.LocationsDataSourceFactory;

import java.util.Dictionary;

public class ListLocationsViewModel extends ViewModel {

    public LiveData<PagedList<Location>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Location>> liveDataSource;
    public final MutableLiveData<Dictionary<String, String>> query =
            new MutableLiveData<>();
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public ListLocationsViewModel() {

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(LocationsDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = Transformations.switchMap(query, (Function<Dictionary<String, String>, LiveData<PagedList<Location>>>) input -> {
            LocationsDataSourceFactory itemDataSourceFactory = new LocationsDataSourceFactory(input, count);
            liveDataSource = itemDataSourceFactory.getLocationLiveDataSource();
            return new LivePagedListBuilder(itemDataSourceFactory, config).build();
        });

    }
}
