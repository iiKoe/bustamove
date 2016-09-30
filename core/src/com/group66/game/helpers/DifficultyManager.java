package com.group66.game.helpers;


/**
 * The Class DifficultyManager.
 */
public class DifficultyManager {
    
    /** The difficulty. */
    public static String difficulty;
    
    /**
     * Instantiates a new difficulty manager.
     */
    public DifficultyManager() {
    }
    
    /**
     * Num rows.
     *
     * @return the int
     */
    public int numRows() {
        int lowBar = 3;
        int highBar = 5;
        if (difficulty == "easy") {
            lowBar = 3;
            highBar = 5;
        } else if (difficulty == "medium") {
            lowBar = 6;
            highBar = 8;
        } else if (difficulty == "hard") {
            lowBar = 9;
            highBar = 10;       
        }
        return lowBar + (int)(Math.random() * (highBar - lowBar + 1));
    }
    
    
    /**
     * Sets the difficulty.
     *
     * @param difficultyLevel the new difficulty
     */
    public void setDifficulty(String difficultyLevel) {
        difficulty = difficultyLevel;
    }
    
}
