package com.group66.game;

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
import com.group66.game.logging.MessageType;

public class BustaMoveTest {
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
    
    /**
     * Object creation test
     */
    @Test
    public void creationTest() {
        BustaMove.getGameInstance();
    }
    
    @Test
    public void createTest() {
        BustaMove game = BustaMove.getGameInstance();
        //game.create();
    }
    
    @SuppressWarnings("unused")
    @Test
    public void sizeTest() {
        BustaMove game = BustaMove.getGameInstance();
        int height = game.getGameHeight();
        int width = game.getGameWidth();
    }
    
    @Test
    public void loggingTest() {
        BustaMove game = BustaMove.getGameInstance();
        game.log(MessageType.Info, "Test");
    }
}
