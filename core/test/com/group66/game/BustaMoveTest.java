package com.group66.game;

import org.junit.Test;

import com.group66.game.logging.MessageType;

public class BustaMoveTest {
    
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
        game.create();
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
