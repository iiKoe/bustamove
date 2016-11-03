package com.group66.game.cannon;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A Class to manage the Balls that are currently moving in the game.
 */
public class BallsMoving {
    
    /** The ball list. */
    private ArrayList<Ball> ballList = new ArrayList<Ball>();

    /** The ball dead list. */
    private ArrayList<Ball> ballDeadList = new ArrayList<Ball>();
    
    /**
     * Adds a ball to the ballList.
     *
     * @param ball the ball
     */
    public void add(Ball ball) {
        if (ball != null) {
            ballList.add(ball);
        }
    }
    
    /**
     * Checks if the ballList is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return ballList.isEmpty();
    }
    
    /**
     * The number of balls in the ballList.
     *
     * @return the int
     */
    public int aliveSize() {
        return ballList.size();
    }
    
    /**
     * The number of dead balls in the ballDeadList.
     *
     * @return the int
     */
    public int deadSize() {
        return ballDeadList.size();
    }
    
    /**
     * Check if a ball is in the ballList.
     *
     * @param ball the ball
     * @return true, if successful
     */
    public boolean aliveContains(Ball ball) {
        if (ball != null) {
            return ballList.contains(ball);
        }
        return false;
    }
    
    /**
     * Check if a ball is in the ballDeadList.
     *
     * @param ball the ball
     * @return true, if successful
     */
    public boolean deadContains(Ball ball) {
        if (ball != null) {
            return ballDeadList.contains(ball);
        }
        return false;
    }
    
    /**
     * Adds the dead ball.
     *
     * @param ball the ball
     */
    public void addDeadBall(Ball ball) {
        if (ball != null) {
            ballDeadList.add(ball);
        }
    }
    
    /**
     * Gets the last ball.
     *
     * @return the last ball
     */
    public Ball getLastBall() {
        return ballList.get(ballList.size() - 1);
    }
    
    /**
     * Gets the ball list.
     *
     * @return the ball list
     */
    public ArrayList<Ball> getBallList() {
        return ballList;
    }
    
    /**
     * Clean dead.
     */
    public void cleanDead() {
        while (deadSize() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }
    }
    
    /**
     * Draw the balls.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(SpriteBatch batch, float delta) {
        if (batch != null) {
            for (Ball ball : ballList) {
                ball.draw(batch, ball.getType().getAnimation(), delta);
            }
        }
    }
}
