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

import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.Episode.ListEpisodesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * checked
 */
public class EpisodesListFragment extends Fragment implements View.OnClickListener {

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
        recyclerView.setNestedScrollingEnabled(false);

        ListEpisodesViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ListEpisodesViewModel.class);
        final EpisodesAdapter adapter = new EpisodesAdapter(episode -> {
            ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
            EpisodeInfoFragment fragment =
                    EpisodeInfoFragment.getInstance(episode);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(adapter);

        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), adapter::submitList);
        TextView countTv = v.findViewById(R.id.count);
        itemViewModel.getCount().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                countTv.setText(String.format("Episodes: %s", count.toString()));
            }
        });
        itemViewModel.init();

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
