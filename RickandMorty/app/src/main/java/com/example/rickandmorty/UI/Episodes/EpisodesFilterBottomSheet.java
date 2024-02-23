package com.example.rickandmorty.UI.Episodes;

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
import com.example.rickandmorty.ViewModel.Episode.ListEpisodesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Hashtable;

public class EpisodesFilterBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText name;
    private EditText episode;
    private Button search;

    public EpisodesFilterBottomSheet() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.episode_bottom_sheet, container, false);
        findViews(v);
        search.setOnClickListener(this);
        return v;
    }

    private void findViews(View v) {
        name = v.findViewById(R.id.name);
        episode = v.findViewById(R.id.episode);
        search = v.findViewById(R.id.search);
    }

    @Override
    public void onClick(View v) {
        ListEpisodesViewModel model = new ViewModelProvider(requireActivity()).get(ListEpisodesViewModel.class);
        String name = this.name.getText().toString();
        String episode = this.episode.getText().toString();
        model.setQuery(new Hashtable<String, String>() {{
            put("name", name);
            put("episode", episode);
        }});
        dismiss();
    }
}
