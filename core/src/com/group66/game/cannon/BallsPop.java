package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A helper Class to manage the Ball popping.
 */
public class BallsPop {
    
    /** The ball pop animation list. */
    private ArrayList<Ball> ballPopList = new ArrayList<Ball>();

    /**
     * Start popping animation.
     *
     * @param ball the ball
     */
    public void startPop(Ball ball) {
        ball.popStart(ball.getType().getPopAnimation());
        ballPopList.add(ball);
    }
    
    /**
     * Check pop.
     */
    public void checkPop() {
        /* Check if the popping balls are done */
        for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
            Ball ball = it.next();
            if (ball.popDone() == true) {
                it.remove();
                //System.out.println("Pop list size: " + ballPopList.size());
            }
        }
    }
    
    /**
     * Contains.
     *
     * @param ball the ball
     * @return true, if successful
     */
    public boolean contains(Ball ball) {
        return ballPopList.contains(ball);
    }
    
    /**
     * Size.
     *
     * @return the int
     */
    public int size() {
        return ballPopList.size();
    }
    
    /**
     * Draw the balls.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(SpriteBatch batch, float delta) {
        for (Ball ball : ballPopList) {
            ball.draw(batch, null, delta);
        }
    }
}
