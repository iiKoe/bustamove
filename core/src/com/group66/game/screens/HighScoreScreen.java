package com.group66.game.screens;

import java.util.Date;
import java.util.TreeSet;

import org.apache.commons.lang.time.DateFormatUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.HighScoreItem;
import com.group66.game.settings.Config;

public class HighScoreScreen implements Screen {
    public static TreeSet<HighScoreItem> highscores = new TreeSet<HighScoreItem>();
    
    private BustaMove game;
    private FileHandle file;
    private Stage stage;
    
    public HighScoreScreen(BustaMove game) {
        this.game = game;
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
        //write high scores to file
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

    public void addScore(int score) {
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        highscores.add(new HighScoreItem("unknown", date, score));
        writeData();
    }
    
    private void createScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        float labelheight = Config.HEIGHT / 13f;
        float labelwidth = 2f * labelheight;
        float xoffset = Math.max((Config.WIDTH - 3f * labelwidth) / 2f, 0);
        
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        
        //create main highscore list
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
            Label date = new Label(hsi.date, labelStyle);
            date.setPosition(xoffset + labelwidth, yoffset);
            stage.addActor(date);
            Label score = new Label("" + hsi.score, labelStyle);
            score.setPosition(xoffset + 2 * labelwidth, yoffset);
            stage.addActor(score);
        }
        
        //create skin for back button
        Skin skin = new Skin();
        skin.add("default", new BitmapFont());
        
        Pixmap pixmap = new Pixmap((int) labelwidth, (int) labelheight, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        
        //back button style
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.up = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.BLACK);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        skin.add("default", textButtonStyle);
        
        //back button
        TextButton backButton = new TextButton("Back", textButtonStyle);
        backButton.setPosition(xoffset + labelwidth, labelheight);
        stage.addActor(backButton);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
    
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
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
