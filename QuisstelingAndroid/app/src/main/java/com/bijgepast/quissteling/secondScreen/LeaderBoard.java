package com.bijgepast.quissteling.secondScreen;

import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Arrays;

public class LeaderBoard implements Serializable {
    private String place;
    private String userName;
    private int score;

    public LeaderBoard(String place, String userName, int score) {
        this.place = place;
        this.userName = userName;
        this.score = score;
    }

    public String getPlace() {
        return place + ": ";
    }

    public String getUserName() {
        return userName + ": ";
    }

    public String getScore() {
        return "" + this.score;
    }
}
