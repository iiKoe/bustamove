package com.group66.game.cannon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.settings.Config;

public abstract class Ball {
    /**
     * The Enum PopStatus.
     */
    private enum PopStatus {
        NONE, 
        POPPING, 
        DONE 
    }

    public enum BallType {
        BLUE,
        GREEN,
        RED,
        YELLOW,
        BOMB
    }

    /** The Constant that represents a BLUE ball. */
    public static final int BLUE = BallType.BLUE.ordinal();

    /** The Constant that represents a GREEN ball. */
    public static final int GREEN = BallType.GREEN.ordinal();

    /** The Constant that represents a RED ball. */
    public static final int RED = BallType.RED.ordinal();

    /** The Constant that represents a YELLOW ball. */
    public static final int YELLOW = BallType.YELLOW.ordinal();

    /** The Constant that represents the maximum number of available colors. */
    public static final int MAX_COLORS = 4;

    /** The Ball hitbox. */
    private Circle hitbox;

    /**  Hitbox for detecting neighbor balls. */
    private Circle neighborBox;

    /** The top hitbox. */
    private Rectangle topHitbox;

    /** The angle in which the Ball moves. */
    private float angle;

    /** The speed at which the ball moves. */
    private int speed;

    /** Stores the time passed since the start for use with the animation. */
    private float time;

    /** The color of the Ball. */
    private BallType type;

    /** The radius of the Ball. */
    private float radius;

    /**  The runtime used for animations. */
    private float runtime;

    /**  The pop animation status. */
    private PopStatus popStatus;

    /**  The pop animation instance. */
    private Animation popAnimation;

    /**
     * Instantiates a new ball.
     * 
     * @param type the color can not be or exceed MAX_COLORS
     * @param xpos the x coordinate of the Ball
     * @param ypos the y coordinate of the Ball
     * @param rad the radius of the Ball
     * @param speed the speed of the Ball
     * @param angle the angle of the Ball
     */
    public Ball(BallType type, float xpos, float ypos, float rad, int speed, float angle) {
        this.time = 10;
        this.speed = speed;
        this.angle =  angle;
        this.radius = rad;
        this.type = type;
        this.popStatus = PopStatus.NONE;
        this.runtime = 0f;

        hitbox = new Circle(xpos, ypos, this.radius);
        neighborBox = new Circle(xpos, ypos, this.radius * 1.2f);
        topHitbox = new Rectangle(xpos - this.radius, ypos - this.radius,
                this.radius * 2.0f, this.radius * 2.0f);
    }


    /**
     * Gets the x coordinate.
     * 
     * @return the x coordinate
     */
    public float getX() {
        return hitbox.x;
    }

    /**
     * Gets the y coordinate.
     *
     * @return the y coordinate
     */
    public float getY() {
        return hitbox.y;
    }

    /**
     * Gets the hitbox.
     * 
     * @return the hitbox
     */
    public Circle getHitbox() {
        return hitbox;
    }

    /**
     * Gets the neighbor box.
     *
     * @return the neighborbox
     */
    public Circle getNeighborBox() {
        return neighborBox;
    }

    /**
     * get top hitbox.
     *
     * @return the tophitbox
     */
    public Rectangle getTopHitbox() {
        return topHitbox;
    }

    /**
     * Sets the angle.
     * 
     * @param angle the new angle
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * Sets the speed.
     * 
     * @param speed the new speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the angle.
     * 
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Gets the radius.
     * 
     * @return the radius
     */
    public float getRadius() {
        return radius;
    }
    /**
     * Gets the color.
     * 
     * @return the color
     */
    public int getColor() {
        return type.ordinal();
    }

    /**
     * Gets the type
     * 
     * @return the type
     */
    public BallType getType() {
        return type;
    }

    /**
     * Move down.
     *
     * @param dy the delta y
     */
    public void moveDown(float dy) {
        this.hitbox.y -= dy;
        this.neighborBox.y -= dy;
        this.topHitbox.y -= dy;
    }

    /**
     * Sets the x coordinate.
     *
     * @param xpos the new x coordinate
     */
    public void setX(float xpos) {
        this.hitbox.x = xpos;
        this.neighborBox.x = xpos;
        this.topHitbox.x = xpos;
    }

    /**
     * Sets the y coordinate.
     *
     * @param ypos the new y coordinate
     */
    public void setY(float ypos) {
        this.hitbox.y = ypos;
        this.neighborBox.y = ypos;
        this.topHitbox.y = ypos;
    }

    /**
     * Update.
     * 
     * @param delta the change in time since the last update
     */
    public void update(float delta) {
        hitbox.x += speed * (float) Math.cos(this.angle) * delta;
        hitbox.y += speed * (float) Math.sin(this.angle) * delta;
        neighborBox.x += speed * (float) Math.cos(this.angle) * delta;
        neighborBox.y += speed * (float) Math.sin(this.angle) * delta;
        topHitbox.x += speed * (float) Math.cos(this.angle) * delta;
        topHitbox.y += speed * (float) Math.sin(this.angle) * delta;
        time -= delta;
    }

    /**
     * Checks if a ball should be dead.
     * 
     * @return true, if should be dead
     */
    public boolean isDead() {
        if (time < 0) {
            return true;
        }
        return false;
    }

    /**
     * Does hit.
     * 
     * @param circle
     *            the Circle object
     * @return true, if the hitboxes overlap
     */
    public boolean doesHit(Circle circle) {
        return circle.overlaps(hitbox);
    }

    /**
     * Is next to.
     * @param circle the circle object
     * @return true, if the neighborBoxes overlap.
     */
    public boolean isNextTo(Circle circle) {
        return circle.overlaps(neighborBox);
    }

    /**
     * Pop done.
     *
     * @return true, if pop animation is done.
     */
    public boolean popDone() {
        if (popStatus == PopStatus.DONE) {
            popStatus = PopStatus.NONE;
            return true;
        }
        return false;
    }

    /**
     * Start the Pop animation.
     */
    public void popStart() {
        if (popStatus == PopStatus.POPPING) {
            return;
        }

        switch (type) {
            case BLUE:
                popAnimation = AssetLoader.getBluePopAnimation();
                break;
            case GREEN:
                popAnimation = AssetLoader.getGreenPopAnimation();
                break;
            case RED:
                popAnimation = AssetLoader.getRedPopAnimation();
                break;
            case YELLOW:
                popAnimation = AssetLoader.getYellowPopAnimation();
                break;
            default:
                popAnimation = AssetLoader.getBluePopAnimation(); // Error
                return;
        }

        this.runtime = 0;
        popStatus = PopStatus.POPPING;
        AudioManager.ballpop();
        //System.out.println("Popping Started!");
    }

    /**
     * Insert the into a graph.
     *
     * @param graph the graph
     */
    public void addToGraph(BallGraph graph) {
        try {
            graph.insertBall(this);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    /**
     * Delete the ball from a graph.
     *
     * @param graph the graph
     */
    public void deleteBallFromGraph(BallGraph graph) {
        try {
            graph.removeBall(this);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }

    /**
     * Draw the Ball.
     * 
     * @param batch the batch used to draw with
     * @param delta the delta since the last draw
     */
    public void draw(SpriteBatch batch, float delta) {
        this.runtime += delta;
        // batch.draw(ball_texture, ); // TODO calc actual x and y

        TextureRegion tr;
        // TODO What to draw when popping is done? Nothing?
        if (popStatus == PopStatus.POPPING) {
            tr = popAnimation.getKeyFrame(this.runtime);
            if (popAnimation.isAnimationFinished(this.runtime)) {
                popStatus = PopStatus.DONE;
                this.runtime = 0;
                //System.out.println("Popping Done!");
            }
        } else {
            switch (type) {
                case BLUE:
                    tr = AssetLoader.blueAnimation.getKeyFrame(this.runtime);
                    break;
                case GREEN:
                    tr = AssetLoader.greenAnimation.getKeyFrame(this.runtime);
                    break;
                case RED:
                    tr = AssetLoader.redAnimation.getKeyFrame(this.runtime);
                    break;
                case YELLOW:
                    tr = AssetLoader.yellowAnimation.getKeyFrame(this.runtime);
                    break;
                case BOMB:
                    tr = new TextureRegion(AssetLoader.bomb);
                    break;
                default:
                    return;
            }
        }

        batch.draw(tr, hitbox.x - this.radius, hitbox.y - this.radius,
                this.radius * 2, this.radius * 2);
    }
    
    /**
     * Draw the Ball.
     * 
     * @param batch the batch used to draw with
     * @param delta the delta since the last draw
     * @param segmentOffset determines the offset
     */
    public void drawSplit(SpriteBatch batch, float delta, int segmentOffset) {
        this.runtime += delta;
    
        TextureRegion tr;
        // TODO What to draw when popping is done? Nothing?
        if (popStatus == PopStatus.POPPING) {
            tr = popAnimation.getKeyFrame(this.runtime);
            if (popAnimation.isAnimationFinished(this.runtime)) {
                popStatus = PopStatus.DONE;
                this.runtime = 0;
                //System.out.println("Popping Done!");
            }
        } else {
            switch (type) {
                case BLUE:
                    tr = AssetLoader.blueAnimation.getKeyFrame(this.runtime);
                    break;
                case GREEN:
                    tr = AssetLoader.greenAnimation.getKeyFrame(this.runtime);
                    break;
                case RED:
                    tr = AssetLoader.redAnimation.getKeyFrame(this.runtime);
                    break;
                case YELLOW:
                    tr = AssetLoader.yellowAnimation.getKeyFrame(this.runtime);
                    break;
                case BOMB:
                    tr = new TextureRegion(AssetLoader.bomb);
                    break;
                default:
                    return;
            }
        }
        
        batch.draw(tr, segmentOffset * Config.SEGMENT_WIDTH + hitbox.x - this.radius, hitbox.y - this.radius,
                this.radius * 2, this.radius * 2);
    }


    /**
     * Checks if two balls of of the same type
     * 
     * @param ball to compare
     * @return Boolean whether balls are of the same type
     */
    public Boolean isEqual(Ball ball) {
        return false;
    }


}
