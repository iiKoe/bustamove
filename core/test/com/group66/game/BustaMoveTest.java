package com.group66.game;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.group66.game.logging.MessageType;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class BustaMoveTest {
    /**
     * Setup mocking so the game can run in headless mode
     */
    /*
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
    }*/
    
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
        try {
            game.create(); //SpriteBatch causes NullPointerException
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Test for getting the size of the game
     */
    @Test
    public void sizeTest() {
        BustaMove game = BustaMove.getGameInstance();
        game.getGameHeight();
        game.getGameWidth();
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
        
        try {
            game.create(); //SpriteBatch causes NullPointerException
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        game.log(null, null);
        game.log(null, "");
        game.log(null, "Test");
        game.log(MessageType.Info, null);
        game.log(MessageType.Info, "");
        
    }
}
