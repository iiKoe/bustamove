package com.group66.game.cannon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Class CannonTest.
 */
public class CannonTest {
    
    /**
     * Cannon test.
     */
    @Test(expected = NullPointerException.class)
    public void cannonTest() {
        @SuppressWarnings("unused")
        Cannon cannon = new Cannon(null, 0, 0, 0, 0, 0, 180);
    }

    /**
     * Min max angle test.
     */
    @Test
    public void setAngleTest() {
        Texture textureMock = mock(Texture.class);
        
        Cannon cannon = new Cannon(textureMock, 0, 0, 0, 0, 0, 180);
        assertNotNull(cannon);
        cannon.setAngle(10);
        assertEquals(10f, cannon.getAngle(), 0.1);
        cannon.setAngle(180);
        assertEquals(180, cannon.getAngle(), 0.1);
        cannon.setAngle(180);
        assertEquals(180, cannon.getAngle(), 0.1);
        cannon.setAngle(181);
        assertEquals(180, cannon.getAngle(), 0.1);
        cannon.setAngle(-1);
        assertEquals(0, cannon.getAngle(), 0.1);
    }
    
    /**
     * Cannon aim adjust test.
     */
    @Test
    public void cannonAimAdjustTest() {
        Texture textureMock = mock(Texture.class);
        
        Cannon cannon = new Cannon(textureMock, 0, 0, 0, 0, -1, 180);
        assertNotNull(cannon);
        cannon.setAngle(100);
        cannon.cannonAimAdjust(-10);
        assertEquals(90, cannon.getAngle(), 0.1);
        cannon.cannonAimAdjust(-90);
        assertEquals(0, cannon.getAngle(), 0.1);
        cannon.setAngle(100);
        cannon.cannonAimAdjust(-200);
        assertEquals(-1, cannon.getAngle(), 0.1);
        cannon.cannonAimAdjust(+200);
        assertEquals(180, cannon.getAngle(), 0.1);
    }
    
    /**
     * Draw null test.
     */
    @Test
    public void drawNullTest() {
        Texture textureMock = mock(Texture.class);
        Cannon cannon = new Cannon(textureMock, 0, 0, 0, 0, -1, 180);
        cannon.draw(null);
    }
    
    /**
     * Draw test.
     */
    @Test
    public void drawTest() {
        Texture textureMock = mock(Texture.class);
        SpriteBatch batchMock = mock(SpriteBatch.class);
        Cannon cannon = new Cannon(textureMock, 0, 0, 0, 0, -1, 180);
        cannon.draw(batchMock);
    }
}
