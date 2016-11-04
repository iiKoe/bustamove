package com.group66.game.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DifficultyManagerTest {
    
    /**
     * Test to see if the object can be created
     */
    @Test
    public void creationTest() {
        new DifficultyManager();
    }
    
    /**
     * Test for switching difficulties
     */
    @Test
    public void difficultyTest() {
        DifficultyManager difficulty = new DifficultyManager();
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
        
        DifficultyManager.setDifficulty("easy");
        rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
        
        DifficultyManager.setDifficulty("medium");
        rows = difficulty.numRows();
        assertTrue(6 <= rows && rows <= 8);
        
        DifficultyManager.setDifficulty("hard");
        rows = difficulty.numRows();
        assertTrue(9 <= rows && rows <= 11);
        
        DifficultyManager.setDifficulty("easy");
        rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
    /**
     * Test for null value
     */
    @Test
    public void nullTest() {
        DifficultyManager difficulty = new DifficultyManager();
        DifficultyManager.setDifficulty(null);
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
    /**
     * Test for undefined value
     */
    @Test
    public void boundaryTest() {
        DifficultyManager difficulty = new DifficultyManager();
        DifficultyManager.setDifficulty("Meaningless string");
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
}
