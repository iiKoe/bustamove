package com.group66.game.helpers;

public class ScoreKeeper {

	public int currentScore;


	public ScoreKeeper(int currentScore) {
		this.currentScore = 0;
	}


	// getting the current score
	public int getCurrentScore() {
		return currentScore;
	}

	//calculating the new score
	public void setCurrentScore(int poppingBalls, int hangingBalls) {
		this.currentScore = this.currentScore + (poppingBalls + (2 ^ hangingBalls)) * 10;
	}

	//update score shown on screen
	public void dispScore(int currentScore) {

	}

}
