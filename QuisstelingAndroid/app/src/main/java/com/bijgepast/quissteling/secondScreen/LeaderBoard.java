package com.bijgepast.quissteling.secondScreen;

import java.io.Serializable;
import java.util.ArrayList;

public class LeaderBoard implements Serializable {
    private int place;
    private final String userName;
    private  int score;

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

    public void setPlace(int place) {
        this.place = place;
    }

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return "" + this.score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public static LeaderBoard getUserLeaderBoardFromList(String user, ArrayList<LeaderBoard> list) {
        for (LeaderBoard l : list) {
            if (l.userName.equals(user)) return l;
        }
        return null;
    }
}
