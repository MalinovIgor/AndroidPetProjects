package com.example.rickandmorty.UI.Episodes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;

//checked
public class EpisodesViewHolder extends RecyclerView.ViewHolder {

    private static final int LAYOUT = R.layout.location_item;


    private TextView mName;
    private TextView mEpisode;
    private TextView mDate;



    public EpisodesViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
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
