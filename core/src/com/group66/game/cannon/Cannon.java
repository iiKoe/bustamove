package com.group66.game.cannon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cannon {
	private float angle;
	private int x, y;
	private int height, width;
	private Texture cannon_texture;
	private TextureRegion cannon_texture_region;
	
	public Cannon(Texture texture, int x, int y, int height, int width) {
		this.angle =  90;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.cannon_texture = texture;
		this.cannon_texture_region = new TextureRegion(cannon_texture);
	}
	
	public void cannonAimAdjust(float angle_adj) {
		angle += angle_adj;
		if (angle >= 180)
			angle = 179;
		else if (angle <= 0)
			angle = 1;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void update(float delta) {
		// Change the cannon angle
	}
	
	public void draw(SpriteBatch batch) {
		//batch.draw(cannon_texture, hitbox.x, hitbox.y, BALL_RAD, BALL_RAD);
		batch.draw(cannon_texture_region, x - width/2f, y - height/2f, width/2f, height/2f, width, height, 1, 1, angle, true);
	}
}
