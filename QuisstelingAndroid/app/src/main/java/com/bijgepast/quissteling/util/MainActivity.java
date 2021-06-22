package com.bijgepast.quissteling.util;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.LeaderBoard;
import com.bijgepast.quissteling.signin.SignInActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Proof of concept Quissteling app
 *
 * by
 *      Luca Brugel
 *      Thomas Flantua
 *      Wesley Roks
 *      Jochem de Goeij
 *      Martijn de Kam
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private UserSetting userSetting;
    private static ArrayList<LeaderBoard> leaderBoards;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaderBoards = new ArrayList<>();

        if (new File("data/data/com.bijgepast.quissteling/dummydata.json").exists()) {
            leaderBoards = IO.readLeaderBoard();
        } else {
            leaderBoards.add(new LeaderBoard(1, "Thomas", 1000));
            leaderBoards.add(new LeaderBoard(2, "Wesley", 700));
            leaderBoards.add(new LeaderBoard(3, "Jochem", 500));
            leaderBoards.add(new LeaderBoard(4, "Martijn", 300));
            leaderBoards.add(new LeaderBoard(5, "Luca", 100));

            IO.writeLeaderBoard(leaderBoards);
        }

        this.userSetting = new UserSetting(this);

        // TODO for testing uncomment below code so multiple users can use the same device
        // For testing purposes, to be able to make multiple users
//        this.userSetting.remove();
        InitQuestion.add(this);

        if (userSetting.exists()) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Log.d(LOG_TAG, "Username found sending to HomeActivity");
            startActivity(intent);
            this.finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            Log.d(LOG_TAG, "No Username found sending to SignInActivity");
            startActivity(intent);
            this.finish();
        }
    }

    public static ArrayList<LeaderBoard> getLeaderBoards() {
        return leaderBoards;
    }

    public static void sortLeaderboards() {
        leaderBoards.sort(new Comparator<LeaderBoard>() {
            @Override
            public int compare(LeaderBoard o1, LeaderBoard o2) {
                return Integer.parseInt(o2.getScore()) - Integer.parseInt(o1.getScore());
            }
        });
        updateRanking();
    }

    private static void updateRanking() {
        ArrayList<LeaderBoard> leaderBoards = getLeaderBoards();
        for (int i = 0; i < leaderBoards.size(); i++) {
            leaderBoards.get(i).setPlace(i + 1);
        }
    }

    public static void swapLeaderboard(String username, int score) {
        for (int i = 0; i < leaderBoards.size(); i++) {
            LeaderBoard leaderBoard = leaderBoards.get(i);
            if (leaderBoard.getUserName().equals(username)) {
                int rank = leaderBoard.getPlace();
                leaderBoards.remove(leaderBoard);
                leaderBoards.add(new LeaderBoard(rank, username, score));
            }
        }
    }
}