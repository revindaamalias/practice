package com.example.clubepl.screens.Players;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.clubepl.model.Player;

import java.util.List;

public class PlayerViewModelFactory implements ViewModelProvider.Factory {
    private List<Player> playerList;

    public PlayerViewModelFactory(List<Player> playerList) {
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PlayersViewModel.class)){
            return (T) new PlayersViewModel(playerList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta PlayersViewModel");
    }
}
