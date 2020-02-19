package com.example.rickandmorty.ViewModel.Character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.CharacterEpisodes;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;

import java.util.List;

public class CharacterInfoViewModel extends ViewModel {
    private LiveData<List<Episode>> episodes = new MutableLiveData<>();
    private MutableLiveData<TheCharacter> character = new MutableLiveData<>();



    public CharacterInfoViewModel() {
        episodes = Transformations.switchMap(this.character, input -> new CharacterEpisodes(input).getEpisodes());
    }

    public LiveData<List<Episode>> getEpisodes() {
        return episodes;
    }
    public void setCharacter(TheCharacter character){
        this.character.setValue(character);
    }
}
