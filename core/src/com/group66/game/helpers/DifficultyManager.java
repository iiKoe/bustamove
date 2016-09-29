package com.group66.game.helpers;


// TODO: Auto-generated Javadoc
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
        difficulty = "easy";
    }
    
    /**
     * Num rows.
     *
     * @return the int
     */
    public static int numRows() {
        int highBar = 3;
        int lowBar = 5;
        
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
    public static void setDifficulty(String difficultyLevel) {
        difficulty = difficultyLevel;
    }
    
}
