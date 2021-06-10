package com.bijgepast.quissteling;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.secondScreen.LeaderBoard;
import com.bijgepast.quissteling.util.UserSetting;

import java.time.LocalTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PrizeAwarding {
    private UserSetting userSetting;
    private final LocalTime awardTime = LocalTime.of(17, 30);
    private boolean doneForToday;
    private final LeaderBoard leaderBoard;

    public PrizeAwarding(LeaderBoard leaderBoard, Context context) {
        this.leaderBoard = leaderBoard;
        this.userSetting = new UserSetting(context);
        this.doneForToday = this.userSetting.getDoneToday();
    }

    public void checkTime(){
        if (LocalTime.now().isAfter(awardTime) && !doneForToday){
            awardPrize();
            userSetting.setDoneToday(true);
        }
        if (LocalTime.now().isBefore(awardTime)){
            userSetting.setDoneToday(false);
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
}
