package com.example.rickandmorty.UI.Locations;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;

//checked
public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private static final int LAYOUT = R.layout.location_item;


    private TextView mName;
    private TextView mType;
    private TextView mDimension;



    public LocationsViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
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
