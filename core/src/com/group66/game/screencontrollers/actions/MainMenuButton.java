package com.group66.game.screencontrollers.actions;

import com.badlogic.gdx.Screen;

/**
 * The Class MainMenuButton.
 */
public class MainMenuButton implements UserAction {

    /** The origin. */
    Screen origin;
    /**
     * Instantiates a new main menu button.
     */
    public MainMenuButton() {
        origin = null;
    }
    
    /**
     * Instantiates a new main menu button.
     *
     * @param origin the origin
     */
    public MainMenuButton(Screen origin) {
        this.origin = origin;
    }

    /**
     * Gets the origin.
     *
     * @return the origin
     */
    public Screen getOrigin() {
        return origin;
    }
    

}
