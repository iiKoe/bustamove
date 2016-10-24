package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.HighScoreItem;
import com.group66.game.settings.Config;

public class HighScoreScreen extends AbstractMenuScreen {
    /** sets up buttons */
    private TextButton backButton;
    
    /** variables to calculate label locations */
    private float labelheight;
    private float labelwidth;
    private float xoffset;
    private float yoffset;
    
    /**
     * Constructor for the high score screen
     */
    public HighScoreScreen() {
        createScreen();
    }

    /**
     * Create the stage for the highscore screen
     */
    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        labelheight = Config.HEIGHT / 13f;
        labelwidth = 2f * labelheight;
        xoffset = Math.max((Config.WIDTH - 3f * labelwidth) / 2f, 0);
        
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        
        //create main highscore list
        int count = 0;
        for (HighScoreItem hsi : BustaMove.getGameInstance().getHighScoreManager().getHighScores()) {
            if (count >= 10) {
                break;
            }
            count++;
            
            yoffset = Config.HEIGHT - count * labelheight;
            
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
        setupButtons();
        stage.addActor(backButton);
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
    public void setupButtons() {
        loadButtonMaterials();
        //back button listener
        backButton = new TextButton("Back", textButtonStyle);
        backButton.setPosition(xoffset + labelwidth, labelheight);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                BustaMove.getGameInstance().setScreen(new MainMenuScreen());
            }
        });
    }
}
