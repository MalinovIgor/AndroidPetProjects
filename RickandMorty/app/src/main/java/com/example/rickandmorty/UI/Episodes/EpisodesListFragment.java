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

import java.util.Hashtable;

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

        ListEpisodesViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ListEpisodesViewModel.class);
        itemViewModel.query.setValue(new Hashtable<String, String>() {{
            put("name", "");
            put("episode", "");
        }});
        final EpisodesAdapter adapter = new EpisodesAdapter();

        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), adapter::submitList);
        TextView countTv = v.findViewById(R.id.count);
        itemViewModel.count.observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                countTv.setText(String.format("Найдено эпизодов: %s", count.toString()));
            }
        });
        recyclerView.setAdapter(adapter);


        return v;
    }
}
