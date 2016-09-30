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
        int numRows;
        if (difficulty == "medium") {
            MediumRowsGenerator generationBehaviour = new MediumRowsGenerator();
            numRows = generationBehaviour.numRowsGenerator();
        } else if (difficulty == "hard") {
            HardRowsGenerator generationBehaviour = new HardRowsGenerator();
            numRows = generationBehaviour.numRowsGenerator();
        }
        else {
            EasyRowsGenerator generationBehaviour = new EasyRowsGenerator();
            numRows = generationBehaviour.numRowsGenerator();
        }
        return numRows;
    }
    
    
    /**
     * Sets the difficulty.
     *
     * @param difficultyLevel the new difficulty
     */
    public void setDifficulty(String difficultyLevel) {
        difficulty = difficultyLevel;
    }
    
    public interface IRowGenerationBehaviour {
        public int numRowsGenerator();
    }
    
    public class EasyRowsGenerator implements IRowGenerationBehaviour {
        public int numRowsGenerator() {
            int lowBar = 3;
            int highBar = 5;
            return lowBar + (int)(Math.random() * (highBar - lowBar + 1));
        }
    }
        
    public class MediumRowsGenerator implements IRowGenerationBehaviour {
        public int numRowsGenerator() {
            int lowBar = 6;
            int highBar = 8;
            return lowBar + (int)(Math.random() * (highBar - lowBar + 1));
        }
    }
    public class HardRowsGenerator implements IRowGenerationBehaviour {
        public int numRowsGenerator() {
            int lowBar = 9;
            int highBar = 11;
            return lowBar + (int)(Math.random() * (highBar - lowBar + 1));
        }
    }
        
}
