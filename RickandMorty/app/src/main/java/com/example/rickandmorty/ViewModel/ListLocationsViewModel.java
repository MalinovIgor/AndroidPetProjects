package com.example.rickandmorty.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.Data.Network.Location.LocationsDataSource;
import com.example.rickandmorty.Data.Network.Location.LocationsDataSourceFactory;

public class ListLocationsViewModel extends ViewModel {

    public LiveData<PagedList<Location>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Location>> liveDataSource;

    public ListLocationsViewModel() {

        LocationsDataSourceFactory itemDataSourceFactory = new LocationsDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getLocationLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(LocationsDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
