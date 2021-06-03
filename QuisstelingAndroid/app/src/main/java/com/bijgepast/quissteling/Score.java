package com.bijgepast.quissteling;

import android.content.SharedPreferences;

import java.io.Serializable;

public class Score implements Serializable {
    private SharedPreferences sharedPreferences;
    private final int score;

    public Score() {
        //this.sharedPreferences = new SharedPreferences()
        this.score = 100;
    }

    public String getScore() {
        return this.score + "";
    }
}
