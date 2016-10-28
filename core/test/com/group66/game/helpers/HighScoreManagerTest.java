package com.group66.game.helpers;

import static org.junit.Assert.assertNotEquals;

import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
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
    
    @Test
    public void testWrite() {
        HighScoreManager highScoreManager = new HighScoreManager();
        
        highScoreManager.addScore(5);
        highScoreManager.writeData();
    }
}
