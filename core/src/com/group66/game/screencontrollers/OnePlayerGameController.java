package com.group66.game.screencontrollers;

import com.group66.game.BustaMove;
import com.group66.game.cannon.GameManager;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.HighScoreManager;
import com.group66.game.helpers.LevelLoader;
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
        AudioManager.startMusic();

        if (!randomLevel) {
            LevelLoader.loadLevel(gameManager1.getBallManager(), level, false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a premade level");
        } else {
            LevelLoader.generateLevel(gameManager1.getBallManager(), false);
            BustaMove.getGameInstance().log(MessageType.Info, "Loaded a random level");
        }
        
        gameManager1.getBallManager().getBallsCannonManager().addRandomBallToCanon();
    }

    @Override
    public void update(float delta) {
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
                
                int score1 = gameManager1.scoreKeeper.getCurrentScore();
                
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
     * Gets the first game manager
     * @return the game manager
     */
    @Override
    public GameManager getGameManager1() {
        return gameManager1;
    }
}
