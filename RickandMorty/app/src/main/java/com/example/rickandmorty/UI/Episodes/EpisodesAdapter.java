package com.example.rickandmorty.UI.Episodes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;

//checked
public class EpisodesAdapter extends PagedListAdapter<Episode, EpisodesAdapter.EpisodesViewHolder> {
    private final OnItemClickListener clickListener;


    public EpisodesAdapter(@Nullable OnItemClickListener clickListener) {
        super(Episode.DIFF_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.episode_item, parent, false);
        return new EpisodesViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        Episode episode = getItem(position);
        ((EpisodesViewHolder) holder).bindItem(episode.getName(), episode.getEpisode(), episode.getAirDate());
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull Episode episode);
    }

    class EpisodesViewHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.location_item;


        private TextView mName;
        private TextView mEpisode;
        private TextView mDate;



        public EpisodesViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            findViews(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        private void findViews(@NonNull View itemView){
            mName = itemView.findViewById(R.id.name);
            mDate = itemView.findViewById(R.id.date);
            mEpisode = itemView.findViewById(R.id.episode);
        }

        public void bindItem(String name, String episode, String date){
            mName.setText(name);
            mEpisode.setText(episode);
            mDate.setText(date);
        }
    }
}
