package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallAnimationLoader;
import com.group66.game.screencontrollers.AbstractGameController.GameState;
import com.group66.game.screencontrollers.ThreePlayerGameController;
import com.group66.game.settings.Config;

/**
 * The Class for the main GameScreen of the game.
 */
public class ThreePlayerGameScreen extends AbstractGameScreen {

    /**
     * Instantiates the game screen.
     *
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public ThreePlayerGameScreen(Boolean randomLevel, int level) {
        gameController = new ThreePlayerGameController(randomLevel, level);
        
        BallAnimationLoader.load();
        loadRelatedGraphics();
    }
    
    /**
     * Instantiates the game screen.
     * @param randomLevel determines if a set level or a random level is used
     */
    public ThreePlayerGameScreen(Boolean randomLevel) {
        this(randomLevel, 1);
    }
    
    /**
     * Instantiates the game screen.
     * @param level the level to load
     */
    public ThreePlayerGameScreen(int level) {
        this(false, level);
    }
    
    /*
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        try {
            //The game is about to end, dispose of the assets already
            if (gameController.getGameManager1().isGameEnded() || gameController.getGameManager2().isGameEnded()
                    || gameController.getGameManager3().isGameEnded()) {
                dispose();
            }
            
            // Update the game controller
            gameController.update(delta);
        
            SpriteBatch batch = BustaMove.getGameInstance().getBatch();
            Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
            batch.begin();
            
            /* Don't update and render when the game is paused */
            if (gameController.getGameState() == GameState.PAUSED) {
                batch.draw(getPauseBackground(), 0, 0, Config.WIDTH, Config.HEIGHT);
            } else {
                batch.enableBlending();
                
                /* Draw the balls */
                gameController.getGameManager1().draw(this, batch, delta);
                gameController.getGameManager2().draw(this, batch, delta);
                gameController.getGameManager3().draw(this, batch, delta);
            }
            batch.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
