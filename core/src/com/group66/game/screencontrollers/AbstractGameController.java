package com.group66.game.screencontrollers;

import javax.naming.OperationNotSupportedException;

import com.group66.game.cannon.GameManager;
import com.group66.game.helpers.AudioManager;
import com.group66.game.input.InputHandler;

public abstract class AbstractGameController {
    /**
     * The Enum GameState.
     */
    public enum GameState {
        
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
     * Get the game state
     * @return the game state
     */
    public GameState getGameState() {
        return gameState;
    }
    
    /**
     * Called when the controller should update its internal state
     * @param delta The time in seconds since the last render
     */
    public abstract void update(float delta);
    
    /**
     * Setup the keys used in the game screen keys.
     */
    protected abstract void setupKeys();
    
    /**
     * Toggles the pause state
     */
    public void togglePause() {
        switch (gameState) {
            case PAUSED:
                /* Resume the game */
                gameState = GameState.RUNNING;
                AudioManager.startMusic();
                break;
            case RUNNING:
                /* Pause the game */
                gameState = GameState.PAUSED;
                AudioManager.stopMusic();
                break;
            default:
                break;
        }
    }
    
    /**
     * Gets the first game manager
     * @return the game manager
     * @throws OperationNotSupportedException 
     */
    public GameManager getGameManager1() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    
    /**
     * Gets the second game manager
     * @return the game manager
     * @throws OperationNotSupportedException 
     */
    public GameManager getGameManager2() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    
    /**
     * Gets the third game manager
     * @return the game manager
     * @throws OperationNotSupportedException 
     */
    public GameManager getGameManager3() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
