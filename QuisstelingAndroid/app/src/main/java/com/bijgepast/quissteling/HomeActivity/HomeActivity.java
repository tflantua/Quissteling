package com.bijgepast.quissteling.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.SecondActivity;

public class HomeActivity extends AppCompatActivity {

    private Button leaderBoardButton;
    private Button infoButton;
    private Button codeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //init values
        this.leaderBoardButton = findViewById(R.id.leaderBoardButton);
        this.infoButton = findViewById(R.id.informationButton);
        this.codeButton = findViewById(R.id.insertCodeButton);

        this.leaderBoardButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            this.startActivity(intent);
        });

        this.infoButton.setOnClickListener(view -> {
            //TODO popup melding voor info
        });

        this.infoButton.setOnClickListener(view -> {
            //TODO popup melding voor code invoer
        });
    }
}