package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.settings.DynamicSettings;

public class BallManagerTest {
    
    DynamicSettings ds = new DynamicSettings();
    Cannon cannonMock = mock(Cannon.class);
    BallGraph bgMock = mock(BallGraph.class);
    ScoreKeeper skMock = mock(ScoreKeeper.class);
    
    BallManager ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
    Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
    
    @Test
    public void testBallCheckDeadNull() {
        ballManager.ballCheckDead(null);
    }
    
    @Test
    public void testBallCheckDeadNo() {
        ballManager.ballCheckDead(ball);
    }
    
    @Test
    public void testBallCheckDeadYes() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        Ball ballDead = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        ballDead.update(1000);
        ballManager.ballCheckDead(ballDead);
        ballManager.ballCleanDead();
    }

    @Test
    public void testCleanStaticDead() {
        Ball ball1 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            ballManager.increaseColorList();
        }
        
        BallsStatic bs = ballManager.getBallsStaticManager();
        bs.addDeadBall(ball1);
        bs.addDeadBall(ball2);
        
        ballManager.increaseColorList();
        ballManager.addBallDeadlist(ball1);
        ballManager.addBallDeadlist(ball2);
        
        ballManager.cleanStaticDead();
    }
    
    @Test
    public void testCleanStaticDeadEmpty() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.cleanStaticDead();
    }

    @Test
    public void testAddStaticBalls() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            ballManager.increaseColorList();
        }
        
        ballManager.addStaticBallToBeAdded(ball);
        ballManager.increaseColorList();
        ballManager.addStaticBalls();
    }
    
    @Test
    public void testAddStaticBallsEmpty() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.addStaticBalls();
    }
    
    @Test
    public void testBallHitBallNull() {
        ballManager.ballHitBall(null, false, 0);
    }
    
    @Test
    public void testBallHitBallFalse() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.ballHitBall(ball, false, 0);
    }
    
    @Test
    public void testBallHitBallTrue() {
        Ball ball1 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.getBallsStaticManager().add(ball1);
        ballManager.ballHitBall(ball2, false, 0);
    }

    @Test
    public void testShootBallEmpty() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.shootBall();
    }
    
    @Test
    public void testShootBall() {
        ballManager = new BallManager(ds, bgMock, cannonMock, skMock);
        ballManager.getBallsCannonManager().add(ball);
        assertEquals(ballManager.getBallsCannonManager().size(), 1);
        ballManager.shootBall();
        assertEquals(ballManager.getBallsCannonManager().size(), 0);
    }

    @Test
    public void testUpdateNull() {
        ballManager.update(null, 0);
    }
    
    @Test
    public void testUpdate() {
        ballManager.update(null, 0);
        SpriteBatch batchMock = mock(SpriteBatch.class);
        ballManager.update(batchMock, 0);
    }

    @Test
    public void testMoveRowDown() {
        ballManager.moveRowDown();
    }

    @Test
    public void testHitsBottom() {
        ballManager.hitsBottom();
    }

    @Test
    public void testGetBallList() {
        assertNotEquals(ballManager.getBallList(), null);
    }

    @Test
    public void testAddBallDeadlistNull() {
        ballManager.addBallDeadlist(null);
    }
    
    @Test
    public void testAddBallDeadlist() {
        ballManager.addBallDeadlist(ball);
    }

    @Test
    public void testAddStaticBallToBeAddedNull() {
        ballManager.addStaticBallToBeAdded(null);
    }
    
    @Test
    public void testAddStaticBallToBeAdded() {
        ballManager.addStaticBallToBeAdded(ball);
    }

    @Test
    public void testGetNumberOfShotBalls() {
        assertTrue(ballManager.getNumberOfShotBalls() >= 0);
    }

    @Test
    public void testGetNumberOfPoppingBalls() {
        assertTrue(ballManager.getNumberOfPoppingBalls() >= 0);
    }

    @Test
    public void testGetNumberOfCannonBalls() {
        assertTrue(ballManager.getNumberOfCannonBalls() >= 0);
    }

    @Test
    public void testIncreaseColorList() {
        ballManager.increaseColorList();
    }

    @Test
    public void testCheckPop() {
        ballManager.checkPop();
    }

}
