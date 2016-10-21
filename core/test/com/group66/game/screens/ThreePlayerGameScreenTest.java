package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;
import com.group66.game.settings.DynamicSettings;

public class ThreePlayerGameScreenTest extends AbstractGameScreenTest {
    
    @Test
    public void creationTest() {
        //DynamicSettings dynamicSettings = new DynamicSettings();
        //new ThreePlayerGameScreen(dynamicSettings);
    }

    @Override
    public Screen getScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new ThreePlayerGameScreen(dynamicSettings);
    }
}
