package com.group66.game.cannon;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;

public enum BallType {
    BLUE {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }
    },
    GREEN {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }
    },
    RED {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }
    },
    YELLOW {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new ColoredBall(this, xpos, ypos, speed, angle);
        }
    },
    BOMB {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            return new BombBall(xpos, ypos, speed, angle);
        }
    },
    MAX_COLORS {
        @Override
        public Ball newBall(float xpos, float ypos, int speed, float angle) {
            BustaMove.getGameInstance().log(MessageType.Error, "An invallid ball request in BallType");
            return null;
        }
    };
    
    public abstract Ball newBall(float xpos, float ypos, int speed, float angle);
}
