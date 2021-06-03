package com.bijgepast.quissteling.secondScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bijgepast.quissteling.BR;
import com.bijgepast.quissteling.R;

import java.util.List;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardViewHolder> {
    private static final String LOGTAG = LeaderBoardAdapter.class.getName();

    private final Context appContext;
    private final List<LeaderBoard> leaderBoards;

    class LeaderBoardViewHolder extends RecyclerView.ViewHolder {
        public final ViewDataBinding binding;

        public LeaderBoardViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            this.binding.setVariable(BR.data, obj);
            this.binding.executePendingBindings();
        }
    }

    public LeaderBoardAdapter(Context context, List<LeaderBoard> leaderBoards) {
        appContext = context;
        this.leaderBoards = leaderBoards;
    }

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.leaderboard_item, viewGroup, false);
        return new LeaderBoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder leaderBoardViewHolder, int i) {
        Log.d(LOGTAG, "onBindViewHolder() called for item " + i);

        leaderBoardViewHolder.bind(leaderBoards.get(i));
    }

    @Override
    public int getItemCount() {
        return leaderBoards.size();
    }

}
