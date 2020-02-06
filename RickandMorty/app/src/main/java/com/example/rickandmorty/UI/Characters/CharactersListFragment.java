package com.example.rickandmorty.UI.Characters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.ListCharactersViewModel;
import com.example.rickandmorty.ViewModel.ListLocationsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * checked
 */
public class CharactersListFragment extends Fragment {

    private RecyclerView recyclerView;

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
//        mViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        recyclerView = v.findViewById(R.id.rv_characters_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        ListCharactersViewModel itemViewModel = ViewModelProviders.of(this).get(ListCharactersViewModel.class);
        final CharactersAdapter adapter = new CharactersAdapter(Glide.with(v.getContext()));

        itemViewModel.itemPagedList.observe(this, adapter::submitList);

        recyclerView.setAdapter(adapter);


        return v;
    }
}
