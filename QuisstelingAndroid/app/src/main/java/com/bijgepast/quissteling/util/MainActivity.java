package com.bijgepast.quissteling.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.LeaderBoard;
import com.bijgepast.quissteling.signin.SignInActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private UserSetting userSetting;
    private static ArrayList<LeaderBoard> leaderBoards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaderBoards = new ArrayList<>();

        if(new File("data/data/com.bijgepast.quissteling/dummydata.json").exists()){
            leaderBoards = IO.readLeaderBoard();
        }

        this.userSetting = new UserSetting(this);
        //this.userSetting.remove();
        InitQuestion.add();

        if (userSetting.exists()) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Log.d(LOG_TAG, "Username found sending to HomeActivity");
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            Log.d(LOG_TAG, "No Username found sending to SignInActivity");
            startActivity(intent);
        }
    }

    public static ArrayList<LeaderBoard> getLeaderBoards() {
        return leaderBoards;
    }
}