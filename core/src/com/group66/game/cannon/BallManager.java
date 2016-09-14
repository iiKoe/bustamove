package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;

public class BallManager {
	public static final int BALL_SPEED = 400;

	Cannon cannon;
	
	/* Projectiles */
	ArrayList<Ball> ballList = new ArrayList<Ball>();
	ArrayList<Ball> ballDeadList = new ArrayList<Ball>();

	/* Some balls to hit for testing */
	ArrayList<Ball> ballStaticList = new ArrayList<Ball>();
	ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();
	
	private double deg_to_rad(double deg) {
		return deg * Math.PI / 180.0;
	}

	private double rad_to_deg(double rad) {
		return rad * 180 / Math.PI;
	}
	
	public BallManager(Cannon cannon) {
		this.cannon = cannon;
	}

	public void addStaticBall(int color) { // FIXME add color option
		ballStaticList.add(new Ball(color, Gdx.input.getX(), BustaMove.HEIGHT - Gdx.input.getY(), 0, 0.0f));
	}
	
	public void addRandomStaticBall(int x, int y) {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		ballStaticList.add(new Ball(rand, x, y, 0, 0.0f));
	}

	public void shootBall(int color) {
		// TODO add math so ball comes out the top of the cannon?
		ballList.add(new Ball(color, cannon.getX(), cannon.getY(), BALL_SPEED, (float) deg_to_rad(cannon.getAngle()))); // FIXME add color option
	}
	
	public void shootRandomBall() {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		shootBall(rand);
	}
	
	public void draw(SpriteBatch batch, float runtime) {
		/* Shoot projectile */
		for (Ball ball : ballList) {
			ball.draw(batch, runtime);
		}

		/* Draw static target balls */
		for (Ball ball : ballStaticList) {
			ball.draw(batch, runtime);
		}
		
		/* Shoot projectile */
		for (Ball ball : ballList) {
			ball.update(Gdx.graphics.getDeltaTime()); // TODO is it nicer to pass down delta?
			if (ball.isDead()) {
				ballDeadList.add(ball);
			}
			for (Ball t : ballStaticList) {
				/* Does the ball hit a target ball? */
				if (t.doesHit(ball.getHitbox())) {
					t.hitEffect();
					ballDeadList.add(ball);
					ballStaticDeadList.add(t);
				}
			}
			/* Does the ball hit the edge? */
			if (ball.getX() - ball.getRad() <= BustaMove.BOUNCE_X_MIN && rad_to_deg(ball.getAngle()) > 90) {
				// LEFT EDGE
				ball.setAngle((float) deg_to_rad(180) - ball.getAngle());
			} else if (ball.getX() + ball.getRad() >= BustaMove.BOUNCE_X_MAX && rad_to_deg(ball.getAngle()) < 90) {
				// RIGHT EDGE
				//ball.setAngle((float) deg_to_rad(90) + ball.getAngle());
				ball.setAngle((float) deg_to_rad(180) - ball.getAngle());
			}
		}
		while (ballDeadList.size() != 0) {
			ballList.remove(ballDeadList.get(0));
			ballDeadList.remove(0);
		}

		/* Static balls to shoot (testing) */
		/*
		 * for (CannonProjectile ball : ballList) {
		 * ball.update(Gdx.graphics.getDeltaTime()); if (ball.isDead()) {
		 * ballDeadList.add(ball); } }
		 */
		while (ballStaticDeadList.size() != 0) {
			ballStaticList.remove(ballStaticDeadList.get(0));
			ballStaticDeadList.remove(0);
		}
	}
}
