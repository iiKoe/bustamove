package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayNextLevelButton;
import com.group66.game.screencontrollers.actions.ShopButton;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.screens.OnePlayerGameScreen;
import com.group66.game.screens.ShopScreen;

/**
 * The Class YouWinCareerController.
 */
public class YouWinCareerController extends AbstractMenuController {

    /**
     * Instantiates a new you win career controller.
     *
     * @param screen the screen
     */
    public YouWinCareerController(Screen screen) {
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
    public void performUserAction(PlayNextLevelButton action) {
        if (action != null) {
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(ShopButton action) {
        if (action != null) {
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new ShopScreen(screen));
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
