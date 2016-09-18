package com.group66.game.cannon;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.AudioManager;

/**
 * A basic Ball class.
 */
public class Ball {
	
	/**
	 * The Enum PopStatus.
	 */
	private enum PopStatus {
		NONE, 
		POPPING, 
		DONE 
	}

	/** The Constant that represents a BLUE ball. */
	public static final int BLUE = 0;

	/** The Constant that represents a GREEN ball. */
	public static final int GREEN = 1;

	/** The Constant that represents a RED ball. */
	public static final int RED = 2;

	/** The Constant that represents a YELLOW ball. */
	public static final int YELLOW = 3;

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
	private int color;

	/** The radius of the Ball. */
	private float radius;
	
	/**  The runtime used for animations. */
	private float runtime;
	
	/**  The pop animation status. */
	private PopStatus pop_status;
	
	/**  The pop animation instance. */
	private Animation pop_animation;

	/**
	 * Instantiates a new ball.
	 * 
	 * @param color the color can not be or exceed MAX_COLORS
	 * @param x the x coordinate of the Ball
	 * @param y the y coordinate of the Ball
	 * @param rad the radius of the Ball
	 * @param speed the speed of the Ball
	 * @param angle the angle of the Ball
	 */
	public Ball(int color, float x, float y, float rad, int speed, float angle) {
		this.time = 10;
		this.speed = speed;
		this.angle =  angle;
		this.radius = rad;
		this.color = color % MAX_COLORS;
		this.pop_status = PopStatus.NONE;
		this.runtime = 0f;

		hitbox = new Circle(x, y, this.radius);
		neighborBox = new Circle(x, y, this.radius * 1.2f);
		topHitbox = new Rectangle(x - this.radius, y - this.radius, this.radius * 2.0f, this.radius * 2.0f);
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
	public Ball(int color, float x, float y, float rad, int speed, float angle, BallGraph graph) {
		this(color, x, y, rad, speed, angle);
		graph.insertBall(this);
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
		return color;
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
	 * @param x the new x coordinate
	 */
	public void setX(float x) {
		this.hitbox.x = x;
		this.neighborBox.x = x;
		this.topHitbox.x = x;
	}
	
	/**
	 * Sets the y coordinate.
	 *
	 * @param y the new y coordinate
	 */
	public void setY(float y) {
		this.hitbox.y = y;
		this.neighborBox.y = y;
		this.topHitbox.y = y;
	}

	/**
	 * Update.
	 * 
	 * @param delta the change in time since the last update
	 */
	public void update(float delta) {
		hitbox.x += speed * (float) Math.cos(this.angle) * delta;
		hitbox.y += speed * (float) Math.sin(this.angle) * delta;
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
	 * @param c
	 *            the Circle object
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
	 * Pop done.
	 *
	 * @return true, if pop animation is done.
	 */
	public boolean popDone() {
		if (pop_status == PopStatus.DONE) {
			pop_status = PopStatus.NONE;
			return true;
		}
		return false;
	}
	
	/**
	 * Start the Pop animation.
	 */
	public void popStart() {
		if (pop_status == PopStatus.POPPING) {
			return;
		}
		
		switch (color) {
		case BLUE:
			pop_animation = AssetLoader.getBluePopAnimation();
			break;
		case GREEN:
			pop_animation = AssetLoader.getGreenPopAnimation();
			break;
		case RED:
			pop_animation = AssetLoader.getRedPopAnimation();
			break;
		case YELLOW:
			pop_animation = AssetLoader.getYellowPopAnimation();
			break;
		default:
			pop_animation = AssetLoader.getBluePopAnimation(); // Error
			return;
		}
		
		this.runtime = 0;
		pop_status = PopStatus.POPPING;
		AudioManager.ballpop();
		//System.out.println("Popping Started!");
	}

	/**
	 * Insert the into a graph.
	 *
	 * @param graph the graph
	 */
	public void addToGraph(BallGraph graph) {
		graph.insertBall(this);
	}

	/**
	 * Delete the ball from a graph.
	 *
	 * @param graph the graph
	 */
	public void deleteBallFromGraph(BallGraph graph) {
		graph.removeBall(this);
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
		if (pop_status == PopStatus.POPPING) {
			tr = pop_animation.getKeyFrame(this.runtime);
			if (pop_animation.isAnimationFinished(this.runtime)) {
				pop_status = PopStatus.DONE;
				this.runtime = 0;
				//System.out.println("Popping Done!");
			}
		} else {
			switch (color) {
			case 0:
				tr = AssetLoader.blueAnimation.getKeyFrame(this.runtime);
				break;
			case 1:
				tr = AssetLoader.greenAnimation.getKeyFrame(this.runtime);
				break;
			case 2:
				tr = AssetLoader.redAnimation.getKeyFrame(this.runtime);
				break;
			case 3:
				tr = AssetLoader.yellowAnimation.getKeyFrame(this.runtime);
				break;
			default:
				return;
			}
		}
		
		batch.draw(tr, hitbox.x - this.radius, hitbox.y - this.radius,
				this.radius * 2, this.radius * 2);
	}
}
