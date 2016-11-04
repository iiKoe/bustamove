package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;

public abstract class AbstractMenuControllerTest {
    @Before
    public void setupTests() {
        HeadlessNativesLoader.load();
        MockGraphics mockGraphics = new MockGraphics();
        Gdx.graphics = mockGraphics;
        HeadlessNet headlessNet = new HeadlessNet();
        Gdx.net = headlessNet;
        HeadlessFiles headlessFiles = new HeadlessFiles();
        Gdx.files = headlessFiles;
        Gdx.gl = mock(GL20.class);
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected abstract AbstractMenuController getMenuController();

    @Test
    public void peformUserActionDefaultTest() {
        AbstractMenuController controller = getMenuController();
        try {
            controller.performUserAction(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new MainMenuButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test 
    public void inputHandlerTest() {
        AbstractMenuController controller = getMenuController();
        try {
            controller.runInputHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
