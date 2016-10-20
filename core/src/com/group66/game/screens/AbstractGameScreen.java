package com.group66.game.screens;

import com.badlogic.gdx.Screen;
import com.group66.game.helpers.AudioManager;
import com.group66.game.input.InputHandler;

public abstract class AbstractGameScreen implements Screen {
    
    /**
     * The Enum GameState.
     */
    protected enum GameState {
        
        /** The game is running. */
        RUNNING,
        
        /** The game is paused. */
        PAUSED
    }
    
    /** The game state. */
    protected GameState gameState;
    
    /** The input handler. */
    protected InputHandler inputHandler;
    
    /**
     * Show
     * @see com.badlogic.gdx.Screen#show()
     */
    public void show() { }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     * @see com.badlogic.gdx.Screen#render(float)
     */
    public void render(float delta) { }
    
    /**
     * Resize
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    public void resize(int width, int height) { }

    /**
     * Pause
     * @see com.badlogic.gdx.Screen#pause()
     */
    public void pause() { }

    /**
     * Unpause
     * @see com.badlogic.gdx.Screen#resume()
     */
    public void resume() { }

    /**
     * Hide
     * @see com.badlogic.gdx.Screen#hide()
     */
    public void hide() { }

    /**
     * Dispose
     * @see com.badlogic.gdx.Screen#dispose()
     */
    public void dispose() {
        AudioManager.stopMusic();
    }

}
