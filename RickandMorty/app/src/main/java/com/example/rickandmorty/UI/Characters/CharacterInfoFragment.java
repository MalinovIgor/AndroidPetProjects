package com.example.rickandmorty.UI.Characters;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterInfoFragment extends Fragment {

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
        setupView(v);
        return v;
    }

    private void setupView(View v) {
        if (getArguments() != null) {
            TheCharacter character = getArguments().getParcelable("character");
            Glide.with(v).load(character.getImage()).into((ImageView) v.findViewById(R.id.image));
            ((TextView)v.findViewById(R.id.status)).setText(character.getStatus());
            ((TextView)v.findViewById(R.id.species)).setText(character.getSpecies());
            ((TextView)v.findViewById(R.id.gender)).setText(character.getGender());
            ((TextView)v.findViewById(R.id.origin)).setText(character.getOrigin().getName());
            ((TextView)v.findViewById(R.id.location)).setText(character.getLocation().getName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        ((FloatingActionButton)getActivity().findViewById(R.id.fab)).show();
    }
}
