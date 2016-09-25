package com.group66.game.screens;

import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.group66.game.helpers.HighScoreItem;
import com.group66.game.settings.Config;

import org.json.*;

public class HighScoreScreen implements Screen {
    private TreeSet<HighScoreItem> highscores;
    private FileHandle file;
    private Stage stage;
    
    public HighScoreScreen() {
        highscores = new TreeSet<HighScoreItem>();
        file = Gdx.files.internal("highscores.json");
        loadData();
        createScreen();
    }

    private void loadData() {
        //load data from file
        try {
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
    
    private void writeData() {
        try {
            JSONArray arr = new JSONArray();
            int i = 0;
            for (HighScoreItem hsi : highscores) {
                if (i >= 10) {
                    break;
                }
                i++;
                
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

    private void createScreen() {
        stage = new Stage();
        //Skin skin = new Skin();
        
        float labelheight = Config.HEIGHT / 15f;
        float labelwidth = 2f * labelheight;
        float xoffset = Math.max((Config.WIDTH - 3f * labelwidth) / 2f, 0);
        
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        
        int i = 0;
        for (HighScoreItem hsi : highscores) {
            if (i >= 10) {
                break;
            }
            i++;
            
            float yoffset = Config.HEIGHT - i * labelheight;
            
            Label name = new Label(hsi.name, labelStyle);
            name.setPosition(xoffset, yoffset);
            stage.addActor(name);
            Label date = new Label(hsi.name, labelStyle);
            date.setPosition(xoffset + labelwidth, yoffset);
            stage.addActor(date);
            Label score = new Label(hsi.name, labelStyle);
            score.setPosition(xoffset + 2 * labelwidth, yoffset);
            stage.addActor(score);
        }
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
