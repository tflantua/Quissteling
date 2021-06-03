package com.bijgepast.quissteling;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSetting {
    private static final String LOGTAG = UserSetting.class.getSimpleName();

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
        return sharedPref.contains(USERNAME_KEY) && sharedPref.contains(String.valueOf(SCORE_KEY));
    }

    public String getUsername() {
        return sharedPref.getString(USERNAME_KEY, "");
    }

    public void setUsername(String username) {
        this.sharedPref.edit().putString(USERNAME_KEY, username).apply();
    }

    public int getScore() {
        return sharedPref.getInt(String.valueOf(SCORE_KEY), 0);
    }

    public void setScore(int score) {
        this.sharedPref.edit().putInt(SCORE_KEY, score).apply();
    }
}
