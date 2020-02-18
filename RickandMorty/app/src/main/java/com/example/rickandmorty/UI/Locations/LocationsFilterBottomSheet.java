package com.example.rickandmorty.UI.Locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.Location.ListLocationsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Hashtable;

public class LocationsFilterBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText name;
    private EditText type;
    private EditText dimension;
    private Button search;

    public LocationsFilterBottomSheet() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.location_bottom_sheet, container, false);
        findViews(v);
        search.setOnClickListener(this);
        return v;
    }

    private void findViews(View v) {
        name = v.findViewById(R.id.name);
        type = v.findViewById(R.id.type);
        dimension = v.findViewById(R.id.dimension);
        search = v.findViewById(R.id.search);
    }

    @Override
    public void onClick(View v) {
        ListLocationsViewModel model = new ViewModelProvider(requireActivity()).get(ListLocationsViewModel.class);
        String name = this.name.getText().toString();
        String type = this.type.getText().toString();
        String dimension = this.dimension.getText().toString();
        model.query.setValue(new Hashtable<String, String>() {{
            put("name", name);
            put("type", type);
            put("dimension", dimension);
        }});
        dismiss();
    }
}
