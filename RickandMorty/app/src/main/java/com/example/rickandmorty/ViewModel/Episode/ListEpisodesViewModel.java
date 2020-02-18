package com.example.rickandmorty.ViewModel.Episode;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSource;
import com.example.rickandmorty.Data.Network.Episode.EpisodesDataSourceFactory;

import java.util.Dictionary;

public class ListEpisodesViewModel extends ViewModel {

    public LiveData<PagedList<Episode>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Episode>> liveDataSource;

    public final MutableLiveData<Dictionary<String, String>> query =
            new MutableLiveData<>();
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public ListEpisodesViewModel() {

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(EpisodesDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = Transformations.switchMap(query, (Function<Dictionary<String, String>, LiveData<PagedList<Episode>>>) input -> {
            EpisodesDataSourceFactory itemDataSourceFactory = new EpisodesDataSourceFactory(input, count);
            liveDataSource = itemDataSourceFactory.getEpisodeLiveDataSource();
            return new LivePagedListBuilder(itemDataSourceFactory, config).build();
        });

    }
}
