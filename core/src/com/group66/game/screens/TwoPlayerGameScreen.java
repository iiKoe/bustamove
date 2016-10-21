package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
public class TwoPlayerGameScreen extends AbstractGameScreen {

    /** The ball manager. */
    private BallManager ballManager1;
    private BallManager ballManager2;
    
    /** The dynamic settings instance. */
    private DynamicSettings dynamicSettings;
    
    /**
     * Instantiates the game screen.
     *
     * @param randomLevel
     *            determines if a set level or a random level is used
     * @param dynamicSettings
     *            the dynamicSettings set for this game turn
     */
    public TwoPlayerGameScreen(Boolean randomLevel, DynamicSettings dynamicSettings) {
        gameState = GameState.RUNNING;
        inputHandler = new InputHandler();
        this.dynamicSettings = dynamicSettings;
        ballManager1 = new BallManager(0, dynamicSettings);
        ballManager2 = new BallManager(2, dynamicSettings);
        setup_keys();
        AssetLoader.load();
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(ballManager1, 1, true);
            ballManager2.shiftClone(ballManager1);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(ballManager1, true);
            ballManager2.shiftClone(ballManager1);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
    }
    
    /**
     * Instantiates the game screen.
     */
    public TwoPlayerGameScreen(DynamicSettings dynamicSettings) {
        this(false, dynamicSettings);
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
            
            /* Update the balls without letting them move*/
            ballManager1.update(0);
            ballManager2.update(0);
            return;
        }
        
        /* Update the balls */
        ballManager1.update(delta);
        ballManager2.update(delta);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        
        /* Draw the balls */
        ballManager1.draw(BustaMove.getGameInstance().batch, delta);
        ballManager2.draw(BustaMove.getGameInstance().batch, delta);
        
        /* Check if game-over condition is reached */
        if (ballManager1.isGameOver() || ballManager2.isGameOver()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Failed the level");
            DynamicSettings ds = ballManager1.getDynamicSettings();
            if (ds.hasExtraLife()) {
                ds.setExtraLife(false);
                BustaMove.getGameInstance().log(MessageType.Info, "Keeping Dynamic Settings");
            } else {
                ds.reset();
                BustaMove.getGameInstance().log(MessageType.Info, "Resetting Dynamic Settings");
            }
            BustaMove.getGameInstance().setScreen(new YouLoseScreenRandom(dynamicSettings));
        }
        
        /* Check if game-complete condition is reached */
        if (ballManager1.isGameComplete() || ballManager2.isGameComplete()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Completed the level");
            HighScoreManager highScoreManager = BustaMove.getGameInstance().getHighScoreManager();
            highScoreManager.addScore(ballManager1.scoreKeeper.getCurrentScore());
            highScoreManager.addScore(ballManager2.scoreKeeper.getCurrentScore());
            
            int score1 = ballManager1.scoreKeeper.getCurrentScore();
            int score2 = ballManager2.scoreKeeper.getCurrentScore();
            ballManager1.getDynamicSettings().addCurrency((score1 + score2) / 2 / Config.SCORE_CURRENCY_DIV);
            BustaMove.getGameInstance().setScreen(new YouWinScreenRandom(dynamicSettings));
        }

        BustaMove.getGameInstance().batch.end();
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
