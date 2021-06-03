package com.bijgepast.quissteling.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.bijgepast.quissteling.R;

public class UserSetting {
    private static final String LOGTAG = UserSetting.class.getSimpleName();

    private String username;
    private int score;

    private final Context context;
    private final SharedPreferences sharedPref;

    public final static String USERNAME_KEY = "username";
    public final static int SCORE_KEY = 0;

    public static UserSetting getUserSettingFromPref(Context context){
        return new UserSetting(context);
    }

    private UserSetting(Context context){
        this.context = context;
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.username = sharedPref.getString(USERNAME_KEY, "User");
        this.score = sharedPref.getInt(String.valueOf(SCORE_KEY), 0);
        Log.d(LOGTAG, "User name restored, Username: " + username + " Score: " + score);
    }

    public void save(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USERNAME_KEY, username);
        editor.putInt(String.valueOf(SCORE_KEY), score);
        editor.apply();
        Log.d(LOGTAG, "User setting saved");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
