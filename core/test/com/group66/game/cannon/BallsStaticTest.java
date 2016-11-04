package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class BallsStaticTest {
    
    DynamicSettings ds = new DynamicSettings();
    BallGraph bgMock = mock(BallGraph.class);
    static ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();
    
    Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
    BallsStatic ballsStatic = new BallsStatic(ds, bgMock, colorList);
    
    @BeforeClass
    public static void setUp() {
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            colorList.add(new AtomicInteger(0));
        }
    }

    @Test
    public void testAddNull() {
        ballsStatic.add(null);
    }
    
    @Test
    public void testAddRemove() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        
        assertEquals(ballsStatic.aliveContains(ball), false);
        ballsStatic.add(ball);
        assertEquals(ballsStatic.aliveSize(), 1);
        assertEquals(ballsStatic.deadSize(), 0);
        assertEquals(ballsStatic.aliveContains(ball), true);
        assertEquals(ballsStatic.deadContains(ball), false);
        
        ballsStatic.addDeadBall(ball);
        assertEquals(ballsStatic.aliveContains(ball), true);
        assertEquals(ballsStatic.deadContains(ball), true);
        
        assertEquals(ballsStatic.getLastStaticBall(), ball);
        assertEquals(ballsStatic.getLastStaticBall(), ball);
        assertEquals(ballsStatic.getFirstDeadBall(), ball);
        
        ballsStatic.removeStaticBall(ball);
        assertEquals(ballsStatic.aliveContains(ball), false);
        
        ballsStatic.removeFirstDeadBall();
        assertEquals(ballsStatic.deadContains(ball), false);
    }

    @Test
    public void testIsEmpty() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertEquals(ballsStatic.isEmpty(), true);
        ballsStatic.add(ball);
        assertEquals(ballsStatic.isEmpty(), false);
    }

    @Test
    public void testAliveContainsNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertEquals(ballsStatic.aliveContains(null), false);
    }

    @Test
    public void testDeadContainsNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertEquals(ballsStatic.deadContains(null), false);
    }

    @Test
    public void testGetFirstDeadBallNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertEquals(ballsStatic.getFirstDeadBall(), null);
    }

    @Test
    public void testAddDeadBallNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.addDeadBall(null);
    }

    @Test
    public void testRemoveStaticBallNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.removeStaticBall(null);
    }

    @Test
    public void testRemoveFirstDeadBall0() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.removeFirstDeadBall();
    }

    @Test
    public void testGetLastStaticBall0() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.getLastStaticBall();
    }

    @Test
    public void testHitsStaticBallTrue() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        Ball ball1 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        ballsStatic.add(ball1);
        assertEquals(ballsStatic.hitsStaticBall(ball2), ball1);
    }
    
    @Test
    public void testHitsStaticBallFalse() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        Ball ball1 = new ColoredBall(BallType.BLUE, 50 + 2*Config.BALL_DIAM, 50 + 2*Config.BALL_DIAM, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        ballsStatic.add(ball1);
        assertNotEquals(ballsStatic.hitsStaticBall(ball2), ball1);
    }
    
    @Test
    public void testHitsStaticBallNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertEquals(ballsStatic.hitsStaticBall(null), null);
    }
    
    @Test
    public void testAddStaticBallBallTypeFloatFloatNull() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.addStaticBall(null, 100, 100);
        assertEquals(ballsStatic.aliveSize(), 0);
    }


    @Test
    public void testAddStaticBallBallTypeFloatFloat() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.addStaticBall(BallType.BLUE, 100, 100);
        assertEquals(ballsStatic.aliveSize(), 1);
    }

    @Test
    public void testAddStaticBallIntFloatFloat() {        
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.addStaticBall(0, 100, 100);
        assertEquals(ballsStatic.aliveSize(), 1);
    }

    @Test
    public void testAddRandomStaticBall() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.addRandomStaticBall(100, 100);
        assertEquals(ballsStatic.aliveSize(), 1);
    }

    @Test
    public void testGetBallStaticList() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        assertNotEquals(ballsStatic.getBallStaticList(), null);
    }

    @Test
    public void testMoveRowDown() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        ballsStatic.add(ball);
        float y = ball.getY();
        float x = ball.getX();
        ballsStatic.moveRowDown();
        assertNotEquals(ball.getY(), y);
        assertEquals(ball.getX(), x, 0.01);
    }

    @Test
    public void testHitsBotom() {
        ballsStatic = new BallsStatic(ds, bgMock, colorList);
        
        Ball noBotomBall = new ColoredBall(BallType.BLUE, 0, Config.BORDER_SIZE_BOT
                + 2 * Config.BALL_DIAM, 0, 0);
        ballsStatic.add(noBotomBall);
        assertEquals(ballsStatic.hitsBottom(), false);
        
        Ball botomBall = new ColoredBall(BallType.BLUE, 0, Config.BORDER_SIZE_BOT, 0, 0);
        ballsStatic.add(botomBall);
        assertEquals(ballsStatic.hitsBottom(), true);
    }
    
    @Test
    public void drawNullTest() {
        ballsStatic.draw(null, 0);
    }
    
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        ballsStatic.add(ball);
        ballsStatic.draw(batchMock, 0);
    }

}
