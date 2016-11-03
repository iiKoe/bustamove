package com.group66.game.screencontrollers.actions;

/**
 * The Class RandomButton.
 */
public class RandomButton implements UserAction {

    /** The number of players. */
    private int numberOfPlayers;
    /**
     * Instantiates a new random button.
     */
    public RandomButton() {
        this.numberOfPlayers = 0;
    }
    
    /**
     * Instantiates a new random button.
     *
     * @param numberOfPlayers the number of players
     */
    public RandomButton(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
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
