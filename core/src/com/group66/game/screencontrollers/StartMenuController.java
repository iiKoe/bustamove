package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.group66.game.BustaMove;
import com.group66.game.input.InputHandler;
import com.group66.game.screencontrollers.actions.StartGameButton;
import com.group66.game.screens.MainMenuScreen;

/**
 * The Class StartMenuController.
 */
public class StartMenuController extends AbstractMenuController {

    /**
     * Instantiates a new start menu controller.
     *
     * @param screen the screen
     */
    public StartMenuController(Screen screen) {
        super(screen);
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(StartGameButton action) {
        if (action != null) {
            BustaMove.getGameInstance().getDynamicSettings().setName(action.getName(), false);
            BustaMove.getGameInstance().getProfileManager().readData(
                    BustaMove.getGameInstance().getDynamicSettings().getName(), 
                    BustaMove.getGameInstance().getDynamicSettings());
            screen.dispose();
            BustaMove.getGameInstance().setScreen(new MainMenuScreen());
        }
    }
    
    /* 
     * @see com.group66.game.screencontrollers.AbstractMenuController#setupKeys()
     */
    @Override
    public void setupKeys() {
// Setup the game keys
        
        inputHandler.registerKeyMap("Toggle mute", Keys.M);


        inputHandler.registerKeyJustPressedFunc("Toggle mute",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        BustaMove.getGameInstance().getAudioManager().toggleMute();
                    }
            });        
    }
    
    

}
