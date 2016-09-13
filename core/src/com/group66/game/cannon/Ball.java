package com.group66.game.cannon;

import com.badlogic.gdx.graphics.Texture;
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
	
	public static final int BALL_RAD = 8;
	
	Circle hitbox;
	float a;
	int speed;
	float time;
	Texture ball_texture;
	int color;
	
	public Ball(int color, int x, int y, int speed, float angle) {
		time = 4;
		this.speed = speed;
		a =  angle;
		hitbox = new Circle(x, y, BALL_RAD);
		this.color = color;
	}
	
	public float getX() {
		return hitbox.x;
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public void setAngle(float angle) {
		a = angle;
	}
	
	public float getAngle() {
		return a;
	}
	
	public int getRad() {
		return BALL_RAD;
	}
	
	public void update(float delta) {
		hitbox.x += speed * (float)Math.cos(a) * delta;
		hitbox.y += speed * (float)Math.sin(a) * delta;
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
		batch.draw(tr, hitbox.x - BALL_RAD, hitbox.y - BALL_RAD, BALL_RAD*2, BALL_RAD*2);
	}
	
}
