package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.HighScoreItem;
import com.group66.game.screencontrollers.HighScoreMenuController;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.settings.Config;

public class HighScoreScreen extends AbstractMenuScreen {
    
    /** The controller. */
    private HighScoreMenuController controller;
    
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
        controller = new HighScoreMenuController(this);
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
        
        LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.BLACK);
       
        //create main highscore list
        int count = 0;
        for (HighScoreItem hsi : BustaMove.getGameInstance().getHighScoreManager().getHighScores()) {
            if (count >= 10) {
                break;
            }
            count++;
            
            /* Generating labels in a specific position */
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Draw the background */
        SpriteBatch batch = BustaMove.getGameInstance().batch;
        batch.begin();
        batch.enableBlending();
        batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //back button listener
        backButton = new TextButton("Back", textButtonStyle);
        backButton.setPosition(xoffset + labelwidth, labelheight);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new MainMenuButton());
            }
        });
    }
}
