package com.bijgepast.quissteling.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijgepast.quissteling.MainActivity;
import com.bijgepast.quissteling.R;

public class SignInActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "usernameMessageToMainActivity";
    private EditText usernameInput;
    UserSetting userSetting;

    protected void onDestroy(){
        userSetting.setUsername(usernameInput.getText().toString());
        userSetting.setScore(0);
        userSetting.save();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameEdit);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(UserSetting.USERNAME_KEY, usernameInput.getText().toString());
                intent.putExtra(String.valueOf(UserSetting.SCORE_KEY), 0);
                Log.d(LOG_TAG, "Traversing to Mainactivity with username: "
                        + intent.getStringExtra(UserSetting.USERNAME_KEY)
                        + " Score: "
                        + intent.getStringExtra(String.valueOf(UserSetting.SCORE_KEY)));
                startActivity(intent);
            }
        });


    }


}