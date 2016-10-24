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
    /**
     * Setup mocking so the game can run in headless mode
     */
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
    
    /**
     * Test for game instantiation
     */
    @Test
    public void createTest() {
        BustaMove game = BustaMove.getGameInstance();
        //game.create(); //SpriteBatch causes NullPointerException
    }
    
    /**
     * Test for getting the size of the game
     */
    @SuppressWarnings("unused")
    @Test
    public void sizeTest() {
        BustaMove game = BustaMove.getGameInstance();
        int height = game.getGameHeight();
        int width = game.getGameWidth();
    }
    
    /**
     * Test for calling the logger
     */
    @Test
    public void loggingTest() {
        BustaMove game = BustaMove.getGameInstance();
        //game.create(); //SpriteBatch causes NullPointerException
        game.log(MessageType.Info, "Test");
    }
    
    @Test
    public void nullTest() {
        BustaMove game = BustaMove.getGameInstance();
        game.log(null, null);
        game.log(null, "");
        game.log(null, "Test");
        game.log(MessageType.Info, null);
        game.log(MessageType.Info, "");
        
        //game.create(); //SpriteBatch causes NullPointerException
        /*
        game.log(null, null);
        game.log(null, "");
        game.log(null, "Test");
        game.log(MessageType.Info, null);
        game.log(MessageType.Info, "");
        */
    }
}
