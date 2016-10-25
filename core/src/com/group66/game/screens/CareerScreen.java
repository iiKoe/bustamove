package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.input.LevelSelectInputListener;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * A Class for the MainMenuScreen of the game.
 */
public class CareerScreen extends AbstractMenuScreen {
    private static DynamicSettings dynamicSettings = new DynamicSettings();

    /** The text drawer. */
    private TextDrawer textDrawer;

    private Screen ownInstance;
    
    /** screen buttons */
    private TextButton levelButton;
    private TextButton chooseButton;
    private TextButton approveButton;
    private TextButton resetButton;
    private TextButton mainMenuButton;
    private TextButton shopButton;
    
    /** variables used to calculate some drawing coordinates */
    private int yoffset;
    private int centercol;
    private int leftcol;
    private int rightcol;

    /**
     * Instantiates a new main menu screen.
     */
    public CareerScreen() {
        BustaMove.getGameInstance().getHighScoreManager().loadData();
        ownInstance = this;
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the main menu screen");
    }

    /**
     * Creates an instance of careerscreen
     * 
     * @param dynamicSettings
     */
    public CareerScreen(DynamicSettings dynamicSettings) {
        this();
    }

    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // Setup the text drawer to show the amount of coins
        textDrawer = new TextDrawer();
        textDrawer.myFont.setColor(Color.BLACK);
        setupButtons();
        stage.addActor(levelButton);
        stage.addActor(chooseButton);
        stage.addActor(approveButton);
        stage.addActor(resetButton);
        stage.addActor(mainMenuButton);
        stage.addActor(shopButton);       
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        chooseButton.setText("Choose level: " + dynamicSettings.getCurrentLevel());

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Draw the background */
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        BustaMove.getGameInstance().batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        textDrawer.draw(BustaMove.getGameInstance().batch, "You have cleared " 
                + dynamicSettings.getLevelCleared() + " out of " + Config.NUMBER_OF_LEVELS + " levels!", 
                Config.WIDTH / 2 - Config.LEVEL_WIDTH / 2 + Config.CURRENCY_X - 100, Config.CURRENCY_Y - 50);
        BustaMove.getGameInstance().batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks
        yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        centercol = (int) ((Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2);

        levelButton = new TextButton("Play: Level " + (dynamicSettings.getLevelCleared() + 1), 
                textButtonStyle);
        levelButton.setPosition(centercol, yoffset);
        leftcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH - 250) / 2;
        rightcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH + 250) / 2;
        chooseButton = new TextButton("Choose level: ", textButtonStyle);
        chooseButton.setPosition(leftcol, yoffset - 1 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        approveButton = new TextButton("Play", textButtonStyle);
        approveButton.setPosition(rightcol, yoffset - 1 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));

        resetButton = new TextButton("Reset career", textButtonStyle);
        resetButton.setPosition(centercol, yoffset - 2 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));

        shopButton = new TextButton("Shop", textButtonStyle);
        shopButton.setPosition(centercol, yoffset - 3 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));

        mainMenuButton = new TextButton("Main Menu", textButtonStyle);
        mainMenuButton.setPosition(centercol, yoffset - 4 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        // Add a listener to the button. ChangeListener is fired when the
        // button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the
        // event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't
        // revert the checked state.
        levelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                dynamicSettings.setCurrentLevel(dynamicSettings.getLevelCleared() + 1);
                BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(false, dynamicSettings));
            }
        });
        chooseButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                LevelSelectInputListener listener = new LevelSelectInputListener(dynamicSettings);
                Gdx.input.getTextInput(listener, "Choose level", "", "Number between 1 and" 
                        + dynamicSettings.getLevelCleared());
            }
        });
        approveButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(false, dynamicSettings));
            }
        });
        resetButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dynamicSettings.reset();
            }
        });
        mainMenuButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new MainMenuScreen());
            }
        });

        shopButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new ShopScreen(dynamicSettings, ownInstance));
            }
        });
        
    }

}
