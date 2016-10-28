package com.group66.game.cannon;

import org.junit.Test;

public class TopBallTest extends BallTest {
    @Test
    public void creationTest() {
        new TopBall(BallType.MAX_COLORS, 0, 0, 0, 0);
    }
    
    @Test
    public void nullTest() {
        new TopBall(null, 0, 0, 0, 0);
    }

    @Override
    protected Ball getBall() {
        return new TopBall(BallType.MAX_COLORS, 1.2f, 2.3f, 4, 5.6f);
    }
}
