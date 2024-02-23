package com.example.rickandmorty.UI.Episodes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.R;
import com.example.rickandmorty.UI.Characters.CharacterInfoFragment;
import com.example.rickandmorty.UI.LocationOrEpisodeResidentsAdapter;
import com.example.rickandmorty.UI.MainActivity;
import com.example.rickandmorty.ViewModel.Episode.EpisodeInfoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EpisodeInfoFragment extends Fragment {
    private Episode episode;
    private List<TheCharacter> residents;

    public EpisodeInfoFragment() {
        // Required empty public constructor
    }

    public static EpisodeInfoFragment getInstance(Episode episode) {
        EpisodeInfoFragment fragment = new EpisodeInfoFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putParcelable("episode", episode);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_episode_info, container, false);
        if (getArguments() != null) {
            episode = getArguments().getParcelable("episode");
        }
        EpisodeInfoViewModel viewModel = new ViewModelProvider(requireActivity()).get(EpisodeInfoViewModel.class);
        viewModel.setEpisode(episode);
        RecyclerView recyclerView = v.findViewById(R.id.residents_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocationOrEpisodeResidentsAdapter adapter = new LocationOrEpisodeResidentsAdapter(viewModel.getResidents().getValue(), character -> {
            ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
            CharacterInfoFragment fragment =
                    CharacterInfoFragment.getInstance(character);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);
        viewModel.getResidents().observe(getViewLifecycleOwner(), residents ->{
            adapter.setResidents(residents);
            adapter.notifyDataSetChanged();
        });
        setupView(v);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(episode.getEpisode());
        return v;
    }

    private void setupView(View v) {

        ((TextView) v.findViewById(R.id.name)).setText(episode.getName());
        ((TextView) v.findViewById(R.id.air_date)).setText(episode.getAirDate());


    }

    @Override
    public void onStop() {
        super.onStop();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).show();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(episode.getEpisode());
    }

}
