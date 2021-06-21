package com.bijgepast.quissteling.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.secondScreen.LeaderBoard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IO {

    public static void writeLeaderBoard(ArrayList<LeaderBoard> leaderBoards) {

        File file = new File("data/data/com.bijgepast.quissteling/dummydata.json");
        int i = 0;
        try (PrintWriter out = new PrintWriter(file)) {
            JSONArray jsonArray = new JSONArray();
            JSONArray names = new JSONArray();
            for (LeaderBoard l : leaderBoards) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", l.getUserName());
                jsonObject.put("place", l.getPlace());
                jsonObject.put("score", l.getScore());
                jsonArray.put(jsonObject);
                names.put(i);
                i++;
            }
            out.write(jsonArray.toJSONObject(names).toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static ArrayList<LeaderBoard> readLeaderBoard() {
        File file = new File("data/data/com.bijgepast.quissteling/dummydata.json");
        String jsonData = "";

        ArrayList<LeaderBoard> leaderBoards = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            jsonData = bufferedReader.readLine();
            JSONObject jsonRootObject = new JSONObject(jsonData);

            for (int i = 0; i < jsonRootObject.length(); i++) {
                JSONObject jsonObject = jsonRootObject.getJSONObject(i + "");

                String username = jsonObject.getString("username");
                int place = jsonObject.getInt("place");
                int score = jsonObject.getInt("score");

                leaderBoards.add(new LeaderBoard(place, username, score));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return leaderBoards;
    }
}
