package com.group66.game.cannon;

import org.junit.Test;

public class BallTypeTest {
    @Test
    public void creationTest() {
        @SuppressWarnings("unused")
        BallType blue = BallType.BLUE;
    }
    
    @Test
    public void blueTest() {
        BallType type = BallType.BLUE;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }
    
    @Test
    public void greenTest() {
        BallType type = BallType.GREEN;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }

    @Test
    public void redTest() {
        BallType type = BallType.RED;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }

    @Test
    public void yellowTest() {
        BallType type = BallType.YELLOW;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }

    @Test
    public void bombTest() {
        BallType type = BallType.BOMB;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }

    @Test
    public void maxTest() {
        BallType type = BallType.MAX_COLORS;
        type.newBall(0, 0, 0, 0);
        type.getAnimation();
        type.getPopAnimation();
    }

}
