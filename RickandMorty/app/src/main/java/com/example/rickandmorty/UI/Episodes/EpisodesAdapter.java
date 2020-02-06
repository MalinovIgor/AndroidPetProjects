package com.example.rickandmorty.UI.Episodes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;

//checked
public class EpisodesAdapter extends PagedListAdapter<Episode, EpisodesViewHolder> {


    public EpisodesAdapter() {
        super(Episode.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.episode_item, parent, false);
        return new EpisodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        Episode episode = getItem(position);
        ((EpisodesViewHolder) holder).bindItem(episode.getName(), episode.getEpisode(), episode.getAirDate());
    }
}
