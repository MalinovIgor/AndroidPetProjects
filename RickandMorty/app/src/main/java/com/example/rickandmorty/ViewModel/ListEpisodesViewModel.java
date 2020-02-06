package com.example.rickandmorty.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSource;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSourceFactory;

public class ListEpisodesViewModel extends ViewModel {

    public LiveData<PagedList<Episode>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Episode>> liveDataSource;

    public ListEpisodesViewModel() {

        EpisodesDataSourceFactory itemDataSourceFactory = new EpisodesDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getEpisodeLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(EpisodesDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
