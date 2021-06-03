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
    //UserSetting userSetting;

    //TODO UserSettings class maken voor het gebruik van SharedPreferences over de hele applicatie
    //TODO Gebruik maken van SharedPreferences om de Username op te slaan. (Zo kunnen we uiteindelijk checken of de gebruiker meteen door mag gaan naar de MainActivity)
    //TODO onDestroy methode maken dat de username opslaat in de SharedPrefrences doormiddel van de UserSettings class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameEdit);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Shared preferences meegeven
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                String message = usernameInput.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                Log.d(LOG_TAG, "Traversing to Mainactivity with username: " + message);
                startActivity(intent);
            }
        });


    }


}