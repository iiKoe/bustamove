package com.group66.game.helpers;

import static org.junit.Assert.*;
import org.junit.Test;

public class HighScoreManagerTest {
    
    @Test
    public void testLoading() {
        assertEquals(0, HighScoreManager.highscores.size());
        HighScoreManager.loadData();
        assertNotEquals(0, HighScoreManager.highscores.size());
    }
}
