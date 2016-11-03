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

public class MultiplayerMenuController extends AbstractMenuController {

    DifficultyManager difficultyManager;
    
    public MultiplayerMenuController(Screen screen) {
        super(screen); 
        difficultyManager = new DifficultyManager();
    }

    @Override
    public void setupKeys() { }

    public void performUserAction(SetDifficultyButton action) {
        if (action != null) {
            difficultyManager.setDifficulty(action.getDifficulty());
        }
    }
    
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
    
    public void performUserAction(MainMenuButton action) {
        if (action != null) {
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new MainMenuScreen());
        }
    }
    
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
