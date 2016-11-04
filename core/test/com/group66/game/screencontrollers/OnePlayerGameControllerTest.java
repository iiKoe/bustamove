package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.group66.game.BustaMove;
public class OnePlayerGameControllerTest extends AbstractGameControllerTest {
    
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
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener myGdxGame = BustaMove.getGameInstance(); //EntryPoint.getHeadlessMyGdxGame(config);
    }
    
    @Override
    protected AbstractGameController getGameController() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OnePlayerGameController(false, 3);
    }
/*    
    @Test
    public void randomTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new OnePlayerGameController(true, 1);
    }*/
}
