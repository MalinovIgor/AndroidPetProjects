package com.example.rickandmorty.UI.Locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rickandmorty.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LocationsFilterBottomSheet extends BottomSheetDialogFragment {
    public LocationsFilterBottomSheet() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.location_bottom_sheet, container, false);
    }
}
