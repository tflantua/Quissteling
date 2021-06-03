package com.bijgepast.quissteling.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.MainActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.UserSetting;

public class SignInActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "usernameMessageToMainActivity";
    private EditText usernameInput;
    UserSetting userSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameEdit);
        this.userSetting = new UserSetting(this);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            userSetting.setScore(0);
            userSetting.setUsername(usernameInput.getText().toString());
            Log.d(LOG_TAG, "Traversing to Mainactivity with username: "
                    + userSetting.getUsername()
                    + " Score: "
                    + userSetting.getScore());
            startActivity(intent);
        });


    }


}