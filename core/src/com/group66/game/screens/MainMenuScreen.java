package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;

/**
 * A Class for the MainMenuScreen of the game.
 */
public class MainMenuScreen extends AbstractMenuScreen {  

    /** The own instance. */
    private Screen ownInstance;
    
    /**  screen buttons. */
    private TextButton levelButton;
    
    /** The random button. */
    private TextButton randomButton;
    
    /** The scores button. */
    private TextButton scoresButton;
    
    /** The exit button. */
    private TextButton exitButton;
    
    /** The settings button. */
    private TextButton settingsButton;
    
    /** The split button. */
    private TextButton splitButton;

    /**
     * Instantiates a new main menu screen.
     */
    public MainMenuScreen() {
        ownInstance = this;
        System.out.println("now start create main menu screen");
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the main menu screen");
    }
    

    /**
     * Creates the screen.
     */
    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        setupButtons();       
        stage.addActor(levelButton);
        stage.addActor(randomButton);
        stage.addActor(scoresButton);
        stage.addActor(exitButton);
        stage.addActor(settingsButton);
        stage.addActor(splitButton);
    }

    /**
     * renders the screen
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        /* Draw the background */
        SpriteBatch batch = BustaMove.getGameInstance().getBatch();
        batch.begin();
        batch.enableBlending();
        batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        batch.end();
        
        stage.act();
        stage.draw();
    }

    /** 
     * sets up screen buttons
     */
    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + 40;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;
        
        levelButton = new TextButton("Career", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);
        
        int leftcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH - 250) / 2;
        randomButton = new TextButton("Play: Random Level", textButtonStyle);
        randomButton.setPosition(leftcol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);

        int rightcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH + 250) / 2;
        splitButton = new TextButton("Play: Split screen", textButtonStyle);
        splitButton.setPosition(rightcol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);
        
        scoresButton = new TextButton("High scores", textButtonStyle);
        scoresButton.setPosition(leftcol, yoffset - 2 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        settingsButton = new TextButton("Settings", textButtonStyle);
        settingsButton.setPosition(rightcol, yoffset - 2 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        exitButton = new TextButton("Exit", textButtonStyle);
        exitButton.setPosition(centercol, yoffset - 3 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when
        // clicked, Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the
        // checked state will be reverted. ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't revert the checked state.
        levelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new CareerScreen());
            }
        });
        randomButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().getDynamicSettings().setRandomLevel(true, true);
                BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(true));
            }
        });
        scoresButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new HighScoreScreen());
            }
        });
        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().log(MessageType.Default, "Exit the game");
                Gdx.app.exit();
            }
        });
        settingsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new SettingsScreen());
            }
        });
        splitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                //BustaMove.getGameInstance().setScreen(new TwoPlayerGameScreen(true, dynamicSettings));
                BustaMove.getGameInstance().setScreen(new MultiplayerMenuScreen());
            }
        });
    }
}
