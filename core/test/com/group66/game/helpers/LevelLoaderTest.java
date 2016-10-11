package com.group66.game.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.group66.game.cannon.BallManager;

public class LevelLoaderTest {
    
    @Test(expected=NullPointerException.class)
    public void nullTest() {
        LevelLoader.loadLevel(null, false);
    }
    
    @Test
    public void loadFileTest() {
        BallManager ballManager = new BallManager();
        LevelLoader.loadLevel(ballManager, false);
        
        assertTrue(ballManager.getBallCount() > 0);
    }
    
    @Test
    public void generateTest() {
        BallManager ballManager = new BallManager();
        LevelLoader.generateLevel(ballManager, false);
        
        assertTrue(ballManager.getBallCount() > 0);
    }
}
