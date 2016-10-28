package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallAnimationLoader;
import com.group66.game.cannon.GameManager;
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
public class ThreePlayerGameScreen extends AbstractGameScreen {

    /** The ball manager. */
    private GameManager gameManager1;
    private GameManager gameManager2;
    private GameManager gameManager3;
   
    /**
     * Instantiates the game screen.
     *
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public ThreePlayerGameScreen(Boolean randomLevel) {
        gameState = GameState.RUNNING;
        inputHandler = new InputHandler();
        gameManager1 = new GameManager(0, BustaMove.getGameInstance().getDynamicSettings());
        gameManager2 = new GameManager(2, BustaMove.getGameInstance().getDynamicSettings());
        gameManager3 = new GameManager(1, BustaMove.getGameInstance().getDynamicSettings());
        setup_keys();
        BallAnimationLoader.load();
        loadRelatedGraphics();
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(gameManager1.getBallManager(), 1, true);
            gameManager2.shiftClone(gameManager1);
            gameManager3.shiftClone(gameManager1);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(gameManager1.getBallManager(), true);
            gameManager2.shiftClone(gameManager1);
            gameManager3.shiftClone(gameManager1);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
        gameManager1.getBallManager().getBallsCannonManager().addRandomBallToCanon();
        gameManager2.getBallManager().getBallsCannonManager().addRandomBallToCanon();
        gameManager3.getBallManager().getBallsCannonManager().addRandomBallToCanon();
    }
    
    /**
     * Instantiates the game screen.
     */
    public ThreePlayerGameScreen(DynamicSettings dynamicSettings) {
        this(false);
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
            BustaMove.getGameInstance().batch.draw(getPauseBackground(), 0, 0, Config.WIDTH, Config.HEIGHT);
            BustaMove.getGameInstance().batch.end();
            
            /* Update the balls without letting them move*/
            gameManager1.update(0);
            gameManager2.update(0);
            gameManager2.update(0);
            return;
        }
        
        /* Update the balls */
        gameManager1.update(delta);
        gameManager2.update(delta);
        gameManager3.update(delta);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        
        /* Draw the balls */
        gameManager1.draw(this, BustaMove.getGameInstance().batch, delta);
        gameManager2.draw(this, BustaMove.getGameInstance().batch, delta);
        gameManager3.draw(this, BustaMove.getGameInstance().batch, delta);
        
        /* Check if game-over condition is reached */
        if (gameManager1.isGameOver() || gameManager2.isGameOver() || gameManager3.isGameOver()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Failed the level");
            DynamicSettings ds = gameManager1.getDynamicSettings();
            if (ds.hasExtraLife()) {
                ds.setExtraLife(false, true);
                BustaMove.getGameInstance().log(MessageType.Info, "Keeping Dynamic Settings");
            } else {
                ds.reset();
                BustaMove.getGameInstance().log(MessageType.Info, "Resetting Dynamic Settings");
            }
            BustaMove.getGameInstance().setScreen(new YouLoseScreenRandom());
        }
        
        /* Check if game-complete condition is reached */
        if (gameManager1.isGameComplete() || gameManager2.isGameComplete() || gameManager3.isGameComplete()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Completed the level");
            HighScoreManager highScoreManager = BustaMove.getGameInstance().getHighScoreManager();
            highScoreManager.addScore(gameManager1.scoreKeeper.getCurrentScore());
            highScoreManager.addScore(gameManager2.scoreKeeper.getCurrentScore());
            highScoreManager.addScore(gameManager3.scoreKeeper.getCurrentScore());
            
            int score1 = gameManager1.scoreKeeper.getCurrentScore();
            int score2 = gameManager2.scoreKeeper.getCurrentScore();
            int score3 = gameManager3.scoreKeeper.getCurrentScore();
            gameManager1.getDynamicSettings().addCurrency((score1 + score2 + score3) / 3 
                    / Config.SCORE_CURRENCY_DIV, true);
            BustaMove.getGameInstance().setScreen(new YouWinScreenRandom());
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
                        gameManager1.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager1.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 1",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager1.shootBall();
                    }
                });
        
        inputHandler.registerKeyPressedFunc("Aim Left 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager2.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager2.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 2",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager2.shootBall();
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Left 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager3.cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyPressedFunc("Aim Right 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager3.cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
                });

        inputHandler.registerKeyJustPressedFunc("Shoot 3",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager3.shootBall();
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
