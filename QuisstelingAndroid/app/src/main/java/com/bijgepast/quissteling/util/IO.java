package com.bijgepast.quissteling.util;

import android.util.JsonReader;
import android.util.JsonWriter;

import com.bijgepast.quissteling.secondScreen.LeaderBoard;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class IO {

    public static void writeLeaderBoard (ArrayList<LeaderBoard> leaderBoards){

        File file = new File("data/data/com.bijgepast.quissteling/dummydata.json");
        try(PrintWriter out = new PrintWriter(file)){
            JSONArray jsonArray = new JSONArray();
            JSONArray names = new JSONArray();
            for (LeaderBoard l:leaderBoards) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", l.getUserName());
                jsonObject.put("place", l.getPlace());
                jsonObject.put("score", l.getScore());
                jsonArray.put(jsonObject);
                names.put(l.getUserName());
            }
            out.write(jsonArray.toJSONObject(names).toString());
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<LeaderBoard> readLeaderBoard(){
        File file = new File("data/data/com.bijgepast.quissteling/dummydata.json");

        ArrayList<LeaderBoard> leaderBoards = new ArrayList<>();

        try(JsonReader jsonReader = new JsonReader(new FileReader(file))){
            jsonReader.beginArray();
            while (jsonReader.hasNext()){
                jsonReader.beginObject();
                String fullString = jsonReader.nextString();
                String[] stringArray = fullString.split(",");
                String usernameString = "";
                int place = 0;
                int score = 0;
                for(int i = 0; i < stringArray.length; i++){
                    if(stringArray[i].contains("username")){
                        String[] username = stringArray[i].split(":");
                        usernameString = username[1].substring(1, username[1].length()-1);
                    } else if(stringArray[i].contains("place")){
                        String[] placeSplit = stringArray[i].split(":");
                        place = Integer.parseInt(placeSplit[1].substring(1, placeSplit[1].length()-1));
                    } else if(stringArray[i].contains("score")){
                        String[] scoreSplit = stringArray[i].split(":");
                        score = Integer.parseInt(scoreSplit[1].substring(1, scoreSplit[1].length()-1));
                    }
                }
                jsonReader.endObject();
                leaderBoards.add(new LeaderBoard(place, usernameString, score));
            }
            jsonReader.endArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        return leaderBoards;
    }
}
