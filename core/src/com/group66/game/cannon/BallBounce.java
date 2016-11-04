package com.group66.game.cannon;

import com.group66.game.BustaMove;
import com.group66.game.helpers.AudioManager;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;

/**
 * A helper Class to manage the Ball bouncing of the edges.
 */
public class BallBounce {

    /**
     * Bounce the ball of the edges if needed.
     *
     * @param ball the ball
     */
    public static void bounceEdge(Ball ball, boolean isSplit, int segmentOffset) {
        if (ball == null) {
            return;
        }
        
        /* Check if an edge is hit */
        int left = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            left = Config.SEGMENT_OFFSET * segmentOffset + Config.BORDER_SIZE_SIDES;
        }
        int right = left + Config.LEVEL_WIDTH;

        if (ball.getX() - Config.BALL_RAD <= left
                && Math.toDegrees(ball.getAngle()) > 90) {
            // LEFT EDGE
            ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
            try {
                AudioManager.wallhit();
            } catch (NullPointerException e) {
                
            }
            BustaMove.getGameInstance().log(MessageType.Info, "Ball hit the wall");
        } else if (ball.getX() + Config.BALL_RAD >= right
                && Math.toDegrees(ball.getAngle()) < 90) {
            // RIGHT EDGE
            ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
            try {
                AudioManager.wallhit();
            } catch (NullPointerException e) {
                
            }
            BustaMove.getGameInstance().log(MessageType.Info, "Ball hit the wall");
        }
    }
}
