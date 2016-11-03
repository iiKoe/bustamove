package com.group66.game.screencontrollers.actions;

public class PlayLevelButton implements UserAction {

    /** The level. */
    private int level;
    
    /**
     * Instantiates a new play level button.
     *
     * @param level the level
     */
    public PlayLevelButton(int level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

}
