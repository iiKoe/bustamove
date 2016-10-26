package com.group66.game.settings;

import java.util.ArrayList;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.shop.BuyScoreMultiplier;
import com.group66.game.shop.BuySpecialBombChance;
import com.group66.game.shop.BuySpeedBoost;

/**
 * The Class DynamicSettings for settings that can change throughout the game.
 */
public class DynamicSettings {
    /** The name of the player. */
    private String name;

    /** The amount of currency. */
    private int currency;

    /** The score multiplier. */
    private double scoreMultiplier;

    /** The special bomb chance multiplier. */
    private double specialBombChanceMultiplier;

    /** The ball speed multiplier. */
    private double ballSpeedMultiplier;

    /** The extra life. */
    boolean extraLife;

    /** The buy score multiplier state machine. */
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;

    /** The buy special bomb chance state machine. */
    private BuySpecialBombChance buySpecialBombChanceStateMachine;

    /** The buy speed boost state machine. */
    private BuySpeedBoost buySpeedBoostStateMachine;

    /** The current level being played. */
    private int currentLevel;

    /** The highest level cleared. */
    private int levelCleared;

    /** The levels that are cleared by the player. */
    private ArrayList<Integer> levelHighscore;

    /** Is current level a random level. */
    private boolean randomLevel;


    /**
     * Instantiates a new dynamic settings instance.
     */
    public DynamicSettings() {
        reset();
        name = new String();
    }

    /**
     * Resets the settings to default.
     */
    public void reset() {
        this.currency = Config.START_CURRENCY;
        this.scoreMultiplier = 1;
        this.specialBombChanceMultiplier = 1;
        this.ballSpeedMultiplier = 1;
        this.extraLife = false;

        this.buyScoreMultiplierStateMachine = new BuyScoreMultiplier();
        this.buySpecialBombChanceStateMachine = new BuySpecialBombChance();
        this.buySpeedBoostStateMachine = new BuySpeedBoost();

        this.currentLevel = 1;
        this.levelCleared = 0;
        this.levelHighscore = new ArrayList<Integer>();
        for (int i = 1; i <= Config.NUMBER_OF_LEVELS; i++) {
            this.levelHighscore.add(0);            
        }
        this.randomLevel = false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @param writeToFile TODO
     */
    public void setName(String name, boolean writeToFile) {
        this.name = name;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile name set to: " + name);
    }

    /**
     * Gets the currency.
     *
     * @return the currency
     */
    public int getCurrency() {
        return currency;
    }

    /**
     * Sets the currency.
     *
     * @param currency the new currency
     * @param writeToFile TODO
     */
    public void setCurrency(int currency, boolean writeToFile) {
        this.currency = currency;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile currency set to: " + currency);
    }

    /**
     * Adds the currency.
     *
     * @param dcurrency the delta currency
     * @param writeToFile TODO
     */
    public void addCurrency(int dcurrency, boolean writeToFile) {
        this.currency += dcurrency;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile currency added: " + dcurrency);
    }

    /**
     * Gets the score multiplier.
     *
     * @return the score multiplier
     */
    public double getScoreMultiplier() {
        return scoreMultiplier;

    }

    /**
     * Sets the score multiplier.
     *
     * @param scoreMultiplier the new score multiplier
     * @param writeToFile TODO
     */
    public void setScoreMultiplier(double scoreMultiplier, boolean writeToFile) {
        this.scoreMultiplier = scoreMultiplier;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile scoremultiplier set to: " + scoreMultiplier);
    }

    /**
     * Gets the special bomb chance multiplier.
     *
     * @return the special bomb chance multiplier
     */
    public double getSpecialBombChanceMultiplier() {
        return specialBombChanceMultiplier;
    }

    /**
     * Sets the special bomb chance multiplier.
     *
     * @param specialBombChance the new special bomb chance multiplier
     * @param writeToFile TODO
     */
    public void setSpecialBombChanceMultiplier(double specialBombChance, boolean writeToFile) {
        this.specialBombChanceMultiplier = specialBombChance;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile special bomb change set to: " + specialBombChance);
    }


    /**
     * Gets the ball speed multiplier.
     *
     * @return the ball speed multiplier
     */
    public double getBallSpeedMultiplier() {
        return ballSpeedMultiplier;
    }

    /**
     * Sets the ball speed multiplier.
     *
     * @param ballSpeedMultiplier the new ball speed multiplier
     * @param writeToFile TODO
     */
    public void setBallSpeedMultiplier(double ballSpeedMultiplier, boolean writeToFile) {
        this.ballSpeedMultiplier = ballSpeedMultiplier;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile ball speed multiplier set to: " + ballSpeedMultiplier);
    }

    /**
     * Checks for extra life.
     *
     * @return true, if successful
     */
    public boolean hasExtraLife() {
        return this.extraLife;
    }

    /**
     * Sets the extra life.
     *
     * @param extraLife the new extra life
     * @param writeToFile TODO
     */
    public void setExtraLife(boolean extraLife, boolean writeToFile) {
        this.extraLife = extraLife;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile extra life set to: " + extraLife);
    }

    /**
     * Gets the buy score multiplier state machine.
     *
     * @return the buy score multiplier state machine
     */
    public BuyScoreMultiplier getBuyScoreMultiplierStateMachine() {
        return buyScoreMultiplierStateMachine;
    }

    /**
     * Gets the buy special bomb chance state machine.
     *
     * @return the buy special bomb chance state machine
     */
    public BuySpecialBombChance getBuySpecialBombChanceStateMachine() {
        return buySpecialBombChanceStateMachine;
    }

    /**
     * Gets the buy speed boost state machine.
     *
     * @return the buy speed boost state machine
     */
    public BuySpeedBoost getBuySpeedBoostStateMachine() {
        return buySpeedBoostStateMachine;
    }

    /**
     * Gets the current level.
     * 
     * @return the currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Set the current level.
     * 
     * @param currentLevel the currentLevel to set
     * @param writeToFile TODO
     */
    public void setCurrentLevel(int currentLevel, boolean writeToFile) {
        if (currentLevel <= Config.NUMBER_OF_LEVELS) {
            this.currentLevel = currentLevel;
            if (writeToFile) {
                BustaMove.getGameInstance().getProfileManager().writeData(this);
            }
            BustaMove.getGameInstance().log(MessageType.Info, "profile currentLevel set to: " + currentLevel);
        }
    } 

    /**
     * @return the levelCleared
     */
    public int getLevelCleared() {
        return levelCleared;
    }

    /**
     * @param levelCleared the levelCleared to set
     * @param writeToFile TODO
     */
    public void setLevelCleared(int levelCleared, boolean writeToFile) {
        if (levelCleared > this.levelCleared) {
            this.levelCleared = levelCleared;
            if (writeToFile) {
                BustaMove.getGameInstance().getProfileManager().writeData(this);
            }
            BustaMove.getGameInstance().log(MessageType.Info, "profile level cleared set to: " + levelCleared);
        }
    }

    /** Is current level random?
     * 
     * @return the randomLevel
     */
    public boolean isRandomLevel() {
        return randomLevel;
    }

    /**
     * Set current level random. 
     * 
     * @param randomLevel the randomLevel to set
     * @param writeToFile TODO
     */
    public void setRandomLevel(boolean randomLevel, boolean writeToFile) {
        this.randomLevel = randomLevel;
        if (writeToFile) {
            BustaMove.getGameInstance().getProfileManager().writeData(this);
        }
        BustaMove.getGameInstance().log(MessageType.Info, "profile random level set to: " + randomLevel);
    }

    /**
     * Sets the highscore of a level
     * 
     * @param level
     * @param score
     */
    public void setLevelHighscore(int level, int score) {
        if (level <= Config.NUMBER_OF_LEVELS && levelHighscore.get(level - 1) < score) {
            levelHighscore.set(level - 1, score);
        }
    }

    /**
     * Get the highscore of a level
     * 
     * @param level
     * @return The highscore
     */
    public int getLevelHighscore(int level) {
        if (level <= Config.NUMBER_OF_LEVELS) {
            return levelHighscore.get(level - 1);
        }
        return 0;
    }
}
