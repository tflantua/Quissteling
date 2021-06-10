package com.bijgepast.quissteling.secondScreen;

import java.io.Serializable;

public class LeaderBoard implements Serializable {
    private final int place;
    private final String userName;
    private final int score;

    public LeaderBoard(int place, String userName, int score) {
        this.place = place;
        this.userName = userName;
        this.score = score;
    }

    public String getPlaceString() {
        return place + "";
    }

    public int getPlace() {
        return place;
    }

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return "" + this.score;
    }
}
