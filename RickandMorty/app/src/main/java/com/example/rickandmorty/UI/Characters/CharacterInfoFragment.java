package com.example.rickandmorty.UI.Characters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Location.Location;
import com.example.rickandmorty.R;
import com.example.rickandmorty.UI.Episodes.EpisodeInfoFragment;
import com.example.rickandmorty.UI.Locations.LocationInfoFragment;
import com.example.rickandmorty.UI.MainActivity;
import com.example.rickandmorty.ViewModel.Character.CharacterInfoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterInfoFragment extends Fragment implements View.OnClickListener {
    private TheCharacter character;
    private HashMap<String, Location> locations;
    private TextView mLocation;
    private TextView mOrigin;
    private ActionBar mActionBar;

    public static CharacterInfoFragment getInstance(TheCharacter character) {
        CharacterInfoFragment fragment = new CharacterInfoFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putParcelable("character", character);
        fragment.setArguments(arguments);
        return fragment;
    }

    public CharacterInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_character_info, container, false);
        if (getArguments() != null) {
            character = getArguments().getParcelable("character");
        }
        setupView(v);
        CharacterInfoViewModel viewModel = new ViewModelProvider(requireActivity()).get(CharacterInfoViewModel.class);
        viewModel.setCharacter(character);
        RecyclerView recyclerView = v.findViewById(R.id.residents_rv);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CharacterEpisodesAdapter adapter = new CharacterEpisodesAdapter(viewModel.getEpisodes().getValue(), episode -> {
            ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
            EpisodeInfoFragment fragment =
                    EpisodeInfoFragment.getInstance(episode);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);
        viewModel.getEpisodes().observe(getViewLifecycleOwner(), residents -> {
            adapter.setEpisodes(residents);
            adapter.notifyDataSetChanged();
        });
        viewModel.getLocations().observe(getViewLifecycleOwner(), locations -> {
            this.locations = locations;
            mOrigin.setOnClickListener(this);
            mLocation.setOnClickListener(this);
            mLocation.setTextColor(getResources().getColor(R.color.default_text_color));
            mOrigin.setTextColor(getResources().getColor(R.color.default_text_color));
            if (locations.get("location") != null) mLocation.setTextColor(getResources().getColor(R.color.holo_blue_dark));
            if (locations.get("origin") != null) mOrigin.setTextColor(getResources().getColor(R.color.holo_blue_dark));
        });
        return v;
    }

    private void setupView(View v) {

        Glide.with(v).load(character.getImage()).into((ImageView) v.findViewById(R.id.image));
        mLocation = v.findViewById(R.id.location);
        mOrigin = v.findViewById(R.id.origin);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        ((TextView) v.findViewById(R.id.status)).setText(character.getStatus());
        ((TextView) v.findViewById(R.id.species)).setText(character.getSpecies());
        ((TextView) v.findViewById(R.id.gender)).setText(character.getGender());
        mOrigin.setText(character.getOrigin().getName());
        mLocation.setText(character.getLocation().getName());
        mActionBar.setTitle(character.getName());

    }

    @Override
    public void onStop() {
        super.onStop();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).show();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location:
                if (locations.get("location") != null) {
                    ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
                    LocationInfoFragment fragment =
                            LocationInfoFragment.getInstance(locations.get("location"));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.origin:
                if (locations.get("origin") != null) {
                    ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
                    LocationInfoFragment fragment2 =
                            LocationInfoFragment.getInstance(locations.get("origin"));
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, fragment2)
                            .addToBackStack(null)
                            .commit();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(character.getName());
    }

}
