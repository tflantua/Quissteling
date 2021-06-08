package com.bijgepast.quissteling.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.UserSetting;
import com.bijgepast.quissteling.databinding.ActivityHomeBinding;
import com.bijgepast.quissteling.secondScreen.SecondActivity;

public class HomeActivity extends AppCompatActivity {

    private Button leaderBoardButton;
    private Button infoButton;
    private Button codeButton;
    private UserSetting userSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userSetting = new UserSetting(this);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setScore(userSetting);

        //init values
        this.leaderBoardButton = findViewById(R.id.leaderBoardButton);
        this.infoButton = findViewById(R.id.InfoButton);
        this.codeButton = findViewById(R.id.CodeButton);

        this.leaderBoardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            this.startActivity(intent);
        });

        this.infoButton.setOnClickListener(view -> {
            //TODO popup melding voor info
        });

        this.infoButton.setOnClickListener(view -> {
            //TODO popup melding voor code invoer
        });
    }
}