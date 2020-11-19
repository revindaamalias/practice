package com.example.clubepl.screens.club;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.clubepl.model.Club;

import java.util.List;

public class ClubViewModelFactory implements ViewModelProvider.Factory {
    private List<Club> clubList;

    public ClubViewModelFactory(List<Club> clubList) {
        this.clubList = clubList;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ClubViewModel.class)) {
            return (T) new ClubViewModel(clubList);
        }
        throw new IllegalArgumentException("Viewmodel Yang Diminta ClubEplViewModel");
    }
}
