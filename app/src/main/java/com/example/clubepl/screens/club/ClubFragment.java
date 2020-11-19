package com.example.clubepl.screens.club;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clubepl.R;
import com.example.clubepl.adapter.ClubAdapter;
import com.example.clubepl.databinding.FragmentClubBinding;
import com.example.clubepl.model.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubFragment extends Fragment {
    private FragmentClubBinding binding;
    private ClubViewModel clubEpl;

    public ClubFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Club> clubEpls = new ArrayList<>();
        clubEpls.add(new Club(R.drawable.arsenalfc, false));
        clubEpls.add(new Club(R.drawable.manchester, false));
        clubEpls.add(new Club(R.drawable.efc,false));
        clubEpls.add(new Club(R.drawable.lvfc,false));
        clubEpls.add(new Club(R.drawable.wlfc,false));
        clubEpls.add(new Club(R.drawable.cfc,false));
        clubEpls.add(new Club(R.drawable.lcfc,false));
        clubEpls.add(new Club(R.drawable.thfc,false));
        ClubViewModelFactory clubViewModelFactory = new ClubViewModelFactory(clubEpls);
        clubEpl = new ViewModelProvider(this, clubViewModelFactory).get(ClubViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_club, container, false);
        binding.setClub(clubEpl);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRvClub();
    }

    private void setupRvClub() {
        RecyclerView recyclerView = binding.rvClubEpl;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ClubAdapter clubAdapter = new ClubAdapter(new OnItemClubListener() {
            @Override
            public void onClubClicked(Club club) {
                clubEpl.onClubClicked(club);
            }
        });
        recyclerView.setAdapter(clubAdapter);
        clubEpl.listClubLiveData().observe(getViewLifecycleOwner(), new Observer<List<Club>>() {
            @Override
            public void onChanged(List<Club> club) {
                clubAdapter.setClubList(club);
            }
        });

        clubEpl.navigateToDetail().observe(getViewLifecycleOwner(), new Observer<Club>() {
            @Override
            public void onChanged(Club club) {
                if(club != null) {
                    NavDirections action = ClubFragmentDirections.actionClubFragmentToPlayersFragment(club);
                    Navigation.findNavController(requireView()).navigate(action);
                    clubEpl.onClubNavigated();
                }
            }
        });
    }
}