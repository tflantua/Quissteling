package com.bijgepast.quissteling;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.signin.SignInActivity;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private UserSetting userSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}