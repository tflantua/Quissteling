package com.bijgepast.quissteling.signin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.util.MainActivity;
import com.bijgepast.quissteling.util.UserSetting;

public class SignInActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "usernameMessageToMainActivity";
    private EditText usernameInput;
    UserSetting userSetting;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameEdit);
        this.userSetting = new UserSetting(this);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            if (!this.usernameInput.getText().toString().isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                userSetting.setScore(0);
                userSetting.setUsername(usernameInput.getText().toString());
                Log.d(LOG_TAG, "Traversing to Mainactivity with username: "
                        + userSetting.getUsername()
                        + " Score: "
                        + userSetting.getScore());
                startActivity(intent);
                this.finish();
            } else Toast.makeText(this, getString(R.string.empty_username), Toast.LENGTH_LONG).show();
        });


    }


}