package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.helpers.DifficultyManager;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.RandomButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.screens.ThreePlayerGameScreen;
import com.group66.game.screens.TwoPlayerGameScreen;

/**
 * The Class MultiplayerMenuController.
 */
public class MultiplayerMenuController extends AbstractMenuController {

    /** The difficulty manager. */
    DifficultyManager difficultyManager;
    
    /**
     * Instantiates a new multiplayer menu controller.
     *
     * @param screen the screen
     */
    public MultiplayerMenuController(Screen screen) {
        super(screen); 
        difficultyManager = new DifficultyManager();
    }

    /* (non-Javadoc)
     * @see com.group66.game.screencontrollers.AbstractMenuController#setupKeys()
     */
    @Override
    public void setupKeys() { }

    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(SetDifficultyButton action) {
        if (action != null) {
            difficultyManager.setDifficulty(action.getDifficulty());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(RandomButton action) {
        if (action != null) {
            screen.dispose();
            if (action.getNumberOfPlayers() == 2) {
                BustaMove.getGameInstance().setScreen(new TwoPlayerGameScreen(true));
            } else {
                BustaMove.getGameInstance().setScreen(new ThreePlayerGameScreen(true));
            }
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(MainMenuButton action) {
        if (action != null) {
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new MainMenuScreen());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(PlayLevelButton action) {
        if (action != null) {
            screen.dispose();
            if (action.getNumberOfPlayers() == 2) {
                BustaMove.getGameInstance().setScreen(new TwoPlayerGameScreen(action.getLevel()));
            } else {
                BustaMove.getGameInstance().setScreen(new ThreePlayerGameScreen(action.getLevel()));
            }
        }
    }
}
