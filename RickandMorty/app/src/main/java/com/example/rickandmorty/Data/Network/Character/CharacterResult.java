package com.example.rickandmorty.Data.Network.Character;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class CharacterResult {
    @SerializedName("results")
    private List<TheCharacter> results;

    public List<TheCharacter> getResults() {
        return results;
    }
}
