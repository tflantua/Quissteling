package com.bijgepast.quissteling.secondScreen.ui.MyPrizes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bijgepast.quissteling.R;

public class MyPrizesFragment extends Fragment {

    private MyPrizesViewModel myPrizesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPrizesViewModel =
                new ViewModelProvider(this).get(MyPrizesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myprizes, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        myPrizesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}