package com.bijgepast.quissteling;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSetting {
    private final SharedPreferences sharedPref;

    private final String USERNAME_KEY = "username";
    private final String SCORE_KEY = "scorekey";

    public UserSetting(Context context) {
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
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
}
