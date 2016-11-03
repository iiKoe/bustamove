package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.TryAgainButton;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.screens.OnePlayerGameScreen;

/**
 * The Class YouLoseCareerController.
 */
public class YouLoseCareerController extends AbstractMenuController {

    /**
     * Instantiates a new you lose career controller.
     *
     * @param screen the screen
     */
    public YouLoseCareerController(Screen screen) {
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
    public void performUserAction(TryAgainButton action) {
        if (action != null) {
            BustaMove.getGameInstance().getDynamicSettings().setExtraLife(false, true);
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen());
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
}
