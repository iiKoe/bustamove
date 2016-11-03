package com.group66.game.screencontrollers.actions;

public class StartGameButton implements UserAction {

    /** The name. */
    private String name;
    
    /**
     * Instantiates a new start game button.
     *
     * @param name the name
     */
    public StartGameButton(String name) {
        this.name = name;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
