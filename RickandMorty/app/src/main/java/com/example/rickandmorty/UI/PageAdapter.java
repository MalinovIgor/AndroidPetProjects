package com.example.rickandmorty.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rickandmorty.UI.Characters.CharactersListFragment;
import com.example.rickandmorty.UI.Episodes.EpisodesListFragment;
import com.example.rickandmorty.UI.Locations.LocationsListFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mNumOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return CharactersListFragment.getInstance();
            case 1: return LocationsListFragment.getInstance();
            case 2: return EpisodesListFragment.getInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
