package com.group66.game.helpers;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;

/**
 * The Class ScoreKeeper.
 */
public class ScoreKeeper {

    /** The current score. */
    private int currentScore;

    /**
     * Instantiates a new score keeper.
     *
     */
    public ScoreKeeper() {
        this.currentScore = 0;
    }

    /**
     * Gets the current score.
     *
     * @return the current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Sets the current score.
     *
     * @param poppingBalls the popping balls
     * @param hangingBalls the hanging balls
     */
    public void setCurrentScore(int poppingBalls, int hangingBalls, double multiplier) {
        int add = poppingBalls * 10 + ((int)java.lang.Math.pow(2.0,hangingBalls)) * 10;
        add *= multiplier;
        this.currentScore += add;
        BustaMove.logger.log(MessageType.Info, "Updated score: " + this.currentScore);
    }
    
    /**
     * Doubles the current score
     * 
     */
    public void doubleCurrentScore() {
        this.currentScore *= 2;
        BustaMove.logger.log(MessageType.Info, "Updated score: " + this.currentScore);
    }
    
    // GameScreen.scoreKeeper.setCurrentScore(poppingBalls, hangingBalls);
    /**
     * Display score.
     *
     * @param currentScore the current score
     */
    public void dispScore(int currentScore) {
    }

}
