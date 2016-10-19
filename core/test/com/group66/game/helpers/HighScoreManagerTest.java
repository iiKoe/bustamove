package com.group66.game.helpers;

import static org.junit.Assert.assertNotEquals;

import java.util.TreeSet;

import org.junit.Test;


public class HighScoreManagerTest {

    @Test
    public void creationTest() {
        new HighScoreManager();
    }
    
    @Test
    public void testLoading() {
        HighScoreManager highScoreManager = new HighScoreManager();
        TreeSet<HighScoreItem> highscores = highScoreManager.getHighScores();
        
        assertNotEquals(0, highscores.size());
    }
}
