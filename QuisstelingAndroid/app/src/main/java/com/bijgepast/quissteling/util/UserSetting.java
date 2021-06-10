package com.bijgepast.quissteling.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.PrizeAwarding;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.LeaderBoard;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserSetting {
    private final SharedPreferences sharedPref;
    private Context context;

    private final String USERNAME_KEY = "username";
    private final String SCORE_KEY = "scorekey";
    private final String DONE_TODAY = "done_today";

    private PrizeAwarding prizeAwarding;
    //TODO get leaderboard from json and place in constructor above

    public UserSetting(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void setPrizeAwarding() {
        this.prizeAwarding = new PrizeAwarding(new LeaderBoard("1", "Jochem", 99999), this.context);
    }

    public void remove() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(USERNAME_KEY);
        editor.remove(SCORE_KEY);
        editor.apply();
    }

    public boolean exists() {
        return sharedPref.contains(USERNAME_KEY) && sharedPref.contains(SCORE_KEY);
    }

    public void addScore(int add) {
        this.setScore(this.getScore() + add);
    }

    public String getUsername() {
        return sharedPref.getString(USERNAME_KEY, "");
    }

    public void setUsername(String username) {
        this.sharedPref.edit().putString(USERNAME_KEY, username).apply();
    }

    public String getScoreString() {
        return "Score: " + sharedPref.getInt(SCORE_KEY, 0);
    }

    public int getScore() {
        return sharedPref.getInt(SCORE_KEY, 0);
    }

    public void setScore(int score) {
        this.sharedPref.edit().putInt(SCORE_KEY, score).apply();
    }

    public void setDoneToday(boolean done) {
        this.sharedPref.edit().putBoolean(this.DONE_TODAY, done);
    }

    public boolean getDoneToday() {
        return this.sharedPref.getBoolean(this.DONE_TODAY, true);
    }

    public PrizeAwarding getPrizeAwarding() {
        return prizeAwarding;
    }
}
