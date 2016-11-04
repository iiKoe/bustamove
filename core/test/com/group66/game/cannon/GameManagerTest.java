package com.group66.game.cannon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.screens.AbstractGameScreen;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class GameManagerTest {
    
    DynamicSettings dynamicSettings = new DynamicSettings();
    GameManager gameManager;
    
    @Test
    public void testGameManagerDynamicSettingsNull() {
        gameManager = new GameManager(null);
    }

    @Test
    public void testGameManagerDynamicSettings() {
        gameManager = new GameManager(dynamicSettings);
    }

    @Test
    public void testGameManagerIntDynamicSettings() {
        gameManager = new GameManager(1, dynamicSettings);
    }

    @Test
    public void testShootBall() {
        GameManager gameManager = new GameManager(dynamicSettings);
        gameManager.shootBall();
        Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        gameManager.getBallManager().getBallsCannonManager().add(ball);
        gameManager.shootBall();
    }

    @Test
    public void testCanShoot() {
        Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        
        gameManager = new GameManager(dynamicSettings);
        assertFalse(gameManager.canShoot());
        gameManager.getBallManager().getBallsCannonManager().add(ball);
        assertTrue(gameManager.canShoot());
        
        gameManager.shootBall();
        assertFalse(gameManager.canShoot());
    }

    @Test
    public void testMoveRowDown() {
        gameManager = new GameManager(dynamicSettings);
        gameManager.moveRowDown();
    }

    @Test
    public void testIsGameOver() {
        gameManager = new GameManager(dynamicSettings);
        assertFalse(gameManager.isGameOver());
    }

    @Test
    public void testIsGameComplete() {
        gameManager = new GameManager(dynamicSettings);
        assertTrue(gameManager.isGameComplete());
    }
    
    @Test
    public void testShiftCloneNull() {
        gameManager = new GameManager(dynamicSettings);
        gameManager.shiftClone(null);
    }

    @Test
    public void testShiftClone() {
        Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        GameManager gameManager1 = new GameManager(dynamicSettings);
        gameManager1.getBallManager().getBallsStaticManager().add(ball);
        assertEquals(gameManager1.getBallManager().getBallsStaticManager().aliveSize(), 1);
        GameManager gameManager2 = new GameManager(dynamicSettings);
        assertEquals(gameManager2.getBallManager().getBallsStaticManager().aliveSize(), 0);
        gameManager2.shiftClone(gameManager1);
        assertEquals(gameManager2.getBallManager().getBallsStaticManager().aliveSize(), 1);
    }

    @Test
    public void testBallCheckRoofNull() {
        gameManager = new GameManager(dynamicSettings);
        gameManager.ballCheckRoof(null);
    }
    
    @Test
    public void testBallCheckRoof() {
        gameManager = new GameManager(dynamicSettings);
        Ball ball1 = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        Ball ball2 = new ColoredBall(BallType.BLUE, gameManager.getRoofHitbox().getX(), 
                gameManager.getRoofHitbox().getY(), 0, 0);
        gameManager.ballCheckRoof(ball1);
        gameManager.ballCheckRoof(ball2);
    }

    @Test
    public void testMoveBallsDown() {
        gameManager = new GameManager(dynamicSettings);
        gameManager.moveBallsDown();
        gameManager.setBallCount(Config.NBALLS_ROW_DOWN);
        gameManager.moveBallsDown();
        
        Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        gameManager.getBallManager().getBallsCannonManager().add(ball);

        gameManager.moveBallsDown();
    }

    @Test
    public void testUpdate() {
        gameManager = new GameManager(dynamicSettings);
        Ball ball = new ColoredBall(BallType.BLUE, 50, 50, 0, 0);
        gameManager.getBallManager().getBallList().add(ball);
        gameManager.update(0);
    }

    @Test
    public void drawNullTest() {
        gameManager = new GameManager(dynamicSettings);
        gameManager.draw(null, null, 0);
    }
    
    @Test
    public void drawTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        AbstractGameScreen screenMock = mock(AbstractGameScreen.class);
        gameManager = new GameManager(dynamicSettings);
        gameManager.draw(screenMock, batchMock, 0);
        
        gameManager = new GameManager(1, dynamicSettings);
        gameManager.draw(screenMock, batchMock, 0);
    }

}
