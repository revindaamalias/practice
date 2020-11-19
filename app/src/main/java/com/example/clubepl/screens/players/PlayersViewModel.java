package com.example.clubepl.screens.players;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clubepl.model.Player;

import java.util.List;

public class PlayersViewModel extends ViewModel {
    private MutableLiveData<List<Player>> playerListMutableLiveData = new MutableLiveData<List<Player>>();
    public List<Player> playersModelList = null;

    public PlayersViewModel(List<Player> playersModelList) {
        this.playersModelList = playersModelList;
        this.playerListMutableLiveData.setValue(playersModelList);
    }

    public LiveData<List<Player>> listPlayersLiveData (){
        return playerListMutableLiveData;
    }

    private MutableLiveData<Player> _navigateToDetail = new MutableLiveData<>();

    public LiveData<Player> navigateToDetail(){
        return _navigateToDetail;
    }

    public void onPlayersClicked(Player player){
        _navigateToDetail.setValue(player);
    }

    public void onPlayerlNavigated(){
        _navigateToDetail.setValue(null);
    }
}
