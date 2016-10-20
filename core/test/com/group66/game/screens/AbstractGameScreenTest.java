package com.group66.game.screens;

import org.junit.Test;

public abstract class AbstractGameScreenTest {
    
    /**
     * Get the current game screen
     * @return the game screen
     */
    protected abstract AbstractGameScreen getGameScreen();
    
    /*
    @Test
    public void functionCallTest() {
        AbstractGameScreen gameScreen = getGameScreen();
        
        gameScreen.show();
        gameScreen.render(1f);
        gameScreen.resize(800, 600);
        gameScreen.pause();
        gameScreen.resume();
        gameScreen.hide();
        gameScreen.dispose();
    }
    */
}
