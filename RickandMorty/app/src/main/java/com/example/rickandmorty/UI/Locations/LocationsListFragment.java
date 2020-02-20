package com.example.rickandmorty.UI.Locations;


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
import com.example.rickandmorty.ViewModel.Location.ListLocationsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Hashtable;

/**
 * A simple {@link Fragment} subclass.
 * checked
 */
public class LocationsListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;

    public static LocationsListFragment getInstance() {
        return new LocationsListFragment();
    }


    public LocationsListFragment() {
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

        ListLocationsViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ListLocationsViewModel.class);

        final LocationsAdapter adapter = new LocationsAdapter(location -> {
            ((FloatingActionButton) getActivity().findViewById(R.id.fab)).hide();
            LocationInfoFragment fragment =
                    LocationInfoFragment.getInstance(location);
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
                countTv.setText(String.format("Locations: %s", count.toString()));
            }
        });
        itemViewModel.init();

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
