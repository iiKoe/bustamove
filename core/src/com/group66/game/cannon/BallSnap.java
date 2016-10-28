package com.group66.game.cannon;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;

/**
 * The Class BallSnap.
 */
public class BallSnap {

    /**
     * Snap ball to grid. TODO Make less HACKY
     *
     * @param ball the ball
     * @param hitb the hitball
     * @param isSplit the is split
     * @param segmentOffset the segment offset
     */
    public static void snapBallToGrid(Ball ball, Ball hitb, boolean isSplit, int segmentOffset) {
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }

        float hitx = hitb.getX();
        float hity = hitb.getY();

        float o1x = hitx - Config.BALL_DIAM;
        float o1y = hity;

        float o2x = hitx - Config.BALL_RAD;
        float o2y = hity - Config.BALL_DIAM;

        float o3x = hitx + Config.BALL_RAD;
        float o3y = hity - Config.BALL_DIAM;

        float o4x = hitx + Config.BALL_DIAM;
        float o4y = hity;

        float o5x = hitx + Config.BALL_RAD;
        float o5y = hity + Config.BALL_DIAM;

        float o6x = hitx - Config.BALL_RAD;
        float o6y = hity + Config.BALL_DIAM;

        float origx = ball.getX();
        float origy = ball.getY();

        float do1 = Math.abs(origx - o1x) + Math.abs(origy - o1y);
        float do2 = Math.abs(origx - o2x) + Math.abs(origy - o2y);
        float do3 = Math.abs(origx - o3x) + Math.abs(origy - o3y);
        float do4 = Math.abs(origx - o4x) + Math.abs(origy - o4y);
        float do5 = Math.abs(origx - o5x) + Math.abs(origy - o5y);
        float do6 = Math.abs(origx - o6x) + Math.abs(origy - o6y);

        // Check bounds
        float redge = xoffset + Config.LEVEL_WIDTH - Config.BALL_RAD;
        if (o4x > redge) {
            do4 = Float.MAX_VALUE;
        }
        if (o3x > redge) {
            do3 = Float.MAX_VALUE;
        }
        if (o5x > redge) {
            do5 = Float.MAX_VALUE;
        }

        float ledge = xoffset + Config.BALL_RAD;
        if (o1x < ledge) {
            do1 = Float.MAX_VALUE;
        }
        if (o2x < ledge) {
            do2 = Float.MAX_VALUE;
        }
        if (o6x < ledge) {
            do6 = Float.MAX_VALUE;
        }

        // Check best option
        if (do1 < do2 && do1 < do3 && do1 < do4 && do1 < do5 && do1 < do6) {
            ball.setX(o1x);
            ball.setY(o1y);
        } else if (do2 < do1 && do2 < do3 && do2 < do4 && do2 < do5 && do2 < do6) {
            ball.setX(o2x);
            ball.setY(o2y);
        } else if (do3 < do1 && do3 < do2 && do3 < do4 && do3 < do5 && do3 < do6) {
            ball.setX(o3x);
            ball.setY(o3y);
        } else if (do4 < do1 && do4 < do2 && do4 < do3 && do4 < do5 && do4 < do6) {
            ball.setX(o4x);
            ball.setY(o4y);
        } else if (do5 < do1 && do5 < do2 && do5 < do3 && do5 < do4 && do5 < do6) {
            ball.setX(o5x);
            ball.setY(o5y);
        } else if (do6 < do1 && do6 < do2 && do6 < do3 && do6 < do4 && do6 < do5) {
            ball.setX(o6x);
            ball.setY(o6y);
        }
    }

    /**
     * Snap ball to roof.
     *
     * @param ball the ball
     * @param roofy the roofy
     * @param isSplit the is split
     * @param segmentOffset the segment offset
     */
    public static void snapBallToRoof(Ball ball, float roofy, boolean isSplit, int segmentOffset) {
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }

        //distance to the left side of the screen
        float distToLeft = ball.getX() - xoffset - Config.BALL_RAD;
        //float value between 0-7 for the estimated index
        float posIndexEst = distToLeft / Config.BALL_DIAM;
        //make float into actual 0-7 value (round and clamp)
        int posIndex = Math.max(0, Math.min(7, Math.round(posIndexEst)));
        //define new x position
        float newx = xoffset + Config.BALL_RAD + posIndex * Config.BALL_DIAM;
        ball.setX(newx);
        ball.setY(roofy - Config.BALL_RAD);

        BustaMove.getGameInstance().log(MessageType.Info, "Ball snapped into place");
    }
}
