package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;
import com.group66.game.settings.DynamicSettings;

public class MainMenuScreenTest extends ScreenTest {
    @Test
    public void creationTest() {
        //new MainMenuScreen();
    }

    @Override
    public Screen getScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new MainMenuScreen(dynamicSettings);
    }
}
