package com.example.rickandmorty.ViewModel.Character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.CharacterEpisodes;
import com.example.rickandmorty.Data.Network.Character.CharacterLocationAndOrigin;
import com.example.rickandmorty.Data.Network.Character.TheCharacter;
import com.example.rickandmorty.Data.Network.Episode.Episode;
import com.example.rickandmorty.Data.Network.Location.Location;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class CharacterInfoViewModel extends ViewModel {
    private LiveData<List<Episode>> episodes;
    private LiveData<HashMap<String, Location>> locations;
    private MutableLiveData<TheCharacter> character = new MutableLiveData<>();



    public CharacterInfoViewModel() {
        episodes = Transformations.switchMap(this.character, input -> new CharacterEpisodes(input).getEpisodes());
        locations = Transformations.switchMap(this.character, input -> new CharacterLocationAndOrigin(input).getLocations());
    }

    public LiveData<List<Episode>> getEpisodes() {
        return episodes;
    }
    public LiveData<HashMap<String, Location>> getLocations() {
        return locations;
    }
    public void setCharacter(TheCharacter character){
        this.character.setValue(character);
    }
}
