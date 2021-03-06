package com.bijgepast.quissteling.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.PrizeAwarding;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.LeaderBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserSetting {
    private static final String LOG_TAG = UserSetting.class.getSimpleName();
    private final SharedPreferences sharedPref;
    private Context context;

    private final String USERNAME_KEY = "username";
    private final String SCORE_KEY = "scorekey";
    private final String DONE_TODAY = "done_today";
    private final String PRIZE1 = "prize1";
    private final String PRIZE2 = "prize2";
    private final String PRIZE3 = "prize3";
    private final String PRIZE4 = "prize4";
    private final String PRIZE5 = "prize5";
    private final String HOURS = "hours";
    private final String MINS = "minutes";
    private final String DAY = "day";
    private final String MONTH = "month";
    private final String YEAR = "year";

    private PrizeAwarding prizeAwarding;

    public UserSetting(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        this.prizeAwarding = new PrizeAwarding(LeaderBoard.getUserLeaderBoardFromList(getUsername(), IO.readLeaderBoard()), this);
    }

    public void setPrizeAwarding(PrizeAwarding prizeAwarding) {
        this.prizeAwarding = prizeAwarding;
    }

    public void remove() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(USERNAME_KEY);
        editor.remove(SCORE_KEY);
        editor.remove(HOURS);
        editor.remove(MINS);
        editor.apply();
    }

    public boolean exists() {
        return sharedPref.contains(USERNAME_KEY) && sharedPref.contains(SCORE_KEY);
    }

    public void addScore(int add) {
        this.setScore(this.getScore() + add);
        MainActivity.swapLeaderboard(getUsername(), getScore());
        try {
            MainActivity.sortLeaderboards();
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "Nullpointer in sorting leaderboards in addScore");
            e.printStackTrace();
        }

        // commit change to json
        IO.writeLeaderBoard(MainActivity.getLeaderBoards());
    }

    public String getUsername() {
        return sharedPref.getString(USERNAME_KEY, "");
    }

    public void setUsername(String username) {
        this.sharedPref.edit().putString(USERNAME_KEY, username).commit();
        Log.i(LOG_TAG, "Added username to preferences: " + this.sharedPref.contains(USERNAME_KEY));
    }

    public String getScoreString() {
        return "Score: " + sharedPref.getInt(SCORE_KEY, 0);
    }

    public int getScore() {
        return sharedPref.getInt(SCORE_KEY, 0);
    }

    public void setScore(int score) {
        this.sharedPref.edit().putInt(SCORE_KEY, score).commit();
        Log.i(LOG_TAG, "Added score to preferences: " + this.sharedPref.contains(SCORE_KEY));
    }

    public void setDoneToday(boolean done) {
        this.sharedPref.edit().putBoolean(this.DONE_TODAY, done).apply();
    }

    public boolean getDoneToday() {
        return this.sharedPref.getBoolean(this.DONE_TODAY, true);
    }

    public void setPrize1(boolean owned) {
        this.sharedPref.edit().putBoolean(this.PRIZE1, owned).apply();
    }

    public boolean getPrize1() {
        return this.sharedPref.getBoolean(this.PRIZE1, false);
    }

    public void setPrize2(boolean owned) {
        this.sharedPref.edit().putBoolean(this.PRIZE2, owned).apply();
    }

    public boolean getPrize2() {
        return this.sharedPref.getBoolean(this.PRIZE2, false);
    }

    public void setPrize3(boolean owned) {
        this.sharedPref.edit().putBoolean(this.PRIZE3, owned).apply();
    }

    public boolean getPrize3() {
        return this.sharedPref.getBoolean(this.PRIZE3, false);
    }

    public void setPrize4(boolean owned) {
        this.sharedPref.edit().putBoolean(this.PRIZE4, owned).apply();
    }

    public boolean getPrize4() {
        return this.sharedPref.getBoolean(this.PRIZE4, false);
    }

    public void setPrize5(boolean owned) {
        this.sharedPref.edit().putBoolean(this.PRIZE5, owned).apply();
    }

    public boolean getPrize5() {
        return this.sharedPref.getBoolean(this.PRIZE5, false);
    }

    public void setLastDate(LocalDateTime time) {
        this.sharedPref.edit().putInt(this.HOURS, time.getHour()).apply();
        this.sharedPref.edit().putInt(this.MINS, time.getMinute()).apply();
        this.sharedPref.edit().putInt(this.YEAR, time.getYear()).apply();
        this.sharedPref.edit().putInt(this.MONTH, time.getMonthValue()).apply();
        this.sharedPref.edit().putInt(this.DAY, time.getDayOfMonth()).apply();
    }

    public LocalDateTime getLastDate() {
        if (sharedPref.contains(this.HOURS) && sharedPref.contains(MINS)) {
            int hours = this.sharedPref.getInt(this.HOURS, 0);
            int minutes = this.sharedPref.getInt(this.MINS, 0);
            int year = this.sharedPref.getInt(this.YEAR, 0);
            int month = this.sharedPref.getInt(this.MONTH, 1);
            int day = this.sharedPref.getInt(this.DAY, 1);
            // TODO for testing remove below +1 and instead do +0 to be able to keep on answering questions
            return LocalDateTime.of(year, month, day, hours + 1, minutes);
        } else {
            return null;
        }
    }

    public PrizeAwarding getPrizeAwarding() {
        return prizeAwarding;
    }

}
