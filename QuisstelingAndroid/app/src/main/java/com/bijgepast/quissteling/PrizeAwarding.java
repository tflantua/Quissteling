package com.bijgepast.quissteling;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.secondScreen.LeaderBoard;

import java.time.LocalTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PrizeAwarding {
    private final LocalTime awardTime = LocalTime.of(17, 30);
    private boolean doneForToday = false;
    private final LeaderBoard leaderBoard;

    public PrizeAwarding(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public void checkTime(){
        if (LocalTime.now().isAfter(awardTime) && !doneForToday){
            awardPrize();
            doneForToday = true;
        }
        if (LocalTime.now().isBefore(awardTime)){
            doneForToday = false;
        }
    }

    private void awardPrize(){
        if (leaderBoard.getPlace().equals("1")){
            //TODO award prize.
        } else if (leaderBoard.getPlace().equals("2")){
            //TODO award prize.
        } else if (leaderBoard.getPlace().equals("3")){
            //TODO award prize.
        }
    }

    public void chanceAtFLPass(){
        double chance = 0.03;
        if (Math.random() < chance){
            //TODO award FastLane Pass
        }
    }
}
