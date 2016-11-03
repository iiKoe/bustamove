
package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.SettingsMenuController;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.settings.Config;

/**
 * A Class for the SettingsScreen of the game.
 */
public class SettingsScreen extends AbstractMenuScreen {

    SettingsMenuController controller; 
    /** sets up button */
    private TextButton easyButton;
    private TextButton mediumButton;
    private TextButton hardButton;
    private TextButton menuButton;
    
    /**
     * Instantiates a new main menu screen.
     */
    public SettingsScreen() {
        controller = new SettingsMenuController(this);
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the settings screen");
    }

    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);   
        setupButtons();
        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
        stage.addActor(menuButton);
    }

    /*
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        /* Draw the background */
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        BustaMove.getGameInstance().batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        BustaMove.getGameInstance().batch.end();
        
        stage.act();
        stage.draw();
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 75;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;
        
        easyButton = new TextButton("Easy", textButtonStyle);
        easyButton.setPosition(centercol - Config.BUTTON_WIDTH - Config.BUTTON_SPACING, yoffset);
        
        mediumButton = new TextButton("Medium", textButtonStyle);
        mediumButton.setPosition(centercol, yoffset);
        
        hardButton = new TextButton("Hard!", textButtonStyle);
        hardButton.setPosition(centercol + Config.BUTTON_WIDTH + Config.BUTTON_SPACING, yoffset);

        menuButton = new TextButton("Menu", textButtonStyle);
        menuButton.setPosition(centercol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);
     
        // Add a listener to the button. ChangeListener is fired when the
        // button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the
        // event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't
        // revert the checked state.
        easyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("easy"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to easy");
            }
        });
        mediumButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("medium"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to medium");
            }
        });
        hardButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("hard"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to hard");
            }
        });
        menuButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new MainMenuButton());
            }
        });

    }
}