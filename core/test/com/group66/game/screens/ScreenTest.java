package com.group66.game.screens;

import org.junit.Test;

import com.badlogic.gdx.Screen;

public abstract class ScreenTest {
    public abstract Screen getScreen();
    
    @Test
    public void functionTest() {
        Screen screen = getScreen();
        screen.show();
        screen.resize(200, 100);
        screen.pause();
        screen.resume();
        screen.hide();
        screen.dispose();
    }
    
    @Test
    public void renderTest() {
        Screen screen = getScreen();
        screen.render(1);
    }
}
