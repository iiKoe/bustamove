package com.group66.game.helpers;

import java.util.Date;
import java.util.TreeSet;

import org.apache.commons.lang.time.DateFormatUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class HighScoreManager {
    public static final TreeSet<HighScoreItem> highscores = new TreeSet<HighScoreItem>();
    
    private static FileHandle file = Gdx.files.external("bustamove/highscores.json");

    /**
     * Load high score data from file
     */
    public static void loadData() {
        try {
            if (!file.exists()) {
                return;
            }
            
            String contents = file.readString();
            JSONArray arr = new JSONArray(contents);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String name = obj.getString("name");
                String date = obj.getString("date");
                int score = obj.getInt("score");
                highscores.add(new HighScoreItem(name, date, score));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write high scores to file
     */
    public static void writeData() {
        try {
            JSONArray arr = new JSONArray();
            int count = 0;
            for (HighScoreItem hsi : highscores) {
                if (count >= 10) {
                    break;
                }
                count++;
                
                JSONObject obj = new JSONObject();
                obj.put("name", hsi.name);
                obj.put("date", hsi.date);
                obj.put("score", hsi.score);
                arr.put(obj);
            }
            file.writeString(arr.toString(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    /**
     * Add a score to the high score list
     * @param score The score the user just achieved
     */
    public static void addScore(int score) {
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        highscores.add(new HighScoreItem("unknown", date, score));
        writeData();
    }
}
