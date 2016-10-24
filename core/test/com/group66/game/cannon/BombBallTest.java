package com.group66.game.cannon;

import org.junit.Test;

public class BombBallTest extends BallTest {
    @Test
    public void creationTest() {
        new BombBall(0, 0, 0, 0);
    }

    @Override
    protected Ball getBall() {
        return new BombBall(1.2f, 2.3f, 4, 5.6f);
    }
}
