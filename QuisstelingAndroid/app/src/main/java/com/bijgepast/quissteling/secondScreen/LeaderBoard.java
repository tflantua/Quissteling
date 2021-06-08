package com.bijgepast.quissteling.secondScreen;

import java.io.Serializable;

public class LeaderBoard implements Serializable {
    private final String place;
    private final String userName;
    private final int score;

    public LeaderBoard(String place, String userName, int score) {
        this.place = place;
        this.userName = userName;
        this.score = score;
    }

    public String getPlace() {
        return place + "";
    }

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return "" + this.score;
    }
}
