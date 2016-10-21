package com.group66.game.cannon;

import com.group66.game.cannon.Ball;
/**
 * A basic Ball class.
 */
public class ColoredBall extends Ball {



    /**
     * Instantiates a new ball.
     * 
     * @param color the color can not be or exceed MAX_COLORS
     * @param xpos the x coordinate of the Ball
     * @param ypos the y coordinate of the Ball
     * @param speed the speed of the Ball
     * @param angle the angle of the Ball
     */
    public ColoredBall(BallType type, float xpos, float ypos, int speed, float angle) {
        super(type, xpos, ypos, speed, angle);
    }
    
    /**
     * Checks if two balls have the same color or are of the same type.
     * 
     * @param ball the ball which type needs to be compared
     * @return Boolean value that indicates whether the types are the same
     */
    public Boolean isEqual(Ball ball) {
        if (ball instanceof ColoredBall && this.getType().equals(ball.getType())) {
            return true;
        }
        return false;
    }


}
