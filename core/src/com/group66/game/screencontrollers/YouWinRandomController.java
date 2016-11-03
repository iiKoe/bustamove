package com.group66.game.screencontrollers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.MainMenuScreen;

/**
 * The Class YouWinRandomController.
 */
public class YouWinRandomController extends AbstractMenuController {

    /**
     * Instantiates a new you win random controller.
     *
     * @param screen the screen
     */
    public YouWinRandomController(Screen screen) {
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
