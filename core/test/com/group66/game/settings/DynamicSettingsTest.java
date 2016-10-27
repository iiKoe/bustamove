package com.group66.game.settings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DynamicSettingsTest {
    @Test
    public void creationTest() {
        new DynamicSettings();
    }
    
    @Test
    public void resetTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.reset();
    }
    
    @Test
    public void nameTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setName("Name", false);
    }

    @Test
    public void lifeTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setExtraLife(true, false);
    }

    @Test
    public void levelTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setCurrentLevel(1000, false);
        dynamicSettings.setCurrentLevel(2, false);
        
        dynamicSettings.setLevelCleared(0, false);
        dynamicSettings.setLevelCleared(1, false);
        dynamicSettings.setRandomLevel(true, false);
        
        dynamicSettings.setLevelHighscore(1000, 1);
        dynamicSettings.setLevelHighscore(1, 1);
        
        assertEquals(0, dynamicSettings.getLevelHighscore(1000));
        assertEquals(1, dynamicSettings.getLevelHighscore(1));
    }
}
