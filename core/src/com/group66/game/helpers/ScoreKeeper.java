package com.group66.game.helpers;

/**
 * The Class ScoreKeeper.
 */
public class ScoreKeeper {

	/** The current score. */
	public int currentScore;

	/**
	 * Instantiates a new score keeper.
	 *
	 * @param currentScore the current score
	 */
	public ScoreKeeper(int currentScore) {
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
	public void setCurrentScore(int poppingBalls, int hangingBalls) {
		this.currentScore = this.currentScore + (poppingBalls + (2 ^ hangingBalls)) * 10;
	}

	/**
	 * Display score.
	 *
	 * @param currentScore the current score
	 */
	public void dispScore(int currentScore) {
	}

}
