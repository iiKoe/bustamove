package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallManager;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.HighScoreManager;
import com.group66.game.helpers.LevelLoader;
import com.group66.game.input.InputHandler;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;

/**
 * The Class for the main GameScreen of the game.
 */
public class SplitGameScreen implements Screen {

    /**
     * The Enum GameState.
     */
    private enum GameState {
        
        /** The game is running. */
        RUNNING,
        
        /** The game is paused. */
        PAUSED
    }
    
    /** A place to store the game instance. */
    public static BustaMove game;
    /*
     * ^^^^ made this object public static so it can be used in other classes 
     * (we would like to use this game instance because we're playing in it, without
     * creating a new one in the other class that's calling this object because that won't make any sense)
     * but, does making it "static" has any affect?
     */
    
    /** The game state. */
    private GameState gameState;
    
    /** The input handler. */
    private InputHandler inputHandler = new InputHandler();

    /** The ball manager. */
    private BallManager ballManager1 = new BallManager(0);
    private BallManager ballManager2 = new BallManager(2);
    private BallManager ballManager3 = new BallManager(1);
    
    /**
     * Instantiates the game screen.
     * 
     * @param game
     *            the game instance
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public SplitGameScreen(BustaMove game, Boolean randomLevel) {
        SplitGameScreen.game = game;
        gameState = GameState.RUNNING;
        setup_keys();
        AssetLoader.load();
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(ballManager1, true);
            ballManager2.shiftClone(ballManager1);
            ballManager3.shiftClone(ballManager1);
            BustaMove.logger.log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(ballManager1, true);
            ballManager2.shiftClone(ballManager1);
            ballManager3.shiftClone(ballManager1);
            BustaMove.logger.log(MessageType.Info, "Loaded a random level");
        }
    }
    
    /**
     * Instantiates the game screen.
     * @param game
     *            the game instance
     */
    public SplitGameScreen(BustaMove game) {
        this(game, false);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#show()
     */
    @Override
    public void show() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        
        /* Handle input keys */
        inputHandler.run();
        
        /* Don't update and render when the game is paused */
        if (gameState == GameState.PAUSED) {
            game.batch.begin();
            game.batch.draw(AssetLoader.pausebg, 0, 0, Config.WIDTH, Config.HEIGHT);
            game.batch.end();
            return;
        }

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        game.batch.enableBlending();
        
        /* Draw the balls */
        ballManager1.draw(game.batch, delta);
        ballManager2.draw(game.batch, delta);
        ballManager3.draw(game.batch, delta);
        
        /* Check if game-over condition is reached */
        if (ballManager1.isGameOver() || ballManager2.isGameOver() || ballManager3.isGameOver()) {
            BustaMove.logger.log(MessageType.Info, "Failed the level");
            HighScoreManager.addScore(ballManager1.scoreKeeper.getCurrentScore());
            HighScoreManager.addScore(ballManager2.scoreKeeper.getCurrentScore());
            HighScoreManager.addScore(ballManager3.scoreKeeper.getCurrentScore());
            game.setScreen(new YouLoseScreen(game));
        }

        game.batch.end();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#pause()
     */
    @Override
    public void pause() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#resume()
     */
    @Override
    public void resume() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#hide()
     */
    @Override
    public void hide() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#dispose()
     */
    @Override
    public void dispose() {
        // img.dispose();
        AudioManager.stopMusic();
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
                        ballManager1.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager1.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager1.shootRandomBall();
                    }
                });
        
        inputHandler.registerKeyPressedFunc("Aim Left 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager2.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager2.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager2.shootRandomBall();
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Left 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager3.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager3.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager3.shootRandomBall();
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Toggle Pause",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
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
                });
        
        inputHandler.registerKeyJustPressedFunc("Toggle mute",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        AudioManager.toggleMute();
                    }
                });
        
    }
}
