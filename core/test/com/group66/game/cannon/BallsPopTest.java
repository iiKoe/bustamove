package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BallsPopTest {
    
    BallsPop ballsPop = new BallsPop();
    Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);

    @Test
    public void testCheckPop() {
        ballsPop = new BallsPop();
        ballsPop.startPop(ball);
        ballsPop.checkPop();
        assertEquals(ballsPop.contains(ball), true);
    }
    
    @Test
    public void testStartPopNull() {
        ballsPop = new BallsPop();
        ballsPop.startPop(null);
    }

    @Test
    public void testContains() {
        ballsPop = new BallsPop();
        assertEquals(ballsPop.contains(ball), false);
        ballsPop.startPop(ball);
        assertEquals(ballsPop.contains(ball), true);
    }
    
    @Test
    public void testContainsNull() {
        ballsPop = new BallsPop();
        assertEquals(ballsPop.contains(null), false);
    }

    @Test
    public void testSize() {
        ballsPop = new BallsPop();
        assertEquals(ballsPop.size(), 0);
        ballsPop.startPop(ball);
        assertEquals(ballsPop.size(), 1);
    }

    @Test
    public void drawNullTest() {
        ballsPop.draw(null, 0);
    }
    
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        ballsPop.startPop(ball);
        ballsPop.draw(batchMock, 0);
    }

}
