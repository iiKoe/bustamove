package com.group66.game.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DifficultyManagerTest {
    
    @Test
    public void creationTest() {
        new DifficultyManager();
    }
    
    @Test
    public void difficultyTest() {
        DifficultyManager difficulty = new DifficultyManager();
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
        
        difficulty.setDifficulty("easy");
        rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
        
        difficulty.setDifficulty("medium");
        rows = difficulty.numRows();
        assertTrue(6 <= rows && rows <= 8);
        
        difficulty.setDifficulty("hard");
        rows = difficulty.numRows();
        assertTrue(9 <= rows && rows <= 11);
        
        difficulty.setDifficulty("easy");
        rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
    @Test
    public void nullTest() {
        DifficultyManager difficulty = new DifficultyManager();
        difficulty.setDifficulty(null);
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
    @Test
    public void boundaryTest() {
        DifficultyManager difficulty = new DifficultyManager();
        difficulty.setDifficulty("Meaningless string");
        
        int rows = difficulty.numRows();
        assertTrue(3 <= rows && rows <= 5);
    }
    
}
