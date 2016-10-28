package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;

public class OnePlayerGameScreenTest extends AbstractGameScreenTest {
    
    @Test
    public void creationTest() {
        //DynamicSettings dynamicSettings = new DynamicSettings();
        //new OnePlayerGameScreen(dynamicSettings);
    }

    @Override
    public Screen getScreen() {
        return new OnePlayerGameScreen();
    }
    
}
