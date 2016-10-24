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
        dynamicSettings.setName("Name");
    }

    @Test
    public void lifeTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setExtraLife(true);
    }

    @Test
    public void levelTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setCurrentLevel(1000);
        dynamicSettings.setCurrentLevel(2);
        
        dynamicSettings.setLevelCleared(0);
        dynamicSettings.setLevelCleared(1);
        dynamicSettings.setRandomLevel(true);
        
        dynamicSettings.setLevelHighscore(1000, 1);
        dynamicSettings.setLevelHighscore(1, 1);
        
        assertEquals(0, dynamicSettings.getLevelHighscore(1000));
        assertEquals(1, dynamicSettings.getLevelHighscore(1));
    }
}
