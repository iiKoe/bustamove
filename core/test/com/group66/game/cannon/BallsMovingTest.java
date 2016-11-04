package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BallsMovingTest {
    
    BallsMoving ballsMoving = new BallsMoving();
    Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
    
    @Test
    public void testAddNull() {
        ballsMoving.add(null);
    }

    @Test
    public void testAdd() {
        ballsMoving.add(ball);
    }

    @Test
    public void testAddRemove() {
        BallsMoving ballsMoving = new BallsMoving();
        assertEquals(ballsMoving.aliveSize(), 0);
        assertEquals(ballsMoving.deadSize(), 0);
        assertEquals(ballsMoving.isEmpty(), true);
        ballsMoving.add(ball);
        assertEquals(ballsMoving.aliveSize(), 1);
        assertEquals(ballsMoving.deadSize(), 0);
        assertEquals(ballsMoving.isEmpty(), false);
        
        assertEquals(ballsMoving.aliveContains(ball), true);
        assertEquals(ballsMoving.deadContains(ball), false);
        
        ballsMoving.addDeadBall(ball);
        
        assertEquals(ballsMoving.aliveContains(ball), true);
        assertEquals(ballsMoving.deadContains(ball), true);
        
        ballsMoving.cleanDead();
        
        assertEquals(ballsMoving.aliveContains(ball), false);
        assertEquals(ballsMoving.deadContains(ball), false);
    }

    @Test
    public void testGetLastBall() {
        BallsMoving ballsMoving = new BallsMoving();
        assertEquals(ballsMoving.getLastBall(), null);
        ballsMoving.add(ball);
        assertEquals(ballsMoving.getLastBall(), ball);
    }
    
    @Test
    public void testAliveContainsNull() {
        BallsMoving ballsMoving = new BallsMoving();
        assertEquals(ballsMoving.aliveContains(null), false);
    }
    
    @Test
    public void testDeadContainsNull() {
        BallsMoving ballsMoving = new BallsMoving();
        assertEquals(ballsMoving.deadContains(null), false);
    }
    
    @Test
    public void testAddDeadNull() {
        BallsMoving ballsMoving = new BallsMoving();
        ballsMoving.addDeadBall(null);
    }

    @Test
    public void testGetBallList() {
        assertNotEquals(ballsMoving.getBallList(), null);
    }

    @Test
    public void drawNullTest() {
        ballsMoving.draw(null, 0);
    }
    
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        ballsMoving.add(ball);
        ballsMoving.draw(batchMock, 0);
    }


}
