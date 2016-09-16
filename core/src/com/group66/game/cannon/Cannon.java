package com.group66.game.cannon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A Cannon class to shoot Balls
 */
public class Cannon {
	
	/** The angle of the cannon. */
	private float angle;
	
	/** The x coordinate of the cannon. */
	private int x;
	
	/** The y coordinate of the cannon. */
	private int y;
	
	/** The height of the cannon. */
	private int height;
	
	/** The width of the cannon. */
	private int width;
	
	/** The cannon texture. */
	private Texture cannon_texture;
	
	/** The cannon texture region. */
	private TextureRegion cannon_texture_region;
	
	/** The minimum angle of the cannon. */
	private float min_angle;
	
	/** The maximum angle of the cannon. */
	private float max_angle;
	
	/**
	 * Instantiates a new cannon.
	 *
	 * @param texture the texture of the cannon
	 * @param x the x coordinate of the cannon
	 * @param y the y coordinate of the cannon
	 * @param height the height of the cannon
	 * @param width the width of the cannon
	 */
	public Cannon(Texture texture, int x, int y, int height, int width,
			float min_angle, float max_angle) {
		this.angle =  90;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.min_angle = min_angle;
		this.max_angle = max_angle;
		
		this.cannon_texture = texture;
		this.cannon_texture_region = new TextureRegion(cannon_texture);
	}
	
	/**
	 * Adjust the angle of the Cannon.
	 *
	 * @param angle_adj the angle which we add to the current Cannon angle
	 */
	public void cannonAimAdjust(float angle_adj) {
		this.angle = checkAngle(this.angle + angle_adj);
	}
	
	/**
	 * Sets the angle of the cannon.
	 *
	 * @param angle the new angle
	 */
	public void setAngle(float angle) {
		this.angle = checkAngle(this.angle + angle);
	}
	
	/**
	 * Gets the angle of the cannon.
	 *
	 * @return the angle
	 */
	public float getAngle() {
		return angle;
	}
	
	/**
	 * Gets the height of the cannon.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the width of the cannon.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the x coordinate of the cannon.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate of the cannon.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Update.
	 *
	 * @param delta the delta
	 */
	public void update(float delta) {
		
	}
	
	/**
	 * Draw the Cannon.
	 *
	 * @param batch the batch used to draw with
	 */
	public void draw(SpriteBatch batch) {
		//batch.draw(cannon_texture, hitbox.x, hitbox.y, BALL_RAD, BALL_RAD);
<<<<<<< HEAD
		batch.draw(cannon_texture_region, x - width/2f, y - height/2f +6, width/2f, height/2f, width, height, 1, 1, angle, true);
=======
		batch.draw(cannon_texture_region, x - width / 2f, y - height / 2f, 
				width / 2f, height / 2f, width, height, 1, 1, angle, true);
>>>>>>> refs/remotes/origin/develop
	}
	
	/**
	 * Check if the cannon angle is within boundaries.
	 *
	 * @param a the angle
	 * @return the angle within boundaries
	 */
	private float checkAngle(float a) {
		if (a > this.max_angle) {
			a = this.max_angle;
		} else if (a < this.min_angle) {
			a = this.min_angle;
		}
		return a;
	}
}
