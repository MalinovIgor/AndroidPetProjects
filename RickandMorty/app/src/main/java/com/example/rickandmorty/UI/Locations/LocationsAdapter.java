package com.example.rickandmorty.UI.Locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;

//checked
public class LocationsAdapter extends PagedListAdapter<Location, LocationsViewHolder> {


    public LocationsAdapter() {
        super(Location.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.location_item, parent, false);
        return new LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewHolder holder, int position) {
        Location location = getItem(position);
        ((LocationsViewHolder) holder).bindItem(location.getName(), location.getType(), location.getDimension());
    }
}
