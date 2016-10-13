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
import com.group66.game.settings.DynamicSettings;

/**
 * The Class for the main GameScreen of the game.
 */
public class GameScreen implements Screen {

    /**
     * The Enum GameState.
     */
    private enum GameState {
        
        /** The game is running. */
        RUNNING,
        
        /** The game is paused. */
        PAUSED
    }
    
    /** The game state. */
    private GameState gameState;
    
    /** The input handler. */
    private InputHandler inputHandler = new InputHandler();

    /** The ball manager. */
    private BallManager ballManager;
    
    /**
     * Instantiates the game screen.
     * 
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public GameScreen(Boolean randomLevel, DynamicSettings dynamicSettings) {
        gameState = GameState.RUNNING;
        ballManager = new BallManager(dynamicSettings);
        setup_keys();
        AssetLoader.load();
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(ballManager, false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(ballManager, false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
    }
    
    /**
     * Instantiates the game screen.
     */
    public GameScreen(DynamicSettings dynamicSettings) {
        this(false, dynamicSettings);
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
            BustaMove.getGameInstance().batch.begin();
            BustaMove.getGameInstance().batch.draw(AssetLoader.pausebg, 0, 0, Config.WIDTH, Config.HEIGHT);
            BustaMove.getGameInstance().batch.end();
            return;
        }

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        
        /* Draw the balls */
        ballManager.draw(BustaMove.getGameInstance().batch, delta);
        
        /* Check if balls need to move down */
        if (ballManager.getBallCount() >= Config.NBALLS_ROW_DOWN 
                && ballManager.canShoot()) {
            System.out.println("Move balls down");
            ballManager.moveRowDown();
            ballManager.setBallCount(0);
        }
        
        /* Check if game-over condition is reached */
        if (ballManager.isGameOver()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Failed the level");
            DynamicSettings ds = ballManager.getDynamicSettings();
            if (ds.hasExtraLife()) {
                ds.setExtraLife(false);
                BustaMove.getGameInstance().log(MessageType.Info, "Keeping Dynamic Settings");
            } else {
                ds.reset();
                BustaMove.getGameInstance().log(MessageType.Info, "Resetting Dynamic Settings");
            }
            BustaMove.getGameInstance().setScreen(new YouLoseScreen());
        }
        
        /* Check if game-complete condition is reached */
        if (ballManager.isGameComplete()) {
            int score = ballManager.scoreKeeper.getCurrentScore();
            BustaMove.getGameInstance().log(MessageType.Info, "Completed the level with score: " + score);
            HighScoreManager.addScore(score);
            ballManager.getDynamicSettings().addCurrency(score / Config.SCORE_CURRENCY_DIV);
            BustaMove.getGameInstance().setScreen(new YouWinScreen());
        }
        
        BustaMove.getGameInstance().batch.end();
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
                        ballManager.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        ballManager.shootRandomBall();
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
