package com.group66.game.screens;

import org.junit.Test;

import com.group66.game.settings.DynamicSettings;

public class TwoPlayerGameScreenTest extends AbstractGameScreenTest {
    /*
    @Test
    public void creationTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        new TwoPlayerGameScreen(dynamicSettings);
    }
    */
    protected AbstractGameScreen getGameScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new TwoPlayerGameScreen(dynamicSettings);
    }
    
}
