package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.helpers.DifficultyManager;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.screens.MainMenuScreen;

/**
 * The Class SettingsMenuController.
 */
public class SettingsMenuController extends AbstractMenuController {

    /** The difficulty manager. */
    private DifficultyManager difficultyManager;
    
    /**
     * Instantiates a new settings menu controller.
     *
     * @param screen the screen
     */
    public SettingsMenuController(Screen screen) {
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
            DifficultyManager.setDifficulty(action.getDifficulty());
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
     * Gets the difficulty manager.
     *
     * @return the difficulty manager
     */
    public DifficultyManager getDifficultyManager() {
        return difficultyManager;
    }

}
