package com.example.rickandmorty.UI.Characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.R;

import java.util.List;

public class CharacterEpisodesAdapter extends RecyclerView.Adapter<CharacterEpisodesAdapter.EpisodeViewHolder> {

    private List<Episode> episodes;
    private final OnItemClickListener clickListener;


    public CharacterEpisodesAdapter(List<Episode> episodes, @Nullable OnItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.location_or_episode_resident_item, parent, false);
        return new EpisodeViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = episodes.get(position);
        ((EpisodeViewHolder) holder).bindItem(episode.getName());
    }

    @Override
    public int getItemCount() {
        return episodes == null ? 0 : episodes.size();
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView mResident;

        EpisodeViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            mResident = itemView.findViewById(R.id.resident);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(episodes.get(position));
                }
            });
        }

        void bindItem(String resident) {
            mResident.setText(resident);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull Episode episode);
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
