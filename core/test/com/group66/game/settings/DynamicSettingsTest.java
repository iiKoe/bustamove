package com.group66.game.settings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.group66.game.BustaMove;

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
        dynamicSettings.setName(null, true);
    }

    @Test
    public void lifeTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setExtraLife(true, false);
        dynamicSettings.setExtraLife(true, true);
    }

    @Test
    public void levelTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setCurrentLevel(1000, false);
        dynamicSettings.setCurrentLevel(2, true);
        dynamicSettings.setCurrentLevel(2, false);
        
        dynamicSettings.setLevelCleared(0, false);
        dynamicSettings.setLevelCleared(1, true);
        dynamicSettings.setLevelCleared(6, false);
        dynamicSettings.setRandomLevel(true, false);
        dynamicSettings.setRandomLevel(true, true);
        
        dynamicSettings.setLevelHighscore(1000, 1);
        dynamicSettings.setLevelHighscore(1, 10);
        dynamicSettings.setLevelHighscore(1, 1);
        
        assertEquals(0, dynamicSettings.getLevelHighscore(1000));
        assertEquals(10, dynamicSettings.getLevelHighscore(1));
    }
    
    @Test
    public void variousTests() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.addCurrency(0, false);
        dynamicSettings.addCurrency(0, true);
        
        dynamicSettings.setCurrency(100, true);
        dynamicSettings.setCurrency(100, false);
        
        dynamicSettings.setBallSpeedMultiplier(1.0f, true);
        dynamicSettings.setBallSpeedMultiplier(1.0f, false);
        
        dynamicSettings.setScoreMultiplier(1.0f, true);
        dynamicSettings.setScoreMultiplier(1.0f, false);    
        
        dynamicSettings.setSpecialBombChanceMultiplier(1.0f, true);
        dynamicSettings.setSpecialBombChanceMultiplier(1.0f, false);
    }
}
