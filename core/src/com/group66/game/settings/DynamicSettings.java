package com.group66.game.settings;

import java.util.ArrayList;

import com.group66.game.shop.BuyScoreMultiplier;
import com.group66.game.shop.BuySpecialBombChance;
import com.group66.game.shop.BuySpeedBoost;

/**
 * The Class DynamicSettings for settings that can change throughout the game.
 */
public class DynamicSettings {

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
     */
    public void setCurrency(int currency) {
        this.currency = currency;
    }

    /**
     * Adds the currency.
     *
     * @param dcurrency the delta currency
     */
    public void addCurrency(int dcurrency) {
        this.currency += dcurrency;
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
     */
    public void setScoreMultiplier(double scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
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
     */
    public void setSpecialBombChanceMultiplier(double specialBombChance) {
        this.specialBombChanceMultiplier = specialBombChance;
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
     */
    public void setBallSpeedMultiplier(double ballSpeedMultiplier) {
        this.ballSpeedMultiplier = ballSpeedMultiplier;
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
     */
    public void setExtraLife(boolean extraLife) {
        this.extraLife = extraLife;
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
     */
    public void setCurrentLevel(int currentLevel) {
        if (currentLevel <= Config.NUMBER_OF_LEVELS) {
            this.currentLevel = currentLevel;
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
     */
    public void setLevelCleared(int levelCleared) {
        if (levelCleared > this.levelCleared) {
            this.levelCleared = levelCleared;
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
     */
    public void setRandomLevel(boolean randomLevel) {
        this.randomLevel = randomLevel;
    }

    /**
     * Sets the highscore of a level
     * 
     * @param level
     * @param score
     */
    public void setLevelHighscore(int level, int score) {
        if (level <= Config.NUMBER_OF_LEVELS) {
            if (levelHighscore.get(level) < score) {
                levelHighscore.set(level, score);
            }
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
            return levelHighscore.get(level);
        }
        return 0;
    }
}
