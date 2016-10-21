package com.group66.game.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.group66.game.cannon.BallManager;
import com.group66.game.settings.DynamicSettings;

public class LevelLoaderTest {
    @Test
    public void nullTest() {
        LevelLoader.loadLevel(null, false);
    }
    
    @Test
    public void loadFileTest() {
        /*
        DynamicSettings dynamicSettings = new DynamicSettings();
        BallManager ballManager = new BallManager(dynamicSettings);
        LevelLoader.loadLevel(ballManager, false);
        
        assertTrue(ballManager.getBallCount() > 0);
        */
    }
    
    @Test
    public void generateTest() {
        /*
        DynamicSettings dynamicSettings = new DynamicSettings();
        BallManager ballManager = new BallManager(dynamicSettings);
        LevelLoader.generateLevel(ballManager, false);
        
        assertTrue(ballManager.getBallCount() > 0);
        */
    }
}
