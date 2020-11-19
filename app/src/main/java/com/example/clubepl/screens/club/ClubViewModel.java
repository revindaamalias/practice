package com.example.clubepl.screens.club;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clubepl.model.Club;

import java.util.List;

public class ClubViewModel extends ViewModel {
    private MutableLiveData<List<Club>> listMutableLiveData = new MutableLiveData<List<Club>>();
    public List<Club> clubList = null;

    public ClubViewModel(List<Club> clubList) {
        this.clubList = clubList;
        this.listMutableLiveData.setValue(clubList);
    }

    public LiveData<List<Club>> listClubLiveData (){
        return listMutableLiveData;
    }

    private MutableLiveData<Club> _navigateToDetail = new MutableLiveData<>();

    public LiveData<Club> navigateToDetail(){
        return _navigateToDetail;
    }

    public void onClubClicked(Club club){
        _navigateToDetail.setValue(club);
    }

    public void onClubNavigated(){
        _navigateToDetail.setValue(null);
    }
}
