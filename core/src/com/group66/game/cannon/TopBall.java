package com.group66.game.cannon;

import org.hamcrest.core.IsEqual;

public class TopBall extends Ball {

    /**
     * Creates a new top ball object
     * @param type
     * @param xpos
     * @param ypos
     * @param speed
     * @param angle
     */
    public TopBall(BallType type, float xpos, float ypos, int speed, float angle) {
        super(type, xpos, ypos, speed, angle);
    }

    /**
     * Checks if two balls of of the same type.
     *
     * @param ball to compare
     * @return Boolean whether balls are of the same type
     */
    public Boolean isEqual(Ball ball) {
        return false;
    }

}
