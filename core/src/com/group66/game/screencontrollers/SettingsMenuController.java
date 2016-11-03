package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.helpers.DifficultyManager;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.screens.MainMenuScreen;

public class SettingsMenuController extends AbstractMenuController {

    private DifficultyManager difficultyManager;
    
    public SettingsMenuController(Screen screen) {
        super(screen);
        difficultyManager = new DifficultyManager();
    }

    @Override
    public void setupKeys() {
        // TODO Auto-generated method stub

    }
    
    public void performUserAction(SetDifficultyButton action) {
        if (action != null) {
            difficultyManager.setDifficulty(action.getDifficulty());
        }
    }
    
    public void performUserAction(MainMenuButton action) {
        if (action != null) {
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new MainMenuScreen());
        }
    }

    public DifficultyManager getDifficultyManager() {
        return difficultyManager;
    }

}
