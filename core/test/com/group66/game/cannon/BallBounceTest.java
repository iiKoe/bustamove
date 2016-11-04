package com.group66.game.cannon;

import static org.junit.Assert.*;

import org.junit.Test;

import com.group66.game.settings.Config;

public class BallBounceTest {
    
    @Test
    public void testBounceEdgeNull() {
        BallBounce.bounceEdge(null, false, 0);
    }
    
    @Test
    public void testBounceNoHit() {
        int left = Config.SINGLE_PLAYER_OFFSET;
        
        Ball ballNoEdge = new ColoredBall(BallType.BLUE, left + Config.BALL_DIAM * 2, 10f, 0, (float) Math.toRadians(100));
        BallBounce.bounceEdge(ballNoEdge, false, 0);
        assertEquals(ballNoEdge.getAngle(), Math.toRadians(100), 0.01);
        
    }

    @Test
    public void testBounceEdgeNoSplit() {
        int left = Config.SINGLE_PLAYER_OFFSET;
        int right = left + Config.LEVEL_WIDTH;
        
        Ball ballHitEdgeL = new ColoredBall(BallType.BLUE, left, 10f, 0, (float) Math.toRadians(100));
        BallBounce.bounceEdge(ballHitEdgeL, false, 0);
        assertNotEquals(ballHitEdgeL.getAngle(), Math.toRadians(100), 0.01);
        
        Ball ballHitEdgeR = new ColoredBall(BallType.BLUE, right, 10f, 0, (float) Math.toRadians(10));
        BallBounce.bounceEdge(ballHitEdgeR, false, 0);
        assertNotEquals(ballHitEdgeR.getAngle(), Math.toRadians(10), 0.01);
    }
    
    @Test
    public void testBounceEdgeOUB() {
        int left = Config.SINGLE_PLAYER_OFFSET;
        int right = left + Config.LEVEL_WIDTH;
        
        Ball ballHitEdgeL = new ColoredBall(BallType.BLUE, left, 10f, 0, (float) Math.toRadians(10));
        BallBounce.bounceEdge(ballHitEdgeL, false, 0);
        
        Ball ballHitEdgeR = new ColoredBall(BallType.BLUE, right, 10f, 0, (float) Math.toRadians(100));
        BallBounce.bounceEdge(ballHitEdgeR, false, 0);
    }
    
    @Test
    public void testBounceEdgeSplit() {
        int left = Config.SEGMENT_OFFSET + Config.BORDER_SIZE_SIDES;
        int right = left + Config.LEVEL_WIDTH;
        
        Ball ballHitEdgeL = new ColoredBall(BallType.BLUE, left, 10f, 0, (float) Math.toRadians(100));
        BallBounce.bounceEdge(ballHitEdgeL, true, 1);
        assertNotEquals(ballHitEdgeL.getAngle(), Math.toRadians(100), 0.01);
        
        Ball ballHitEdgeR = new ColoredBall(BallType.BLUE, right, 10f, 0, (float) Math.toRadians(10));
        BallBounce.bounceEdge(ballHitEdgeR, true, 1);
        assertNotEquals(ballHitEdgeR.getAngle(), Math.toRadians(10), 0.01);
    }
}
