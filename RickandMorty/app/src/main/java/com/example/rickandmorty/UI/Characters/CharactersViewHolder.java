package com.example.rickandmorty.UI.Characters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.rickandmorty.R;
//checked
public class CharactersViewHolder extends RecyclerView.ViewHolder {

    private static final int LAYOUT = R.layout.character_list_item;

    private RequestManager imageLoader;

    private ImageView mImage;
    private TextView mName;



    public CharactersViewHolder(@NonNull View itemView, RequestManager requestManager) {
        super(itemView);
        this.imageLoader = requestManager;
        findViews(itemView);
    }

    private void findViews(@NonNull View itemView){
        mImage = itemView.findViewById(R.id.image);
        mName = itemView.findViewById(R.id.name);
    }

    public void bindItem(@NonNull String imgUrl, String name){
        imageLoader.load(imgUrl).into(mImage);
        mName.setText(name);
    }
}
