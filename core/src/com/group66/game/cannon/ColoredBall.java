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
     * @param rad the radius of the Ball
     * @param speed the speed of the Ball
     * @param angle the angle of the Ball
     */
    public ColoredBall(int color, float xpos, float ypos, float rad, int speed, float angle) {
        super(getEnum(color), xpos, ypos, rad, speed, angle);
    }
    
    /**
     * Instantiates a new ball.
     * 
     * @param type the color defined by Enum
     * @param xpos the x coordinate of the Ball
     * @param ypos the y coordinate of the Ball
     * @param rad the radius of the Ball
     * @param speed the speed of the Ball
     * @param angle the angle of the Ball
     */
    public ColoredBall(BallType type, float xpos, float ypos, float rad, int speed, float angle) {
        super(type, xpos, ypos, rad, speed, angle);
    }

    /**
     * generates the right Enum value of BallType
     * 
     * @param color
     * @return the right balltype for the color
     */
    private static BallType getEnum(int color) {
        BallType type = BallType.BLUE;
        color %= MAX_COLORS;
        if (color == BLUE) {
            type = BallType.BLUE;
        }
        if (color == GREEN) {
            type = BallType.GREEN;
        }
        if (color == RED) {
            type = BallType.RED;
        }
        if (color == YELLOW) {
            type = BallType.YELLOW;
        }

        return type;
    }
    
    /**
     * Checks if two balls have the same color or are of the same type.
     * 
     * @param ball the ball which type needs to be compared
     * @return Boolean value that indicates wheter the types are the same
     */
    public Boolean isEqual(Ball ball) {
        if (this.getType().equals(ball.getType())) {
            return true;
        }
        return false;
    }


}
