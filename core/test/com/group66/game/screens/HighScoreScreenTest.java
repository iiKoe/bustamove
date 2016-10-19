package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;

public class HighScoreScreenTest extends ScreenTest {
    @Test
    public void creationTest() {
        new HighScoreScreen();
    }

    @Override
    public Screen getScreen() {
        return new HighScoreScreen();
    }
}
