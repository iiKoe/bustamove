package com.group66.game.cannon;

public class BombBall extends Ball {

    public BombBall(float xpos, float ypos, float rad, int speed, float angle) {
        super(BallType.BOMB, xpos, ypos, rad, speed, angle);
    }
    
    public Boolean isEqual(Ball ball) {
        if (ball.getType().equals(this.getType())) {
            return true;
        }
        
        return false;
    }

}
