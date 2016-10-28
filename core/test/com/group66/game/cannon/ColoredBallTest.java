package com.group66.game.cannon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ColoredBallTest extends BallTest {
    @Test
    public void creationTest() {
        new ColoredBall(BallType.RED, 0, 0, 0, 0);
    }
    
    @Test
    public void nullTest() {
        new ColoredBall(null, 0, 0, 0, 0);
    }

    @Override
    protected Ball getBall() {
        return new ColoredBall(BallType.RED, 1.2f, 2.3f, 4, 5.6f);
    }

    @Test
    public void popTest() {
        SpriteBatch batchMock = mock(SpriteBatch.class);
        
        ColoredBall red = new ColoredBall(BallType.RED, 1.2f, 2.3f, 4, 5.6f);
        red.draw(batchMock, 1f);
        assertFalse(red.popDone());
        red.popStart();
        red.draw(batchMock, 1f);
        assertTrue(red.popDone());
    }
    
    @Test
    public void colorTest() {
        new ColoredBall(BallType.RED, 1.2f, 2.3f, 4, 5.6f);
        new ColoredBall(BallType.GREEN, 1.2f, 2.3f, 4, 5.6f);
        new ColoredBall(BallType.BLUE, 1.2f, 2.3f, 4, 5.6f);
        new ColoredBall(BallType.YELLOW, 1.2f, 2.3f, 4, 5.6f);
    }
    
    @Test
    public void colorBombTest() {
        new ColoredBall(BallType.BOMB, 1.2f, 2.3f, 4, 5.6f);
    }
}
