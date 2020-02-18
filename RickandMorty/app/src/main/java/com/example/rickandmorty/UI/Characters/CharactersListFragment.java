package com.example.rickandmorty.UI.Characters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.Character.ListCharactersViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * checked
 */
public class CharactersListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ListCharactersViewModel itemViewModel;

    public static CharactersListFragment getInstance() {
        return new CharactersListFragment();
    }


    public CharactersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_characters_list, container, false);
        recyclerView = v.findViewById(R.id.rv_characters_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ListCharactersViewModel.class);
        final CharactersAdapter adapter = new CharactersAdapter(Glide.with(v.getContext()),
                character -> {
                    ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
                    CharacterInfoFragment fragment =
                            CharacterInfoFragment.getInstance(character);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null)
                            .commit();
                });
        recyclerView.setAdapter(adapter);

        TextView countTv = v.findViewById(R.id.count);
        TextView errorTv = v.findViewById(R.id.error);
        ProgressBar loading_pb = v.findViewById(R.id.loading_pb);
        Button try_again_bt = v.findViewById(R.id.try_again_bt);
        try_again_bt.setOnClickListener(this);

        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), adapter::submitList);
        itemViewModel.getCount().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                countTv.setText(String.format("Characters: %s", count.toString()));
            }
        });
        itemViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case EMPTY_RESPONSE:
                    recyclerView.setVisibility(View.GONE);
                    countTv.setVisibility(View.GONE);
                    loading_pb.setVisibility(View.GONE);
                    try_again_bt.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                    errorTv.setText("There is nothing here");
                    break;
                case SERVER_ERROR:
                    recyclerView.setVisibility(View.GONE);
                    countTv.setVisibility(View.GONE);
                    loading_pb.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                    errorTv.setText("Server error");
                    try_again_bt.setVisibility(View.VISIBLE);
                    break;
                case CONNECTION_ERROR:
                    recyclerView.setVisibility(View.GONE);
                    countTv.setVisibility(View.GONE);
                    loading_pb.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                    errorTv.setText("Connection error");
                    try_again_bt.setVisibility(View.VISIBLE);
                    break;
                case LOADED:
                    errorTv.setVisibility(View.GONE);
                    loading_pb.setVisibility(View.GONE);
                    try_again_bt.setVisibility(View.GONE);
                    countTv.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    break;
                case LOADING:
                    recyclerView.setVisibility(View.GONE);
                    countTv.setVisibility(View.GONE);
                    errorTv.setVisibility(View.GONE);
                    try_again_bt.setVisibility(View.GONE);
                    loading_pb.setVisibility(View.VISIBLE);
                    break;
            }
        });
        itemViewModel.init();


        return v;
    }

    @Override
    public void onClick(View v) {
        itemViewModel.loadAgain();
    }
}
