package com.group66.game.screens;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public abstract class ScreenTest {
    public abstract Screen getScreen();
    
    @Test
    public void gdxFileExistsTest() {
        assertTrue(Gdx.files.internal("roof.png").exists());
    }
    
    /*
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
    */
    
    /*
    @Test
    public void renderTest() {
        Screen screen = getScreen();
        screen.render(1);
    }
    */
}
