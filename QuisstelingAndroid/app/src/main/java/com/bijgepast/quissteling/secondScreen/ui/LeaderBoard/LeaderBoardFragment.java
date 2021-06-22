package com.bijgepast.quissteling.secondScreen.ui.LeaderBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.LeaderBoard;
import com.bijgepast.quissteling.secondScreen.LeaderBoardAdapter;
import com.bijgepast.quissteling.util.MainActivity;

import java.util.ArrayList;

public class LeaderBoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private LeaderBoardAdapter leaderBoardAdapter;

    private LeaderBoardViewModel leaderBoardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leaderBoardViewModel =
                new ViewModelProvider(this).get(LeaderBoardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        this.recyclerView = root.findViewById(R.id.recyclerViewLeader);
        this.leaderBoardAdapter = new LeaderBoardAdapter(root.getContext(), MainActivity.getLeaderBoards());
        this.recyclerView.setAdapter(this.leaderBoardAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }


}