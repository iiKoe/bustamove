package com.group66.game.screencontrollers.actions;

/**
 * The Class SetDifficultyButton.
 */
public class SetDifficultyButton implements UserAction {

    /** The difficulty. */
    String difficulty;

    /**
     * Instantiates a new sets the difficulty button.
     *
     * @param difficulty the difficulty
     */
    public SetDifficultyButton(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the difficulty.
     *
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }



}
