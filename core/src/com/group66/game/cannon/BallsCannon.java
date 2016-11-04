package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * A Class to manage the Balls ready to be shot from the Cannon.
 */
public class BallsCannon {

    /**  Dynamic settings. */
    private DynamicSettings dynamicSettings;
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList;
    
    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;
    
    /** The cannon. */
    private Cannon cannon;
    
    /** The balls the canon will shoot. */
    private ArrayList<Ball> cannonBallList = new ArrayList<Ball>();
    
    /**
     * Instantiates a new balls cannon.
     *
     * @param dynamicSettings the dynamic settings
     * @param ballGraph the ball graph
     * @param cannon the cannon
     * @param colorList the color list
     */
    public BallsCannon(DynamicSettings dynamicSettings, BallGraph ballGraph, Cannon cannon, 
            ArrayList<AtomicInteger> colorList) {
        this.dynamicSettings = dynamicSettings;
        this.ballGraph = ballGraph;
        this.cannon = cannon;
        this.colorList = colorList;
    }

    /**
     * Adds the ball to the cannonBallList.
     *
     * @param ball the ball
     */
    public void add(Ball ball) {
        if (ball != null) {
            cannonBallList.add(ball);
        }
    }
    
    /**
     * Checks if the cannonBallList is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return cannonBallList.isEmpty();
    }
    
    /**
     * Gets the first ball.
     *
     * @return the first
     */
    public Ball getFirst() {
        return cannonBallList.get(0);
    }
    
    /**
     * Removes the first ball.
     */
    public void removeFirst() {
        cannonBallList.remove(0);
    }
    
    /**
     * The size of the cannonBallList.
     *
     * @return the int
     */
    public int size() {
        return cannonBallList.size();
    }
    
    /**
     * Draw the balls.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(SpriteBatch batch, float delta) {
        if (batch != null) {
            for (Ball ball: cannonBallList) {
                ball.draw(batch, ball.getType().getAnimation(), delta);
            }
        }
    }
    
    /**
     * Shoot random colored ball.
     */
    public void addRandomBallToCanon() {
        Random random = new Random();
        int maxType = BallType.BOMB.ordinal();
        
        BustaMove.getGameInstance().log(MessageType.Info, "check if bomb balls is equal to total number of balls: " 
                + (colorList.get(BallType.BOMB.ordinal()).get() == ballGraph.numberOfBalls()));
        
        //Generating a random number
        int rand = random.nextInt(100);
        
        if (dynamicSettings != null
                && rand <= (Config.BOMB_BALL_CHANCE * dynamicSettings.getSpecialBombChanceMultiplier())
                || colorList.get(BallType.BOMB.ordinal()).get() == ballGraph.numberOfBalls()) {
            BallType ballType = BallType.BOMB;
            add(ballType.newBall(cannon.getX(), cannon.getY(), 0, 0.0f));
            return;
        }
        do {
            rand = random.nextInt(maxType);
        } while (colorList.get(rand).get() <= 0);
        BallType ballType = BallType.values()[rand];
        add(ballType.newBall(cannon.getX(), cannon.getY(), 0, 0.0f));
    }
}
