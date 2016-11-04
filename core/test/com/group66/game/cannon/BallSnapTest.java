package com.group66.game.cannon;

import org.junit.Test;

import com.group66.game.settings.Config;

public class BallSnapTest {
    
    @Test
    public void testSnapBallToGridNull() {
        BallSnap.snapBallToGrid(null, null, false, 0);
    }
    
    @Test
    public void testSnapBallToRoofNull() {
        BallSnap.snapBallToRoof(null, 0, false, 0);
    }
    
    public void testSnapBallToGridSplit() {
        float left = Config.SINGLE_PLAYER_OFFSET + Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2.5f, 10f, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, true, 1);
    }

    @Test
    public void testSnapBallToGrid() {
        float left = Config.SINGLE_PLAYER_OFFSET + Config.BALL_RAD;
        
        Ball ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2.5f, 10f, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);
        
        ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 1.5f, 10f, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);
        
        ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2f, 10f + Config.BALL_DIAM, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);
        
        ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f + Config.BALL_DIAM, 0, 0);
        ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2f, 10f, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);
        
        ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f + Config.BALL_DIAM, 0, 0);
        ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2.5f, 10f, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);
        
        ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        ball2 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 1.5f, 10f + Config.BALL_DIAM, 0, 0);
        BallSnap.snapBallToGrid(ball1, ball2, false, 0);

    }
    
    @Test
    public void testSnapBallToRoofSplit() {
        float left = Config.SINGLE_PLAYER_OFFSET + Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        BallSnap.snapBallToRoof(ball1, 10f, true, 1);
    }

    @Test
    public void testSnapBallToRoof() {
        float left = Config.SINGLE_PLAYER_OFFSET + Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, 0);
        BallSnap.snapBallToRoof(ball1, 10f, false, 0);
    }

}
