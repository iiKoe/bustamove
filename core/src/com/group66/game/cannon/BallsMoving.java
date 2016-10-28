package com.group66.game.cannon;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BallsMoving {
    
    /** The ball list. */
    private ArrayList<Ball> ballList = new ArrayList<Ball>();

    /** The ball dead list. */
    private ArrayList<Ball> ballDeadList = new ArrayList<Ball>();
    
    public void add(Ball ball) {
        ballList.add(ball);
    }
    
    public boolean isEmpty() {
        return ballList.isEmpty();
    }
    
    public int aliveSize() {
        return ballList.size();
    }
    
    public int deadSize() {
        return ballDeadList.size();
    }
    
    public boolean aliveContains(Ball ball) {
        return ballList.contains(ball);
    }
    
    public boolean deadContains(Ball ball) {
        return ballDeadList.contains(ball);
    }
    
    public void addDeadBall(Ball ball) {
        ballDeadList.add(ball);
    }
    
    public Ball getLastBall() {
        return ballList.get(ballList.size() - 1);
    }
    
    public ArrayList<Ball> getBallList() {
        return ballList;
    }
    
    public void cleanDead() {
        while (deadSize() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }
    }
    
    public void draw(SpriteBatch batch, float delta) {
        for (Ball ball : ballList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
    }
    
}
