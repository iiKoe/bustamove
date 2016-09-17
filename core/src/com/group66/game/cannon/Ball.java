package com.group66.game.cannon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.helpers.AssetLoader;

/**
 * A basic Ball class.
 */
public class Ball {

	/** The Constant that represents a BLUE ball. */
	public static final int BLUE = 0;

	/** The Constant that represents a GREEN ball. */
	public static final int GREEN = 1;

	/** The Constant that represents a RED ball. */
	public static final int RED = 2;

	/** The Constant that represents a YELLOW ball. */
	public static final int YELLOW = 3;

	/** The Constant that represents the maximum number of availible colors. */
	public static final int MAX_COLORS = 4;

	/** The Ball hitbox. */
	private Circle hitbox;

	/** Hitbox for detecting neighbor balls */
	private Circle neighborBox;

	private Rectangle topHitbox;

	/** The angle in which the Ball moves. */
	private float angle;

	/** The speed at which the ball moves. */
	private int speed;


	/** Stores the time passed since the start for use with the animation. */
	private float time;

	/** The color of the Ball. */
	private int color;


	/** The radius of the Ball. */
	private int radius;

	/**
	 * Instantiates a new ball.
	 *
	 * @param color the color (must be one of the defined colors, can not be or exceed MAX_COLORS
	 * @param x the x coordinate of the Ball
	 * @param y the y coordinate of the Ball
	 * @param rad the radius of the Ball
	 * @param speed the speed of the Ball
	 * @param angle the angle of the Ball
	 */
	public Ball(int color, int x, int y, int rad, int speed, float angle) {
		this.time = 4;
		this.speed = speed;
		this.angle =  angle;
		this.radius = rad;
		this.color = color; // TODO Add color range check for integers equal or larger then MAX_COLORS

		hitbox = new Circle(x, y, this.radius);
		neighborBox = new Circle(x, y, this.radius*1.2f);
		topHitbox = new Rectangle(x-this.radius, y-this.radius, this.radius*2.0f, this.radius*2.0f);
	}

	/**
	 * Instantiates a new ball.
	 *
	 * @param color the color (must be one of the defined colors, can not be or exceed MAX_COLORS
	 * @param x the x coordinate of the Ball
	 * @param y the y coordinate of the Ball
	 * @param rad the radius of the Ball
	 * @param speed the speed of the Ball
	 * @param angle the angle of the Ball
	 * @param graph the needs to be added to
	 */
	public Ball(int color, int x, int y, int rad, int speed, float angle, BallGraph graph) {
		this.time = 4;
		this.speed = speed;
		this.angle =  angle;
		this.radius = rad;
		this.color = color; // TODO Add color range check for integers equal or larger then MAX_COLORS
		graph.insertBall(this);

		hitbox = new Circle(x, y, this.radius);
		neighborBox = new Circle(x, y, this.radius*1.2f);
		topHitbox = new Rectangle(x-this.radius, y-this.radius, this.radius*2.0f, this.radius*2.0f);
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
	 * Gets the y coordinate
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
	 * Gets the neighbor box
	 * @return the neighborbox
	 */
	public Circle getNeighborBox() {
		return neighborBox;
	}

	/**
	 * get top hitbox
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
	public int getRadius() {
		return radius;
	}
	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Update.
	 *
	 * @param delta the change in time since the last update
	 */
	public void update(float delta) {
		hitbox.x += speed * (float)Math.cos(this.angle) * delta;
		hitbox.y += speed * (float)Math.sin(this.angle) * delta;
		time -= delta;
	}

	/**
	 * Checks if a ball should be dead.
	 *
	 * @return true, if should be dead
	 */
	public boolean isDead() {
		if (time < 0)
			return true;
		return false;
	}

	/**
	 * Does hit.
	 *
	 * @param c the Circle object
	 * @return true, if the hitboxes overlap
	 */
	public boolean doesHit(Circle c) {
		return c.overlaps(hitbox);
	}

	/**
	 * Is next to.
	 * @param c the circle object
	 * @return true, if the neighborBoxes overlap.
	 */
	public boolean isNextTo(Circle c) {
		return c.overlaps(neighborBox);
	}

	/**
	 * The effect that happens if a ball gets hit.
	 */
	public void hitEffect() {
		System.out.println("Ball hit!");
		//TODO add destroyed animation
	}

	/**
	 * Set the speed at which the ball moves
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Insert the into a graph
	 * 
	 * @param graph
	 */
	public void addToGraph(BallGraph graph) {
		graph.insertBall(this);
	}

	/**
	 * Delete the ball from a graph
	 * 
	 * @param graph
	 */
	public void deleteBallFromGraph(BallGraph graph) {
		graph.removeBall(this);
	}

	/**
	 * Draw the Ball.
	 *
	 * @param batch the batch used to draw with
	 * @param runtime the runtime since the start of the program
	 */
	public void draw(SpriteBatch batch, float runtime) {
		//batch.draw(ball_texture, ); // TODO calc actual x and y

		TextureRegion tr;
		switch (color){
		case(0):
			tr = AssetLoader.blueAnimation.getKeyFrame(runtime);
		break; 
		case(1):
			tr = AssetLoader.greenAnimation.getKeyFrame(runtime);
		break;
		case(2):
			tr = AssetLoader.redAnimation.getKeyFrame(runtime);
		break;
		case(3):
			tr = AssetLoader.yellowAnimation.getKeyFrame(runtime);
		break;
		default:
			return;
		}
		batch.draw(tr, hitbox.x - this.radius, hitbox.y - this.radius, this.radius*2, this.radius*2);
	}
}
