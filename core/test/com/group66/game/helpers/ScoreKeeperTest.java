package com.group66.game.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScoreKeeperTest {

    /**
     * Test to see if the object can be created
     */
    @Test
    public void creationTest() {
        new ScoreKeeper();
    }
    
    /**
     * Test the simple score count
     */
    @Test
    public void simpleScoreTest() {
        ScoreKeeper sk = new ScoreKeeper();
        assertEquals(0, sk.getCurrentScore());
        
        sk.addCurrentScore(2, 0, 1);
        assertEquals(30, sk.getCurrentScore());
        
        sk.addCurrentScore(8, 0, 1);
        assertEquals(120, sk.getCurrentScore());
    }
    
    /**
     * Test the advanced score count
     */
    @Test
    public void advancedScoreTest() {
        ScoreKeeper sk = new ScoreKeeper();
        assertEquals(0, sk.getCurrentScore());
        
        sk.addCurrentScore(5, 3, 1);
        //5*10 + (2^3)*10 = 50 + 80 = 130
        assertEquals(130, sk.getCurrentScore());
        
        sk.doubleCurrentScore();
        assertEquals(260, sk.getCurrentScore());
        
        sk.addCurrentScore(4, 1, 1);
        //4*10 + (2^1)*10 = 40 + 20 = 60
        assertEquals(320, sk.getCurrentScore());
    }
    
    /**
     * Test for negative values
     */
    @Test
    public void boundaryTest() {
        ScoreKeeper sk = new ScoreKeeper();
        assertEquals(0, sk.getCurrentScore());
        
        sk.addCurrentScore(-2, -1, 1);
        //-2*10 + (2^-1)*10 = -20 + 5 = -15
        assertEquals(-15, sk.getCurrentScore());
    }
    
}
