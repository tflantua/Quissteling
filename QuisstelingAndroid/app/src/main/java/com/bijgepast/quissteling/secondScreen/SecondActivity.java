package com.bijgepast.quissteling.secondScreen;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.databinding.ActivitySecondscreenBinding;
import com.bijgepast.quissteling.util.PopUpClass;
import com.bijgepast.quissteling.util.UserSetting;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SecondActivity extends AppCompatActivity {

    private Button backButton;
    private Button infoButton;
    private Button codeButton;

    private UserSetting userSetting;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSetting = new UserSetting(this);
        ActivitySecondscreenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_secondscreen);
        binding.setScore(userSetting);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_leaderboard, R.id.navigation_myprizes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        //init values
        this.backButton = findViewById(R.id.goBack);
        this.infoButton = findViewById(R.id.InfoButton);
        this.codeButton = findViewById(R.id.CodeButton);

        //Onclicklisteners
        backButton.setOnClickListener(view -> {
            this.onBackPressed();
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