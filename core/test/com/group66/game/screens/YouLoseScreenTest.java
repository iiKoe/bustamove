package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;

public class YouLoseScreenTest extends ScreenTest {
    @Test
    public void creationTest() {
        new YouLoseScreenRandom();
    }

    @Override
    public Screen getScreen() {
        return new YouLoseScreenCareer();
    }
}
