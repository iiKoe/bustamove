package com.group66.game.screencontrollers.actions;

/**
 * The Class PlayLevelButton.
 */
public class PlayLevelButton implements UserAction {

    /** The level. */
    private int level;
    
    /** The number of players. */
    private int numberOfPlayers;
    
    /**
     * Instantiates a new play level button.
     *
     * @param level the level
     */
    public PlayLevelButton(int level) {
        this.level = level;
        this.numberOfPlayers = 1;
    }
    
    /**
     * Instantiates a new play level button.
     *
     * @param level the level
     * @param numberOfPlayers the number of players
     */
    public PlayLevelButton(int level, int numberOfPlayers) {
        this.level = level;
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the number of players.
     *
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

}
