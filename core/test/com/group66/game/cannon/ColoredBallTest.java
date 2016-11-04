package com.group66.game.cannon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;

public class ColoredBallTest extends BallTest {
    @Before
    public void setupTests() {
        HeadlessNativesLoader.load();
        MockGraphics mockGraphics = new MockGraphics();
        Gdx.graphics = mockGraphics;
        HeadlessNet headlessNet = new HeadlessNet();
        Gdx.net = headlessNet;
        HeadlessFiles headlessFiles = new HeadlessFiles();
        Gdx.files = headlessFiles;
        Gdx.gl = mock(GL20.class);
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    @Test
    public void testIsEqual() {
        ColoredBall ball1 = new ColoredBall(BallType.RED, 1.2f, 2.3f, 4, 5.6f);
        ColoredBall ball2 = new ColoredBall(BallType.BLUE, 1.2f, 2.3f, 4, 5.6f);
        
        assertTrue(ball1.isEqual(ball1));
        assertFalse(ball1.isEqual(null));
        assertFalse(ball1.isEqual(ball2));
    }
    
    @Test
    public void drawNullTest() {
        ColoredBall red = new ColoredBall(BallType.RED, 1.2f, 2.3f, 4, 5.6f);
        red.draw(null, 0);
    }
}
