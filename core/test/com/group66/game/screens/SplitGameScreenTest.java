package com.group66.game.screens;

import org.junit.Test;

import com.group66.game.settings.DynamicSettings;

public class SplitGameScreenTest extends AbstractGameScreenTest {
    /*
    @Test
    public void creationTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        new SplitGameScreen(dynamicSettings);
    }
    */
    protected SplitGameScreen getGameScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new SplitGameScreen(dynamicSettings);
    }
    
}
