package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.settings.DynamicSettings;

public class BallsCannonTest {
       
    DynamicSettings ds = new DynamicSettings();
    BallGraph bgMock = mock(BallGraph.class);
    Cannon cannonMock = mock(Cannon.class);
    ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();
    
    Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
    BallsCannon ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);

    @Test
    public void testAddNull() {
        ballsCannon.add(null);
    }
    
    @Test
    public void testEmpty() {
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        assertEquals(ballsCannon.isEmpty(), true);
        ballsCannon.add(ball);
        assertEquals(ballsCannon.isEmpty(), false);
    }
    
    @Test
    public void testGetFirstEmpty() {
        Ball compBall = ballsCannon.getFirst();
        assertEquals(ballsCannon.size(), 0);
        assertEquals(null, compBall);
    }
    
    @Test
    public void testAdd() {
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        ballsCannon.add(ball);
        assertEquals(ballsCannon.size(), 1);
    }

    @Test
    public void testGetFirst() {
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        ballsCannon.add(ball);
        assertEquals(ballsCannon.size(), 1);
        Ball compBall = ballsCannon.getFirst();
        assertEquals(ballsCannon.size(), 1);
        assertEquals(ball, compBall);
    }

    @Test
    public void testRemoveFirst() {
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        ballsCannon.add(ball);
        assertEquals(ballsCannon.size(), 1);
        ballsCannon.removeFirst();
        assertEquals(ballsCannon.size(), 0);
    }
    
    @Test
    public void testRemoveFirstEmpty() {
        ballsCannon.removeFirst();
        assertEquals(ballsCannon.size(), 0);
    }
    
    @Test
    public void drawNullTest() {
        ballsCannon.draw(null, 0);
    }
    
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        ballsCannon.add(ball);
        ballsCannon.draw(batchMock, 0);
    }

    @Test
    public void testAddRandomBallToCanonBomb() {
        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            colorList.add(new AtomicInteger(0));
        }
        
        int size = ballsCannon.size();
        ds.setSpecialBombChanceMultiplier(100, false);
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        ballsCannon.addRandomBallToCanon();
        assertEquals(size+1, ballsCannon.size());
    }
    
    @Test
    public void testAddRandomBallToCanon() {
        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            colorList.add(new AtomicInteger(0));
        }
        
        int size = ballsCannon.size();
        ds.setSpecialBombChanceMultiplier(-1, false);
        ballsCannon = new BallsCannon(ds, bgMock, cannonMock, colorList);
        ballsCannon.addRandomBallToCanon();
        assertEquals(size+1, ballsCannon.size());
    }

}
