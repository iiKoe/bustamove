package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.settings.Config;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {

	/** The cannon instance to shoot out. */
	private Cannon cannon;

	/** The ball speed. */
	private int ball_speed;

	/** The ball radius. */
	private int ball_rad;

	/** The ball list. */
	private ArrayList<Ball> ballList = new ArrayList<Ball>();

	/** The ball dead list. */
	private ArrayList<Ball> ballDeadList = new ArrayList<Ball>();

	/** The static ball list. */
	private ArrayList<Ball> ballStaticList = new ArrayList<Ball>();
	
	/** The ball pop animation list. */
	private ArrayList<Ball> ballPopList = new ArrayList<Ball>();

	/**
	 * Instantiates a new ball manager.
	 * 
	 * @param cannon the cannon to shoot the Balls out
	 * @param ball_rad the Ball radius
	 * @param speed the Ball speed
	 */
	public BallManager(Cannon cannon, int ball_rad, int speed) {
		this.cannon = cannon;
		this.ball_rad = ball_rad;
		this.ball_speed = speed;
	}

	/**
	 * Sets the ball speed.
	 * 
	 * @param speed the new ball speed
	 */
	public void setBallSpeed(int speed) {
		this.ball_speed = speed;
	}

	/**
	 * Adds a static ball.
	 * 
	 * @param color the color
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void addStaticBall(int color, int x, int y) { 
		ballStaticList.add(new Ball(color, x, y, ball_rad, 0, 0.0f));
	}

	/**
	 * Adds a random static ball.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void addRandomStaticBall(int x, int y) {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		ballStaticList.add(new Ball(rand, x, y, ball_rad, 0, 0.0f));
	}

	/**
	 * Shoot ball.
	 * 
	 * @param color the color of the Ball
	 */
	public void shootBall(int color) {
		// TODO add math so ball comes out the top of the cannon?
		if (canShoot()) {
			ballList.add(new Ball(color, cannon.getX(), cannon.getY(), ball_rad,
					ball_speed, (float) Math.toRadians(cannon.getAngle())));
		}
	}

	/**
	 * Shoot random colored ball.
	 */
	public void shootRandomBall() {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		shootBall(rand);
	}
	
	public boolean canShoot() {
		if (ballList.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * Draw the Balls managed by BallManager.
	 * 
	 * @param batch the batch used to draw with
	 * @param runtime the runtime since the start of the program
	 */
	public void draw(SpriteBatch batch, float delta) {
		
		// Itterate the Pop Animation list
		for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
			Ball b = it.next();
			if (b.popDone() == true) {
				it.remove();
			}
			ballStaticList.remove(b);
			ballList.remove(b);
		}
		
		while (ballDeadList.size() != 0) {
			ballList.remove(ballDeadList.get(0));
			ballStaticList.remove(ballDeadList.get(0));
			ballDeadList.remove(0);
		}
		
		/* Shoot projectile */
		for (Ball ball : ballList) {
			ball.draw(batch, delta);
		}

		/* Draw static target balls */
		for (Ball ball : ballStaticList) {
			ball.draw(batch, delta);
		}
		
		/* Draw popping balls */
		for (Ball ball : ballPopList) {
			ball.draw(batch, delta);
		}

		/* Shoot projectile */
		for (Ball ball : ballList) {			
			ball.update(delta);	
			bounceEdge(ball);
			
			if (ball.isDead()) {
				ballDeadList.add(ball);
			}
			
			for (Ball sb : ballStaticList) {
				/* Does the ball hit a target ball? */
				if (sb.doesHit(ball.getHitbox())) {
					ball.setSpeed(0);
					sb.hitEffect();
					ball.hitEffect();
					ballPopList.add(ball);
					ballPopList.add(sb);
				}
			}
		}
	}
	
	private void bounceEdge(Ball ball) {
		/* Does the ball hit the edge? */
		if (ball.getX() - ball.getRadius() <= Config.BOUNCE_X_MIN
				&& Math.toDegrees(ball.getAngle()) > 90) {
			// LEFT EDGE
			ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
		} else if (ball.getX() + ball.getRadius() >= Config.BOUNCE_X_MAX
				&& Math.toDegrees(ball.getAngle()) < 90) {
			// RIGHT EDGE
			// ball.setAngle((float) deg_to_rad(90) + ball.getAngle());
			ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
		}
	}
}
