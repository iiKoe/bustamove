package com.group66.game.cannon;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BallsStatic {

    /** The static ball list. */
    private ArrayList<Ball> ballStaticList = new ArrayList<Ball>();

    /** The static ball dead list. */
    private ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();
    
    public void add(Ball ball) {
        ballStaticList.add(ball);
    }
    
    public boolean isEmpty() {
        return ballStaticList.isEmpty();
    }
    
    public int aliveSize() {
        return ballStaticList.size();
    }
    
    public int deadSize() {
        return ballStaticDeadList.size();
    }
    
    public boolean aliveContains(Ball ball) {
        return ballStaticList.contains(ball);
    }
    
    public boolean deadContains(Ball ball) {
        return ballStaticDeadList.contains(ball);
    }
    
    public Ball getFirstDeadBall() {
        return ballStaticDeadList.get(0);
    }
    
    public void addDeadBall(Ball ball) {
        ballStaticDeadList.add(ball);
    }
    
    public void removeStaticBall(Ball ball) {
        ballStaticList.remove(ball);
    }
    
    public void removeFirstDeadBall() {
        ballStaticDeadList.remove(0);
    }
    
    public Ball getLastStaticBall() {
        return ballStaticList.get(ballStaticList.size() - 1);
    }
    
    public void draw(SpriteBatch batch, float delta) {
        for (Ball ball : ballStaticList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
    }
    
    public Ball hitsStaticBall(Ball ball) {
        for (Ball t : ballStaticList) {
            // Does the ball hit a target ball?
            if (t.doesHit(ball.getHitbox())) {
                return t;
            }
        }
        return null;
    }
}
