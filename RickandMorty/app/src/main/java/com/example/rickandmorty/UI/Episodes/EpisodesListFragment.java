package com.example.rickandmorty.UI.Episodes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.ListEpisodesViewModel;
import com.example.rickandmorty.ViewModel.ListLocationsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * checked
 */
public class EpisodesListFragment extends Fragment {

    private RecyclerView recyclerView;

    public static EpisodesListFragment getInstance() {
        return new EpisodesListFragment();
    }


    public EpisodesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_characters_list, container, false);
//        mViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        recyclerView = v.findViewById(R.id.rv_characters_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        ListEpisodesViewModel itemViewModel = ViewModelProviders.of(this).get(ListEpisodesViewModel.class);
        final EpisodesAdapter adapter = new EpisodesAdapter();

        itemViewModel.itemPagedList.observe(this, adapter::submitList);

        recyclerView.setAdapter(adapter);


        return v;
    }
}
