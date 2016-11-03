/**
 * 
 */
package com.group66.game.screencontrollers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.LevelButton;
import com.group66.game.screencontrollers.actions.RandomButton;
import com.group66.game.screencontrollers.actions.ScoresButton;
import com.group66.game.screencontrollers.actions.SettingsButton;
import com.group66.game.screencontrollers.actions.SplitButton;
import com.group66.game.screens.CareerScreen;
import com.group66.game.screens.HighScoreScreen;
import com.group66.game.screens.MultiplayerMenuScreen;
import com.group66.game.screens.OnePlayerGameScreen;
import com.group66.game.screens.SettingsScreen;

/**
 * The Class MainMenuController.
 */
public class MainMenuController extends AbstractMenuController {

   
    /**
     * @param screen
     */
    public MainMenuController(Screen screen) {
        super(screen);
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(LevelButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().setScreen(new CareerScreen());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(RandomButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().getDynamicSettings().setRandomLevel(true, true);
            BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(true));
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(ScoresButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().setScreen(new HighScoreScreen());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(SettingsButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().setScreen(new SettingsScreen());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(ExitButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().log(MessageType.Default, "Exit the game");
            Gdx.app.exit();
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(SplitButton action) {
        if (action != null) {
            super.screen.dispose();
            BustaMove.getGameInstance().setScreen(new MultiplayerMenuScreen());
        }
    }

    /* (non-Javadoc)
     * @see com.group66.game.screencontrollers.AbstractMenuController#setupKeys()
     */
    @Override
    public void setupKeys() { }

}
