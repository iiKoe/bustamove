/**
 * 
 */
package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.input.InputHandler;
import com.group66.game.screencontrollers.actions.UserAction;

public abstract class AbstractMenuController {

    /* The instance of the screen it controls. */
    protected Screen screen;
    
    /** The input handler. */
    protected InputHandler inputHandler;
    
    /**
     * 
     */
    public AbstractMenuController(Screen screen) {
        this.screen = screen;
        inputHandler = new InputHandler();
        setupKeys();
    }
    
    public void performUserAction(UserAction action) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Setup keys.
     */
    public abstract void setupKeys();
    
    /**
     * Run input handler.
     */
    public void runInputHandler() {
        inputHandler.run();
    }

}
