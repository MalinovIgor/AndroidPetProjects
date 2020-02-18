package com.example.rickandmorty.ViewModel.Episode;

import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Episode.Episode;

public class EpisodeInfoViewModel extends ViewModel {

    private Episode episode;

    public EpisodeInfoViewModel(Episode episode) {
        this.episode = episode;
    }
}
