package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.ResetButton;
import com.group66.game.screencontrollers.actions.ShopButton;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.screens.OnePlayerGameScreen;
import com.group66.game.screens.ShopScreen;

public class CareerMenuController extends AbstractMenuController {

    /**
     * Instantiates a new career menu controller.
     *
     * @param screen the screen
     */
    public CareerMenuController(Screen screen) {
        super(screen);
    }

    /* (non-Javadoc)
     * @see com.group66.game.screencontrollers.AbstractMenuController#setupKeys()
     */
    @Override
    public void setupKeys() { }
    
    public void performUserAction(ShopButton action) {
        screen.dispose();
        BustaMove.getGameInstance().setScreen(new ShopScreen(screen));
    }
    
    public void performUserAction(ResetButton action) {
        BustaMove.getGameInstance().getDynamicSettings().reset();
    }
    
    public void performUserAction(MainMenuButton action) {
        screen.dispose();
        BustaMove.getGameInstance().setScreen(new MainMenuScreen());
    }
    
    public void performUserAction(PlayLevelButton action) {
        if (BustaMove.getGameInstance().getDynamicSettings().getLevelCleared() >= action.getLevel() - 1) {
            screen.dispose();
            BustaMove.getGameInstance().getDynamicSettings().setCurrentLevel(action.getLevel(), false);
            BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(false));
        }
    }

}
