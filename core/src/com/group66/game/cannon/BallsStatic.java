package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;
import com.group66.game.cannon.ballgraph.BallGraph;

/**
 * A Class to manage the Balls that are currently not moving in the game.
 */
public class BallsStatic {
    
    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList;

    /** The static ball list. */
    private ArrayList<Ball> ballStaticList = new ArrayList<Ball>();

    /** The static ball dead list. */
    private ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();
    
    /**
     * Instantiates a new balls static.
     *
     * @param dynamicSettings the dynamic settings
     * @param ballGraph the ball graph
     * @param colorList the color list
     */
    public BallsStatic(DynamicSettings dynamicSettings, BallGraph ballGraph, ArrayList<AtomicInteger> colorList) {
        this.ballGraph = ballGraph;
        this.colorList = colorList;
    }
    
    /**
     * Adds the.
     *
     * @param ball the ball
     */
    public void add(Ball ball) {
        ballStaticList.add(ball);
    }
    
    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return ballStaticList.isEmpty();
    }
    
    /**
     * Alive size.
     *
     * @return the int
     */
    public int aliveSize() {
        return ballStaticList.size();
    }
    
    /**
     * Dead size.
     *
     * @return the int
     */
    public int deadSize() {
        return ballStaticDeadList.size();
    }
    
    /**
     * Alive contains.
     *
     * @param ball the ball
     * @return true, if successful
     */
    public boolean aliveContains(Ball ball) {
        return ballStaticList.contains(ball);
    }
    
    /**
     * Dead contains.
     *
     * @param ball the ball
     * @return true, if successful
     */
    public boolean deadContains(Ball ball) {
        return ballStaticDeadList.contains(ball);
    }
    
    /**
     * Gets the first dead ball.
     *
     * @return the first dead ball
     */
    public Ball getFirstDeadBall() {
        return ballStaticDeadList.get(0);
    }
    
    /**
     * Adds the dead ball.
     *
     * @param ball the ball
     */
    public void addDeadBall(Ball ball) {
        ballStaticDeadList.add(ball);
    }
    
    /**
     * Removes the static ball.
     *
     * @param ball the ball
     */
    public void removeStaticBall(Ball ball) {
        ballStaticList.remove(ball);
    }
    
    /**
     * Removes the first dead ball.
     */
    public void removeFirstDeadBall() {
        ballStaticDeadList.remove(0);
    }
    
    /**
     * Gets the last static ball.
     *
     * @return the last static ball
     */
    public Ball getLastStaticBall() {
        return ballStaticList.get(ballStaticList.size() - 1);
    }
    
    /**
     * Draw the balls.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(SpriteBatch batch, float delta) {
        for (Ball ball : ballStaticList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
    }
    
    /**
     * Hits static ball.
     *
     * @param ball the ball
     * @return the ball
     */
    public Ball hitsStaticBall(Ball ball) {
        for (Ball t : ballStaticList) {
            // Does the ball hit a target ball?
            if (t.doesHit(ball.getHitbox())) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * Adds a static ball.
     * 
     * @param type the color
     * @param xpos the x coordinate
     * @param ypos the y coordinate
     */
    public void addStaticBall(BallType type, float xpos, float ypos) {
        Ball ball;
        ball = type.newBall(xpos, ypos, 0, 0.0f);
        add(ball);
        colorList.get(ball.getColor()).incrementAndGet();
        ballGraph.insertBall(ball);
        
        BustaMove.getGameInstance().log(MessageType.Debug, "add ball: color(" + ball.getColor() + "), x(" + ball.getX()
            + "), y(" + ball.getY() + "), pointer(" + ball.toString() + ")");
    }
    
    /**
     * Adds a static ball.
     *
     * @param color the color
     * @param xpos the xpos
     * @param ypos the ypos
     */
    public void addStaticBall(int color, float xpos, float ypos) { 
        color %= BallType.MAX_COLORS.ordinal();
        BallType type = BallType.values()[color];
        addStaticBall(type, xpos, ypos);
    }
    
    /**
     * Adds a random static ball.
     * 
     * @param xpos the x coordinate
     * @param ypos the y coordinate
     */
    public void addRandomStaticBall(float xpos, float ypos) {
        Random random = new Random();
        int rand = random.nextInt(BallType.MAX_COLORS.ordinal() + 1);
        addStaticBall(rand, xpos, ypos);
    }
    
    /**
     * Gets the ball static list.
     *
     * @return the ball static list
     */
    public ArrayList<Ball> getBallStaticList() {
        return ballStaticList;
    }
    
    /**
     * Move row down.
     */
    public void moveRowDown() {
        // Move all the balls down
        for (Ball b : ballStaticList) {
            b.moveDown(Config.BALL_DIAM);
        }
    }
    
    /**
     * Hits bottom.
     *
     * @return true, if successful
     */
    public boolean hitsBottom() {
        for (Ball b : ballStaticList) {
            // TODO fix the != check
            if (b.getY() - Config.BALL_DIAM <= Config.BORDER_SIZE_BOT && b.getY() != 0) {
                return true;
            }
        }
        return false;
    }
}
