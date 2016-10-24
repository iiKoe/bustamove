package com.group66.game.cannon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.math.Circle;
import com.group66.game.cannon.BallType;

/**
 * The Class BallTest.
 */
public abstract class BallTest {

    protected abstract Ball getBall();
    
    /**
     * Ball test.
     */
    @Test
    public void ballTest() {
        Ball ball = getBall();
        assertNotNull(ball);
        assertEquals(1.2f, ball.getX(), 0.001);
        assertEquals(2.3f, ball.getY(), 0.001);
        assertEquals(5.6f, ball.getAngle(), 0.001);
    }

    @Test
    public void equalTest() {
        Ball ball = getBall();
        Ball ball2 = new ColoredBall(BallType.BLUE, 10f, 10f, 4, 6.4f);
        ball.isEqual(ball2);
    }
    
    /**
     * Sets the move test.
     */
    @Test
    public void setMoveTest() {
        Ball ball = new ColoredBall(BallType.BLUE, 10f, 10f, 4, 6.4f);
        ball.setX(20f);
        ball.setY(30f);
        assertEquals(20f, ball.getX(), 0.001);
        assertEquals(30f, ball.getY(), 0.001);
        ball.moveDown(10f);
        assertEquals(20f, ball.getY(), 0.001);
    }
    
    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        float delta = 10;
        int speed = 30;
        float xpos = 10f;
        float ypos = 20f;
        float angle = 6.4f;
        Ball ball = new ColoredBall(BallType.BLUE, xpos, ypos, speed, angle);
        assertEquals(10f, ball.getX(), 0.001);
        float resx = xpos + speed * (float) Math.cos(angle) * delta;
        float resy = ypos + speed * (float) Math.sin(angle) * delta;
        ball.update(delta);
        assertEquals(resx, ball.getX(), 0.001);
        assertEquals(resy, ball.getY(), 0.001);
    }
    
    /**
     * Dead test.
     */
    @Test
    public void deadTest() {
        Ball ball = new ColoredBall(BallType.BLUE, 10f, 10f, 4, 6.4f);
        assertFalse(ball.isDead());
        ball.update(5);
        assertFalse(ball.isDead());
        ball.update(20);
        assertTrue(ball.isDead());
    }
    
    /**
     * Hit test.
     */
    @Test
    public void hitTest() {
        Ball ball = new ColoredBall(BallType.BLUE, 100, 100, 4, 6.4f);
        
        Circle circleHit = new Circle(100, 100, 10);
        Circle circleMiss = new Circle(0, 0, 10);
        assertTrue(ball.doesHit(circleHit));
        assertFalse(ball.doesHit(circleMiss));
        
        Circle circleNextTo = new Circle(100, 90, 10);
        assertTrue(ball.isNextTo(circleNextTo));
    }
        
    /**
     * Adds the graph null test.
     */
    @Test(expected = NullPointerException.class)
    public void addGraphNullTest() {
        BallGraph graphMock = mock(BallGraph.class);
        Ball ball = new ColoredBall(BallType.BLUE, 100, 100, 4, 6.4f);
        ball.addToGraph(graphMock);
        ball.addToGraph(null);
    }
    
    /**
     * Delete graph null test.
     */
    @Test(expected = NullPointerException.class)
    public void deleteGraphNullTest() {
        BallGraph graphMock = mock(BallGraph.class);
        Ball ball = new ColoredBall(BallType.BLUE, 100, 100, 4, 6.4f);
        ball.deleteBallFromGraph(graphMock);
        ball.deleteBallFromGraph(null);
    }
    
    /**
     * Tests the balls comparison methods for the colored and bomb balls
     */
    @Test
    public void compareBallTest() {
        Ball ball1 = new ColoredBall(BallType.GREEN, 50, 50, 0, 0);
        Ball ball2 = new ColoredBall(BallType.GREEN, 50, 80, 0, 0);
        Ball ball3 = new ColoredBall(BallType.BLUE, 50, 110, 0, 0);
        assertTrue(ball1.isEqual(ball2));
        assertFalse(ball1.isEqual(ball3));
        Ball ball4 = new BombBall(50, 50, 0, 0);
        Ball ball5 = new BombBall(50, 80, 0, 0);
        assertTrue(ball4.isEqual(ball5));
        assertFalse(ball4.isEqual(ball3));
        assertFalse(ball3.isEqual(ball4));
    }
        
    /**
     * Test the ball getColor method
     */
    @Test
    public void getColorTest() {
        Ball ball1 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        assertEquals(ball1.getColor(), 0);
    }
    
}
