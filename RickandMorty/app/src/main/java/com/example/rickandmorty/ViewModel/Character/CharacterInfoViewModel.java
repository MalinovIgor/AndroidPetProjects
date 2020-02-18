package com.example.rickandmorty.ViewModel.Character;

import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.Data.Network.Character.TheCharacter;

public class CharacterInfoViewModel extends ViewModel {
    private TheCharacter character;

    public CharacterInfoViewModel(TheCharacter character) {
        this.character = character;
    }
}
