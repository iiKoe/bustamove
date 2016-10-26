package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;

public class YouWinScreenTest extends ScreenTest {
    
    @Test
    public void creationTest() {
        //new YouWinScreen();
    }

    @Override
    public Screen getScreen() {
        return new YouWinScreenCareer();
    }
}
