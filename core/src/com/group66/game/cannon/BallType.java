package com.group66.game.cannon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.group66.game.BustaMove;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.logging.MessageType;

public enum BallType {
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
    
    public abstract Ball newBall(float xpos, float ypos, int speed, float angle);
    public abstract Animation getAnimation();
    public abstract Animation getPopAnimation();
}
