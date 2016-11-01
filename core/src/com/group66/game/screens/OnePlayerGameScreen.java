package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallAnimationLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.input.InputHandler;
import com.group66.game.screencontrollers.OnePlayerGameController;
import com.group66.game.screencontrollers.AbstractGameController.GameState;
import com.group66.game.settings.Config;

/**
 * The Class for the main GameScreen of the game.
 */
public class OnePlayerGameScreen extends AbstractGameScreen {
   
    /**
     * Instantiates the game screen.
     * 
     * @param randomLevel
     *            determines if a set level or a random level is used
     * @param level the level to load
     */
    public OnePlayerGameScreen(Boolean randomLevel, int level) {
        gameController = new OnePlayerGameController(randomLevel, level);
        inputHandler = new InputHandler();
        
        BustaMove.getGameInstance().getDynamicSettings().setRandomLevel(randomLevel, true);
        setup_keys();
        loadRelatedGraphics();
        BallAnimationLoader.load();
    }

    /**
     * Instantiates the game screen.
     * @param randomLevel determines if a set level or a random level is used
     */
    public OnePlayerGameScreen(Boolean randomLevel) {
        this(randomLevel, 1);
    }
    
    /**
     * Instantiates the game screen.
     * @param level the level to load
     */
    public OnePlayerGameScreen(int level) {
        this(false, level);
    }
    
    /**
     * Instantiates the game screen.
     */
    public OnePlayerGameScreen() {
        this(false, BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel());
    }

    /*
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        try {
            /* Handle input keys */
            inputHandler.run();
    
            //The game is about to end, dispose of the assets already
            if (gameController.getGameManager1().isGameEnded()) {
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
            }
            batch.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Setup the keys used in the game screen keys.
     */
    private void setup_keys() {
        // Setup the game keys
        inputHandler.registerKeyMap("Shoot", Keys.SPACE);
        inputHandler.registerKeyMap("Shoot", Keys.BACKSPACE);
        inputHandler.registerKeyMap("Shoot", Keys.W);
        inputHandler.registerKeyMap("Shoot", Keys.UP);
        inputHandler.registerKeyMap("Aim Left", Keys.A);
        inputHandler.registerKeyMap("Aim Left", Keys.LEFT);
        inputHandler.registerKeyMap("Aim Right", Keys.D);
        inputHandler.registerKeyMap("Aim Right", Keys.RIGHT);
        inputHandler.registerKeyMap("Place Ball", Keys.ENTER);
        inputHandler.registerKeyMap("Toggle Pause", Keys.ESCAPE);
        inputHandler.registerKeyMap("Toggle mute", Keys.M);

        /* Register key names to functions */
        inputHandler.registerKeyPressedFunc("Aim Left",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyPressedFunc("Aim Right",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().cannon.cannonAimAdjust(-Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Shoot",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().shootBall();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Toggle Pause",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameController.togglePause();
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Toggle mute",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        AudioManager.toggleMute();
                    }
            });

    }
}
