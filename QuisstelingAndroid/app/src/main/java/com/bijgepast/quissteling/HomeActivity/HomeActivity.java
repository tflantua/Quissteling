package com.bijgepast.quissteling.HomeActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.databinding.ActivityHomeBinding;
import com.bijgepast.quissteling.secondScreen.SecondActivity;
import com.bijgepast.quissteling.util.PopUpClass;
import com.bijgepast.quissteling.util.UserSetting;

public class HomeActivity extends AppCompatActivity {

    private Button leaderBoardButton;
    private Button infoButton;
    private Button codeButton;
    private UserSetting userSetting;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userSetting = new UserSetting(this);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setScore(userSetting);

        userSetting.setPrizeAwarding();

        //init values
        this.leaderBoardButton = findViewById(R.id.leaderBoardButton);
        this.infoButton = findViewById(R.id.InfoButton);
        this.codeButton = findViewById(R.id.CodeButton);

        this.leaderBoardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            this.startActivity(intent);
        });

        this.infoButton.setOnClickListener(view -> {
            new PopUpClass(view, R.layout.popup_info, this).show();
        });

        this.codeButton.setOnClickListener(view -> {
            new PopUpClass(view, R.layout.popup_insertcode, this).show();
        });

        userSetting.getPrizeAwarding().checkTime();
    }
}