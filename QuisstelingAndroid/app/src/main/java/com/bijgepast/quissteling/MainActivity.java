package com.bijgepast.quissteling;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.quiz.QuizActivity;
import com.bijgepast.quissteling.util.UserSetting;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private UserSetting userSetting;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userSetting = new UserSetting(this);
        this.userSetting.remove();

//        if (userSetting.exists()) {
//            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//            Log.d(LOG_TAG, "Username found sending to HomeActivity");
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
//            Log.d(LOG_TAG, "No Username found sending to SignInActivity");
//            startActivity(intent);
//        }

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.EXTRA_QUIZ_ID, "0101");
        this.startActivity(intent);
    }
}