package com.group66.game.screencontrollers;

import com.badlogic.gdx.Input.Keys;
import com.group66.game.BustaMove;
import com.group66.game.cannon.GameManager;
import com.group66.game.helpers.HighScoreManager;
import com.group66.game.helpers.LevelLoader;
import com.group66.game.input.InputHandler;
import com.group66.game.logging.MessageType;
import com.group66.game.screens.YouLoseScreenCareer;
import com.group66.game.screens.YouLoseScreenRandom;
import com.group66.game.screens.YouWinScreenCareer;
import com.group66.game.screens.YouWinScreenRandom;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class OnePlayerGameController extends AbstractGameController {
    private GameManager gameManager1;
    
    /**
     * Instantiates the controller for the two player game screen
     * @param randomLevel determines if a set level or a random level is used
     * @param level the specific level to load
     */
    public OnePlayerGameController(Boolean randomLevel, int level) {
        gameState = GameState.RUNNING;
        gameManager1 = new GameManager(BustaMove.getGameInstance().getDynamicSettings());
        inputHandler = new InputHandler();
        setupKeys();
        BustaMove.getGameInstance().getAudioManager().startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(gameManager1.getBallManager(), level, false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(gameManager1.getBallManager(), false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
        
        if (gameManager1.getBallManager() != null) {
            gameManager1.getBallManager().getBallsCannonManager().addRandomBallToCanon();
        }
    }

    @Override
    public void update(float delta) {
        /* Handle input keys */
        inputHandler.run();
        
        if (gameState == GameState.PAUSED) {
            /* Update the balls without letting them move*/
            gameManager1.update(0);
        } else {
            /* Update the balls */
            gameManager1.update(delta);
    
            /* Check if game-over condition is reached */
            if (gameManager1.isGameOver()) {
                BustaMove.getGameInstance().log(MessageType.Info, "Failed the level");
                DynamicSettings ds = gameManager1.getDynamicSettings();
                ds = (ds == null ? new DynamicSettings() : ds);
                if (ds.hasExtraLife()) {
                    ds.setExtraLife(false, true);
                    BustaMove.getGameInstance().log(MessageType.Info, "Keeping Dynamic Settings");
                } else {
                    ds.reset();
                    BustaMove.getGameInstance().log(MessageType.Info, "Resetting Dynamic Settings");
                }
                
                if (BustaMove.getGameInstance().getDynamicSettings() == null
                        || BustaMove.getGameInstance().getDynamicSettings().isRandomLevel()) {
                    BustaMove.getGameInstance().setScreen(new YouLoseScreenRandom());
                } else {
                    BustaMove.getGameInstance().setScreen(new YouLoseScreenCareer());
                }
            }
            
            /* Check if game-complete condition is reached */
            if (gameManager1.isGameComplete()) {
                BustaMove.getGameInstance().log(MessageType.Info, "Completed the level");
                
                int score1 = gameManager1.getScoreKeeper().getCurrentScore();
                
                HighScoreManager highScoreManager = BustaMove.getGameInstance().getHighScoreManager();
                highScoreManager.addScore(score1);
                
                gameManager1.getDynamicSettings().addCurrency(score1 / Config.SCORE_CURRENCY_DIV, true);
                
                if (BustaMove.getGameInstance().getDynamicSettings() == null
                        || BustaMove.getGameInstance().getDynamicSettings().isRandomLevel()) {
                    BustaMove.getGameInstance().log(MessageType.Info, "Randomlevel is won");
                    BustaMove.getGameInstance().setScreen(new YouWinScreenRandom());
                } else {
                    int currLevel = BustaMove.getGameInstance().getDynamicSettings().getCurrentLevel();
                    BustaMove.getGameInstance().log(MessageType.Info, "career level is won");
                    BustaMove.getGameInstance().getDynamicSettings().setLevelHighscore(currLevel, score1);
                    BustaMove.getGameInstance().getDynamicSettings().setLevelCleared(currLevel, true);
                    BustaMove.getGameInstance().getDynamicSettings().setCurrentLevel(currLevel + 1, true);
                    BustaMove.getGameInstance().setScreen(new YouWinScreenCareer());
                }

            }

        }
    }
    
    /**
     * Setup the keys used in the game screen keys.
     */
    @Override
    protected void setupKeys() {
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
                            gameManager1.getCannon().cannonAimAdjust(Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyPressedFunc("Aim Right",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameManager1.getCannon().cannonAimAdjust(-Config.CANNON_AIM_DELTA);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Shoot",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        try {
                            gameManager1.shootBall();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Toggle Pause",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        togglePause();
                    }
            });

        inputHandler.registerKeyJustPressedFunc("Toggle mute",
                new InputHandler.KeyCommand() {
                    public void runCommand() {
                        BustaMove.getGameInstance().getAudioManager().toggleMute();
                    }
            });
    }
    
    /**
     * Gets the first game manager
     * @return the game manager
     */
    @Override
    public GameManager getGameManager1() {
        return gameManager1;
    }
}
