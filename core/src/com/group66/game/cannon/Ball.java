package com.group66.game.cannon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.group66.game.helpers.AssetLoader;

public class Ball {
	public static final int BLUE = 0;
	public static final int GREEN = 1;
	public static final int RED = 2;
	public static final int YELLOW = 3;
	public static final int MAX_COLORS = 4;
	
	private Circle hitbox;
	private float angle;
	private int speed;
	private float time;
	private int color;
	private int rad;
	
	public Ball(int color, int x, int y, int rad, int speed, float angle) {
		this.time = 4;
		this.speed = speed;
		this.angle =  angle;
		this.rad = rad;
		this.color = color;
		
		hitbox = new Circle(x, y, this.rad);
	}
	
	public float getX() {
		return hitbox.x;
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public int getRad() {
		return rad;
	}
	
	public void update(float delta) {
		hitbox.x += speed * (float)Math.cos(this.angle) * delta;
		hitbox.y += speed * (float)Math.sin(this.angle) * delta;
		time -= delta;
	}
	
	public boolean isDead() {
		if (time < 0)
			return true;
		return false;
	}
	
	public boolean doesHit(Circle c) {
		return c.overlaps(hitbox);
	}
	
	public void hitEffect() {
		System.out.println("Ball hit!");
	}
	
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
		batch.draw(tr, hitbox.x - rad, hitbox.y - rad, rad*2, rad*2);
	}
}
