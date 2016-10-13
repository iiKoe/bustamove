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
     * Add to the current score.
     *
     * @param poppingBalls the popping balls
     * @param hangingBalls the hanging balls
     */
    public void addCurrentScore(int poppingBalls, int hangingBalls) {
        this.currentScore += poppingBalls * 10 + (int)(Math.pow(2.0, hangingBalls) * 10);
        try {
            BustaMove.logger.log(MessageType.Info, "Updated score: " + this.currentScore);
        } catch (Exception e) {
            System.out.println("Could not find logger");
            System.out.println("Info: Updated score: " + this.currentScore);
        }
    }
    
    /**
     * Doubles the current score
     * 
     */
    public void doubleCurrentScore() {
        this.currentScore *= 2;
        try {
            BustaMove.logger.log(MessageType.Info, "Updated score: " + this.currentScore);
        } catch (Exception e) {
            System.out.println("Could not find logger");
            System.out.println("Info: Updated score: " + this.currentScore);
        }
    }

}
