package com.example.rickandmorty.UI.Characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.R;

public class CharactersAdapter extends PagedListAdapter<TheCharacter, CharactersAdapter.CharactersViewHolder> {

    private RequestManager glideRequestManager;
    private final OnItemClickListener clickListener;


    public CharactersAdapter(RequestManager glideRequestManager, @Nullable OnItemClickListener clickListener) {
        super(TheCharacter.DIFF_CALLBACK);
        this.glideRequestManager = glideRequestManager;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_list_item, parent, false);
        return new CharactersViewHolder(view, glideRequestManager, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        TheCharacter character = getItem(position);
        ((CharactersViewHolder) holder).bindItem(character.getImage(), character.getName());
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull TheCharacter character);
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.character_list_item;

        private RequestManager imageLoader;

        private ImageView mImage;
        private TextView mName;



        CharactersViewHolder(@NonNull View itemView, RequestManager requestManager, @Nullable OnItemClickListener listener) {
            super(itemView);
            this.imageLoader = requestManager;
            findViews(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        private void findViews(@NonNull View itemView){
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.name);
        }

        void bindItem(@NonNull String imgUrl, String name){
            imageLoader.load(imgUrl).into(mImage);
            mName.setText(name);
        }


    }
}
