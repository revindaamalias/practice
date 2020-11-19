package com.example.clubepl.screens.players;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clubepl.R;
import com.example.clubepl.adapter.PlayerAdapter;
import com.example.clubepl.databinding.FragmentPlayersBinding;
import com.example.clubepl.model.Club;
import com.example.clubepl.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment {
    private FragmentPlayersBinding binding;
    private PlayersViewModel playersViewModel;
    private Club clubEplModels;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Player> playerList = new ArrayList<>();
        assert getArguments() != null;
        Club clubEpl = PlayersFragmentArgs.fromBundle(getArguments()).getClubEpl();
        if (clubEpl.getImage() == R.drawable.wlfc){
            playerList.add(new Player("Ki-Jana Hoever"));
            playerList.add(new Player("Marçal"));
            playerList.add(new Player("Pedro Neto"));
            playerList.add(new Player("Willy Boly"));
            playerList.add(new Player("Fábio Silva"));
            playerList.add(new Player("Jonny"));
        }else if (clubEpl.getImage() == R.drawable.lcfc){
            playerList.add(new Player("James Justin"));
            playerList.add(new Player("Demarai Gray"));
            playerList.add(new Player("Youri Tielemans"));
            playerList.add(new Player("James Maddison"));
            playerList.add(new Player("Islam Slimani"));
            playerList.add(new Player("Kelechi Iheanacho"));
        }else if (clubEpl.getImage() == R.drawable.lvfc){
            playerList.add(new Player("Mohamed Salah"));
            playerList.add(new Player("Virgil van Dijky"));
            playerList.add(new Player("James Milner"));
            playerList.add(new Player("Jordan Henderson"));
            playerList.add(new Player("Sadio Mané"));
            playerList.add(new Player("Alisson Ramsés Becker"));
        }else if (clubEpl.getImage() == R.drawable.cfc){
            playerList.add(new Player("Reece James"));
            playerList.add(new Player("Mateo Kovačić"));
            playerList.add(new Player("Jorge Luiz Frello Filho"));
            playerList.add(new Player("Timo Werner"));
            playerList.add(new Player("Andreas Christensen"));
            playerList.add(new Player("Marcos Alonso"));
        }else if (clubEpl.getImage() == R.drawable.arsenalfc) {
            playerList.add(new Player("Alexandre Lacazette"));
            playerList.add(new Player("Gabriel Martinelli"));
            playerList.add(new Player("Pierre-Emerick Aubameyang"));
            playerList.add(new Player("Mesut Özil"));
            playerList.add(new Player("David Luiz Moreira Marinho"));
            playerList.add(new Player("Sead Kolašinac"));
        }else if (clubEpl.getImage() == R.drawable.thfc) {
            playerList.add(new Player("Son Heung-min"));
            playerList.add(new Player("Harry Kane"));
            playerList.add(new Player("Lucas Moura"));
            playerList.add(new Player("Christian Eriksen"));
            playerList.add(new Player("Ben Davies"));
            playerList.add(new Player("Marcos AlonsoDavinson Sánchez Mina"));
        }else if (clubEpl.getImage() == R.drawable.efc) {
            playerList.add(new Player("Bernard"));
            playerList.add(new Player("Dominic Calvert-Lewin"));
            playerList.add(new Player("Jordan Pickford"));
            playerList.add(new Player("Robin Olsen"));
            playerList.add(new Player("Séamus Coleman"));
            playerList.add(new Player("\tMuhamed Besic"));
        }else if (clubEpl.getImage() == R.drawable.manchester) {
            playerList.add(new Player("Paul Pogba"));
            playerList.add(new Player("Bruno Fernandes"));
            playerList.add(new Player("Edinson Canvani"));
            playerList.add(new Player("Alex Telles"));
            playerList.add(new Player("Marcus Rashford"));
            playerList.add(new Player("Mason Greenwood"));
        }
        PlayerViewModelFactory playerViewModelFactory = new PlayerViewModelFactory(playerList);
        playersViewModel = new ViewModelProvider(this, playerViewModelFactory).get(PlayersViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false);
        binding.setPlayer(playersViewModel);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRvPlayer();
    }

    private  void setRvPlayer(){
        RecyclerView recyclerView = binding.rvPlayer;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        PlayerAdapter playerAdapter = new PlayerAdapter(new OnItemPlayerListener() {
            @Override
            public void onPlayersClicked(Player player) {
                playersViewModel.onPlayerlNavigated();
            }
        });
        recyclerView.setAdapter(playerAdapter);
        playersViewModel.listPlayersLiveData().observe(getViewLifecycleOwner(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> player) {
                playerAdapter.setPlayerList(player);
            }
        });
    }
}