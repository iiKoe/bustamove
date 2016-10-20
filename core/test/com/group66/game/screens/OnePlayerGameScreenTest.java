package com.group66.game.screens;

import org.junit.Test;
import com.group66.game.settings.DynamicSettings;

public class OnePlayerGameScreenTest extends AbstractGameScreenTest {
    
    @Test
    public void creationTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        new OnePlayerGameScreen(dynamicSettings);
    }
    
    protected AbstractGameScreen getGameScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new OnePlayerGameScreen(dynamicSettings); 
    }
}
