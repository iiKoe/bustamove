package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.settings.Config;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {

	/** The cannon instance to shoot out. */
	private Cannon cannon;

	/** The graph where all the connections between balls are stored. */
	private BallGraph ballGraph = new BallGraph();

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

	/** The static ball dead list. */
	private ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();

	/** The ball to be added to static List */
	private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();

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
		addStaticBall(-1, 0, 0);
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
		ballStaticList.get(ballStaticList.size() - 1).addToGraph(ballGraph);
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
		ballList.add(new Ball(color, cannon.getX(), cannon.getY(), ball_rad,
				ball_speed, (float) Math.toRadians(cannon.getAngle())));
	}

	/**
	 * Shoot random colored ball.
	 */
	public void shootRandomBall() {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		shootBall(rand);
	}

	/**
	 * Draw the Balls managed by BallManager.
	 * 
	 * @param batch the batch used to draw with
	 * @param runtime the runtime since the start of the program
	 */
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
			// TODO is it nicer to pass down delta?
			ball.update(Gdx.graphics.getDeltaTime());
			if (ball.isDead()) {
				ballDeadList.add(ball);
			}
			for (Ball t : ballStaticList) {
				/* Does the ball hit a target ball? */
				if (t.doesHit(ball.getHitbox())) {
					t.hitEffect();
					ballDeadList.add(ball);
					ball.setSpeed(0);
					ballToBeAdded.add(ball);
				}
			}
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
		while (ballDeadList.size() != 0) {
			ballList.remove(ballDeadList.get(0));
			ballDeadList.remove(0);
		}

		while (ballStaticDeadList.size() != 0) {
			ballGraph.removeBall(ballStaticDeadList.get(0));
			ballStaticList.remove(ballStaticDeadList.get(0));
			ballStaticDeadList.remove(0);
			System.out.println("number of balls left: " + ballGraph.numberOfBalls());
			if (ballStaticDeadList.size() == 0) {
				for (Ball e:ballGraph.getFreeBalls()) {
					ballStaticDeadList.add(e);
					System.out.println("ball added to deadlist(free)");
				}
			}
		}

		while (ballToBeAdded.size() != 0) {
			addStaticBall(ballToBeAdded.get(0).getColor(), 
					(int)ballToBeAdded.get(0).getX(), (int)ballToBeAdded.get(0).getY());
			ballToBeAdded.remove(0);
			if (ballGraph.numberOfAdjacentBalls(ballStaticList.get(ballStaticList.size() - 1)) >= 3) {
				for (Ball e:ballGraph.getAdjacentBalls(ballStaticList.get(ballStaticList.size() - 1))) {
					System.out.println("ball added to deadlist (adjacent)");
					ballStaticDeadList.add(e);
				}
			}


		}
	}
}
