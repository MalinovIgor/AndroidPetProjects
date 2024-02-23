package com.example.rickandmorty.UI.Locations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;
import com.example.rickandmorty.UI.Characters.CharactersAdapter;

//checked
public class LocationsAdapter extends PagedListAdapter<Location, LocationsAdapter.LocationsViewHolder> {
    private final OnItemClickListener clickListener;


    public LocationsAdapter(@Nullable OnItemClickListener clickListener) {
        super(Location.DIFF_CALLBACK);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.location_item, parent, false);
        return new LocationsViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewHolder holder, int position) {
        Location location = getItem(position);
        ((LocationsViewHolder) holder).bindItem(location.getName(), location.getType(), location.getDimension());
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull Location location);
    }

    class LocationsViewHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.location_item;


        private TextView mName;
        private TextView mType;
        private TextView mDimension;



        public LocationsViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
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
            mDimension = itemView.findViewById(R.id.dimension);
            mType = itemView.findViewById(R.id.type);
        }

        public void bindItem(String name, String type, String dimension){
            mName.setText(name);
            mType.setText(type);
            mDimension.setText(dimension);
        }
    }
}
