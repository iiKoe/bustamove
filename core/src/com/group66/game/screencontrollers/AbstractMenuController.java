/**
 * 
 */
package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.input.InputHandler;
import com.group66.game.screencontrollers.actions.UserAction;

/**
 * The Class AbstractMenuController.
 */
public abstract class AbstractMenuController {

    /* The instance of the screen it controls. */
    protected Screen screen;
    
    /** The input handler. */
    protected InputHandler inputHandler;
    
    /**
     * Instantiates a new abstract menu controller.
     *
     * @param screen the screen
     */
    public AbstractMenuController(Screen screen) {
        this.screen = screen;
        inputHandler = new InputHandler();
        setupKeys();
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(UserAction action) {
        if (action != null) {
            throw new UnsupportedOperationException();
        }
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
