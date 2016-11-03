package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.MainMenuScreen;

public class HighScoreMenuController extends AbstractMenuController {

    /**
     * Instantiates a new high score menu controller.
     *
     * @param screen the screen
     */
    public HighScoreMenuController(Screen screen) {
        super(screen);
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
    public void performUserAction(MainMenuButton action) {
        screen.dispose();
        BustaMove.getGameInstance().setScreen(new MainMenuScreen());
    }
}
