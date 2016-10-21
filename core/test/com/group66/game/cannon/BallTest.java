package com.group66.game.cannon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.math.Circle;
import com.group66.game.cannon.BallType;

/**
 * The Class BallTest.
 */
public class BallTest {

    /**
     * Ball test.
     */
    @Test
    public void ballTest() {
        Ball ball = new ColoredBall(BallType.BLUE, 1f, 2f, 4, 6.4f);
        assertNotNull(ball);
        assertEquals(1f, ball.getX(), 0.001);
        assertEquals(2f, ball.getY(), 0.001);
        assertEquals(6.4f, ball.getAngle(), 0.001);
    }
    
    /**
     * Ball null test.
     */
   /* @Test(expected=NullPointerException.class)
    public void ballNullTest() {
        @SuppressWarnings("unused")
        Ball ball = new ColoredBall(0, 1f, 2f, 3f, 4, 6.4f, null);
    }*/
    
    /**
     * Ball test 2.
     */
    /*@Test
    public void ballTest2() {
        BallGraph graph = mock(BallGraph.class);
        @SuppressWarnings("unused")
        Ball ball = new ColoredBall(0, 1f, 2f, 3f, 4, 6.4f, graph);
    }*/
    
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
        float x = 10f;
        float y = 20f;
        float angle = 6.4f;
        Ball ball = new ColoredBall(BallType.BLUE, x, y, speed, angle);
        assertEquals(10f, ball.getX(), 0.001);
        float resx = x + speed * (float) Math.cos(angle) * delta;
        float resy = y + speed * (float) Math.sin(angle) * delta;
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
        assertEquals(false, ball.isDead());
        ball.update(5);
        assertEquals(false, ball.isDead());
        ball.update(20);
        assertEquals(true, ball.isDead());
    }
    
    /**
     * Hit test.
     */
    @Test
    public void hitTest() {
        Ball ball = new ColoredBall(BallType.BLUE, 100, 100, 4, 6.4f);
        
        Circle circleHit = new Circle(100, 100, 10);
        Circle circleMiss = new Circle(0, 0, 10);
        assertEquals(true, ball.doesHit(circleHit));
        assertEquals(false, ball.doesHit(circleMiss));
        
        Circle circleNextTo = new Circle(100, 90, 10);
        assertEquals(true, ball.isNextTo(circleNextTo));
    }
    
    /**
     * Pop test.
     */
    /*
    @Test
    public void popTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        
        for (int i = BallType.BLUE.ordinal(); i < BallType.MAX_COLORS.ordinal(); i++) {
            Ball ball = new ColoredBall(BallType.values()[i], 100, 100, 4, 6.4f);
            assertEquals(false, ball.popDone());
            ball.popStart();
            ball.draw(batchMock, 0);
            assertEquals(false, ball.popDone());
            ball.popStart();
            ball.draw(batchMock, 100000);
            assertEquals(true, ball.popDone());
        }
    }
    */
    
    /* TODO fix audiomanager to have error handling
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        
        for (int i = Ball.BLUE; i < Ball.MAX_COLORS; i++) {
            Ball ball = new Ball(i, 100, 100, 10, 4, 6.4f);
            ball.draw(batchMock, 1);
        }
    }
    */
    
    /**
     * Adds the graph null test.
     */
    @Test(expected=NullPointerException.class)
    public void addGraphNullTest() {
        BallGraph graphMock = mock(BallGraph.class);
        Ball ball = new ColoredBall(BallType.BLUE, 100, 100, 4, 6.4f);
        ball.addToGraph(graphMock);
        ball.addToGraph(null);
    }
    
    /**
     * Delete graph null test.
     */
    @Test(expected=NullPointerException.class)
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
        assertEquals(true, ball1.isEqual(ball2));
        assertEquals(false, ball1.isEqual(ball3));
        Ball ball4 = new BombBall(50, 50, 0, 0);
        Ball ball5 = new BombBall(50, 80, 0, 0);
        assertEquals(true, ball4.isEqual(ball5));
        assertEquals(false, ball4.isEqual(ball3));
        assertEquals(false, ball3.isEqual(ball4));
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
