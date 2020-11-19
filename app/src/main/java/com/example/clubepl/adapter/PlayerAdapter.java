package com.example.clubepl.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clubepl.databinding.ItemPlayerBinding;
import com.example.clubepl.model.Player;
import com.example.clubepl.screens.Players.OnItemPlayerListener;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> playerList;
    private OnItemPlayerListener itemPlayersListener;

    public PlayerAdapter(List<Player> playerList, OnItemPlayerListener itemPlayersListener) {
        this.playerList = playerList;
        this.itemPlayersListener = itemPlayersListener;
    }

    public PlayerAdapter(OnItemPlayerListener itemPlayersListener) {
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlayerBinding itemPlayerBinding = ItemPlayerBinding.inflate(layoutInflater,parent,false);
        return new PlayerViewHolder(itemPlayerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.bind(player, itemPlayersListener);
    }

    @Override
    public int getItemCount() {
        if (playerList!=null){
            return playerList.size();
        }else {
            return 0;
        }
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private ItemPlayerBinding binding;

        public PlayerViewHolder(@NonNull ItemPlayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Player player, OnItemPlayerListener itemPlayersListener){
            binding.setPlayer(player);
            binding.setPlayerListener(itemPlayersListener);
            binding.executePendingBindings();
        }
    }
}
