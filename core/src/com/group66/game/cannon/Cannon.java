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
	
	/** The x and y coordinate of the cannon. */
	private int x, y;
	
	/** The height and width of the cannon. */
	private int height, width;
	
	/** The cannon texture. */
	private Texture cannon_texture;
	
	/** The cannon texture region. */
	private TextureRegion cannon_texture_region;
	
	/**
	 * Instantiates a new cannon.
	 *
	 * @param texture the texture of the cannon
	 * @param x the x coordinate of the cannon
	 * @param y the y coordinate of the cannon
	 * @param height the height of the cannon
	 * @param width the width of the cannon
	 */
	public Cannon(Texture texture, int x, int y, int height, int width) {
		this.angle =  90;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.cannon_texture = texture;
		this.cannon_texture_region = new TextureRegion(cannon_texture);
	}
	
	/**
	 * Adjust the angle of the Cannon. Minimum=1 degree Maximum=179 degrees
	 *
	 * @param angle_adj the angle which we add to the current Cannon angle
	 */
	public void cannonAimAdjust(float angle_adj) {
		angle += angle_adj;
		if (angle >= 180)
			angle = 179;
		else if (angle <= 0)
			angle = 1;
	}
	
	/**
	 * Sets the angle of the cannon.
	 *
	 * @param angle the new angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
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
		batch.draw(cannon_texture_region, x - width/2f, y - height/2f, width/2f, height/2f, width, height, 1, 1, angle, true);
	}
}
