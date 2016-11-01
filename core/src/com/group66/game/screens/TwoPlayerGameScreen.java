package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallAnimationLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.input.InputHandler;
import com.group66.game.screencontrollers.AbstractGameController.GameState;
import com.group66.game.screencontrollers.TwoPlayerGameController;
import com.group66.game.settings.Config;

/**
 * The Class for the main GameScreen of the game.
 */
public class TwoPlayerGameScreen extends AbstractGameScreen {
    
    /**
     * Instantiates the game screen.
     *
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public TwoPlayerGameScreen(Boolean randomLevel, int level) {
        gameController = new TwoPlayerGameController(randomLevel, level);
        inputHandler = new InputHandler();

        setup_keys();
        BallAnimationLoader.load();
        loadRelatedGraphics();
    }

    /**
     * Instantiates the game screen.
     * @param randomLevel determines if a set level or a random level is used
     */
    public TwoPlayerGameScreen(Boolean randomLevel) {
        this(randomLevel, 1);
    }
    
    /**
     * Instantiates the game screen.
     * @param level the level to load
     */
    public TwoPlayerGameScreen(int level) {
        this(false, level);
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
            if (gameController.getGameManager1().isGameEnded() || gameController.getGameManager2().isGameEnded()) {
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
        inputHandler.registerKeyMap("Shoot 1", Keys.W);
        inputHandler.registerKeyMap("Aim Left 1", Keys.A);
        inputHandler.registerKeyMap("Aim Right 1", Keys.D);
        inputHandler.registerKeyMap("Shoot 2", Keys.UP);
        inputHandler.registerKeyMap("Aim Left 2", Keys.LEFT);
        inputHandler.registerKeyMap("Aim Right 2", Keys.RIGHT);
        inputHandler.registerKeyMap("Toggle Pause", Keys.ESCAPE);
        inputHandler.registerKeyMap("Toggle mute", Keys.M);
        inputHandler.registerKeyMap("Shoot 3", Keys.I);
        inputHandler.registerKeyMap("Aim Left 3", Keys.J);
        inputHandler.registerKeyMap("Aim Right 3", Keys.L);
        
        /* Register key names to functions */
        inputHandler.registerKeyPressedFunc("Aim Left 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().cannon.cannonAimAdjust(-Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager1().shootBall();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        
        inputHandler.registerKeyPressedFunc("Aim Left 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager2().cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager2().cannon.cannonAimAdjust(-Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameController.getGameManager2().shootBall();
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
