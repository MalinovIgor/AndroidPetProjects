package com.example.pharmacies_analysis.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.ui.search.SearchFragment;
import com.google.android.gms.maps.MapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private FloatingActionButton fab;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            getParentFragmentManager().beginTransaction().replace(R.id.container, SearchFragment.newInstance(), SearchFragment.class.toString())
                    .addToBackStack(null).commit();
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tab_fragment, DrugListFragment.newInstance()).addToBackStack(DrugListFragment.class.getName()).commit();
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tab_setup(tabLayout);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // TODO: Use the ViewModel
    }

    private void tab_setup(TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab().setText(R.string.list));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.map));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    fab.show();
                    if (getChildFragmentManager().findFragmentByTag(DrugListFragment.class.toString()) != null){
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.tab_fragment, Objects.requireNonNull(getChildFragmentManager()
                                        .findFragmentByTag(DrugListFragment.class.toString())))
                                .addToBackStack(null).commit();
                    } else {
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.tab_fragment, DrugListFragment.newInstance(), DrugListFragment.class.toString())
                                .addToBackStack(null).commit();
                    }

                } else if (tab.getPosition() == 1){
                    fab.hide();
                    if (getChildFragmentManager().findFragmentByTag(PharmacyMapFragment.class.toString()) != null){
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.tab_fragment, Objects.requireNonNull(getChildFragmentManager()
                                        .findFragmentByTag(PharmacyMapFragment.class.toString())))
                                .addToBackStack(null).commit();
                    } else {
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.tab_fragment, PharmacyMapFragment.newInstance(), PharmacyMapFragment.class.toString())
                                .addToBackStack(null).commit();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}