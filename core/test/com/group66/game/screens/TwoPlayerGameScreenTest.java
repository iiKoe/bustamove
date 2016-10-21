package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;
import com.group66.game.settings.DynamicSettings;

public class TwoPlayerGameScreenTest extends AbstractGameScreenTest {
    
    @Test
    public void creationTest() {
        //DynamicSettings dynamicSettings = new DynamicSettings();
        //new TwoPlayerGameScreen(dynamicSettings);
    }

    @Override
    public Screen getScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new TwoPlayerGameScreen(dynamicSettings);
    }
    
}
