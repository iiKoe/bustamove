package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;


public class BallsCannon {

    /**  Dynamic settings. */
    private DynamicSettings dynamicSettings;
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList;
    
    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;
    
    private Cannon cannon;
    
    /** The balls the canon will shoot. */
    private ArrayList<Ball> cannonBallList = new ArrayList<Ball>();
    
    public BallsCannon(DynamicSettings dynamicSettings, BallGraph ballGraph, Cannon cannon, ArrayList<AtomicInteger> colorList) {
        this.dynamicSettings = dynamicSettings;
        this.ballGraph = ballGraph;
        this.cannon = cannon;
        this.colorList = colorList;
    }

    public void add(Ball ball) {
        cannonBallList.add(ball);
    }
    
    public boolean isEmpty() {
        return cannonBallList.isEmpty();
    }
    
    public Ball getFirst() {
        return cannonBallList.get(0);
    }
    
    public void removeFirst() {
        cannonBallList.remove(0);
    }
    
    public int size() {
        return cannonBallList.size();
    }
    
    public void draw(SpriteBatch batch, float delta) {
        for (Ball ball: cannonBallList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
    }
    
    /**
     * Shoot random colored ball.
     */
    public void addRandomBallToCanon() {
        Random random = new Random();
        int rand;
        int maxType = BallType.BOMB.ordinal();

        
        BustaMove.getGameInstance().log(MessageType.Info, "check if bomb balls is equal to total number of balls: " 
                + (colorList.get(BallType.BOMB.ordinal()).get() == ballGraph.numberOfBalls()));
        rand = random.nextInt(100);
        
        if (rand <= (Config.BOMB_BALL_CHANCE * dynamicSettings.getSpecialBombChanceMultiplier())
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
