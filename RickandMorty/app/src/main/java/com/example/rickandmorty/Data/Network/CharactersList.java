package com.example.rickandmorty.Data.Network;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharactersList {
    private List<TheCharacter> characters = new ArrayList<>();
    public List<TheCharacter> getCharacters() {
        return characters;
    }
}
