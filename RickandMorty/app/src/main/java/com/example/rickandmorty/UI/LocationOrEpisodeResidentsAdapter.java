package com.example.rickandmorty.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.R;

import java.util.List;

public class LocationOrEpisodeResidentsAdapter extends RecyclerView.Adapter<LocationOrEpisodeResidentsAdapter.ResidentViewHolder> {

    private List<TheCharacter> residents;
    private final OnItemClickListener clickListener;


    public LocationOrEpisodeResidentsAdapter(List<TheCharacter> residents, @Nullable OnItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.residents = residents;
    }

    @NonNull
    @Override
    public ResidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.location_or_episode_resident_item, parent, false);
        return new ResidentViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentViewHolder holder, int position) {
        TheCharacter character = residents.get(position);
        ((ResidentViewHolder) holder).bindItem(character.getName());
    }

    @Override
    public int getItemCount() {
        return residents == null ? 0 : residents.size();
    }

    class ResidentViewHolder extends RecyclerView.ViewHolder {
        TextView mResident;

        ResidentViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            mResident = itemView.findViewById(R.id.resident);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(residents.get(position));
                }
            });
        }

        void bindItem(String resident) {
            mResident.setText(resident);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull TheCharacter character);
    }

    public void setResidents(List<TheCharacter> residents) {
        this.residents = residents;
    }
}
