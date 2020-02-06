package com.example.rickandmorty.UI.Characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.bumptech.glide.RequestManager;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.R;
//checked
public class CharactersAdapter extends PagedListAdapter<TheCharacter, CharactersViewHolder> {

    private RequestManager glideRequestManager;

    public CharactersAdapter(RequestManager glideRequestManager) {
        super(TheCharacter.DIFF_CALLBACK);
        this.glideRequestManager = glideRequestManager;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_list_item, parent, false);
        return new CharactersViewHolder(view, glideRequestManager);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        TheCharacter character = getItem(position);
        ((CharactersViewHolder) holder).bindItem(character.getImage(), character.getName());
    }
}
