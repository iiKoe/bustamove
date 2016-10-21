package com.group66.game.cannon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.group66.game.BustaMove;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.logging.MessageType;

/**
 * The Enum BallType.
 */
public enum BallType {
    
    /** The blue. */
    BLUE {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }

        @Override
        public Animation getAnimation() {
            return AssetLoader.getBlueAnimation();
        }

        @Override
        public Animation getPopAnimation() {
            return AssetLoader.getBluePopAnimation();
        }
    },
    
    /** The green. */
    GREEN {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }

        @Override
        public Animation getAnimation() {
            return AssetLoader.getGreenAnimation();
        }

        @Override
        public Animation getPopAnimation() {
            return AssetLoader.getGreenPopAnimation();
        }
    },
    
    /** The red. */
    RED {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }

        @Override
        public Animation getAnimation() {
            return AssetLoader.getRedAnimation();
        }

        @Override
        public Animation getPopAnimation() {
            return AssetLoader.getRedPopAnimation();
        }
    },
    
    /** The yellow. */
    YELLOW {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }

        @Override
        public Animation getAnimation() {
            return AssetLoader.getYellowAnimation();
        }

        @Override
        public Animation getPopAnimation() {
            return AssetLoader.getYellowPopAnimation();
        }
    },
    
    /** The bomb. */
    BOMB {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new BombBall(xpos, ypos, speed, angle);
        }

        @Override
        public Animation getAnimation() {
            return null;
        }

        @Override
        public Animation getPopAnimation() {
            return null;
        }
    },
    
    /** The max colors. */
    MAX_COLORS {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            BustaMove.getGameInstance().log(MessageType.Error, "An invallid ball request in BallType");
            return null;
        }

        @Override
        public Animation getAnimation() {
            return null;
        }

        @Override
        public Animation getPopAnimation() {
            return null;
        }
    };
    
    /**
     * New ball.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @param speed the speed
     * @param angle the angle
     * @return the ball
     */
    public abstract Ball newBall(float xpos, float ypos, int speed, float angle);
    
    /**
     * Gets the static animation.
     *
     * @return the animation
     */
    public abstract Animation getAnimation();
    
    /**
     * Gets the pop animation.
     *
     * @return the pop animation
     */
    public abstract Animation getPopAnimation();
}
