package com.group66.game.screencontrollers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.MainMenuScreen;

/**
 * The Class YouLoseRandomController.
 */
public class YouLoseRandomController extends AbstractMenuController {

    /**
     * Instantiates a new you lose random controller.
     *
     * @param screen the screen
     */
    public YouLoseRandomController(Screen screen) {
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
    public void performUserAction(ExitButton action) {
        super.screen.dispose();
        BustaMove.getGameInstance().log(MessageType.Default, "Exit the game");
        Gdx.app.exit();
    }

}
