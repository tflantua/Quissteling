package com.bijgepast.quissteling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "usernameMessageToMainActivity";
    private EditText usernameInput;

    //TODO Gebruik maken van SharedPreferences om de Username op te slaan. (Zo kunnen we uiteindelijk checken of de gebruiker meteen door mag gaan naar de MainActivity)

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
                String message = usernameInput.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                Log.d(LOG_TAG, "Traversing to Mainactivity with username: " + message);
                startActivity(intent);
            }
        });


    }


}