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

/**
 * The Class for the main GameScreen of the game.
 */
public class OnePlayerGameScreen extends AbstractGameScreen {

    /** The ball manager. */
    private GameManager gameManager;

   
    /**
     * Instantiates the game screen.
     * 
     * @param randomLevel
     *            determines if a set level or a random level is used
     */
    public OnePlayerGameScreen(Boolean randomLevel) {
        gameState = GameState.RUNNING;
        inputHandler = new InputHandler();
        gameManager = new GameManager(BustaMove.getGameInstance().getDynamicSettings());
        BustaMove.getGameInstance().getDynamicSettings().setRandomLevel(randomLevel, true);
        setup_keys();
        loadRelatedGraphics();
        BallAnimationLoader.load();
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(gameManager.getBallManager(), 
                    BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel(), false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(gameManager.getBallManager(), false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
        gameManager.getBallManager().getBallsCannonManager().addRandomBallToCanon();
    }

    /**
     * Instantiates the game screen.
     */
    public OnePlayerGameScreen() {
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
            gameManager.update(0);
            return;
        }
        
        /* Update the balls */
        gameManager.update(delta);
        
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();

        /* Draw the balls */
        gameManager.draw(this, BustaMove.getGameInstance().batch, delta);

        /* Check if balls need to move down */
        if (gameManager.getBallCount() >= Config.NBALLS_ROW_DOWN 
                && gameManager.canShoot()) {
            System.out.println("Move balls down");
            gameManager.moveRowDown();
            gameManager.setBallCount(0);
        }

        /* Check if game-over condition is reached */
        if (gameManager.isGameOver()) {
            BustaMove.getGameInstance().log(MessageType.Info, "Failed the level");
            
            if (BustaMove.getGameInstance().getDynamicSettings().isRandomLevel()) {
                BustaMove.getGameInstance().setScreen(new YouLoseScreenRandom());
            } else {
                BustaMove.getGameInstance().setScreen(new YouLoseScreenCareer());
            }
        }

        /* Check if game-complete condition is reached */
        if (gameManager.isGameComplete()) {
            int score = gameManager.getScoreKeeper().getCurrentScore();
            BustaMove.getGameInstance().log(MessageType.Info, "Completed the level with score: " + score);
            HighScoreManager highScoreManager = BustaMove.getGameInstance().getHighScoreManager();
            highScoreManager.addScore(score);
            gameManager.getDynamicSettings().addCurrency(score / Config.SCORE_CURRENCY_DIV, true);
            dispose();
            if (BustaMove.getGameInstance().getDynamicSettings().isRandomLevel()) {
                BustaMove.getGameInstance().log(MessageType.Info, "Randomlevel is won");
                BustaMove.getGameInstance().setScreen(new YouWinScreenRandom());
            } else {
                BustaMove.getGameInstance().log(MessageType.Info, "career level is won");
                BustaMove.getGameInstance().getDynamicSettings().setLevelHighscore(
                        BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel(), score);
                BustaMove.getGameInstance().getDynamicSettings().setLevelCleared(
                        BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel(), true);
                BustaMove.getGameInstance().getDynamicSettings().setCurrentLevel(
                        BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel() + 1, true);
                BustaMove.getGameInstance().setScreen(new YouWinScreenCareer());
            }
        }

        BustaMove.getGameInstance().batch.end();
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
                        gameManager.getCannon().cannonAimAdjust(Config.CANNON_AIM_DELTA);
                    }
            });

        inputHandler.registerKeyPressedFunc("Aim Right",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager.getCannon().cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Shoot",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        gameManager.shootBall();
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
