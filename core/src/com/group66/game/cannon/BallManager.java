package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.settings.Config;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {

	/** The cannon instance to shoot out. */
	private Cannon cannon;
	
	private Rectangle roofHitbox;

	/** The graph where all the connections between balls are stored. */
	private BallGraph ballGraph;

	/** The ball speed. */
	private int ball_speed;

	/** The ball radius. */
	private float ball_rad;
	
	private int ball_count;

	/** The ball list. */
	private ArrayList<Ball> ballList = new ArrayList<Ball>();

	/** The ball dead list. */
	private ArrayList<Ball> ballDeadList = new ArrayList<Ball>();

	/** The static ball list. */
	private ArrayList<Ball> ballStaticList = new ArrayList<Ball>();
	
	/** The static ball dead list. */
	private ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();
	
	/** The ball pop animation list. */
	private ArrayList<Ball> ballPopList = new ArrayList<Ball>();

	/**  The ball to be added to static List. */
	private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();

	/**
	 * Instantiates a new ball manager.
	 * 
	 * @param cannon the cannon to shoot the Balls out
	 * @param ball_rad the Ball radius
	 * @param speed the Ball speed
	 */
	public BallManager(Cannon cannon, float ball_rad, int speed) {
		this.cannon = cannon;
		this.ball_rad = ball_rad;
		this.ball_speed = speed;
		this.ball_count = 0;
		
		this.roofHitbox  = new Rectangle(0.0f, Config.BOUNCE_Y_MAX - 10, Config.WIDTH, 10.0f);
		this.ballGraph = new BallGraph(roofHitbox);
		
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
		if (canShoot()) {
			ballList.add(new Ball(color, cannon.getX(), cannon.getY(), ball_rad,
					ball_speed, (float) Math.toRadians(cannon.getAngle())));
			this.ball_count++;
		}
	}

	/**
	 * Shoot random colored ball.
	 */
	public void shootRandomBall() {
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		shootBall(rand);
	}
	
	/**
	 * Can shoot.
	 *
	 * @return true, if successful
	 */
	public boolean canShoot() {
		if (ballList.size() > 0 || ballPopList.size() != 0) {
			return false;
		}
		return true;
	}
	
	public void moveRowDown() {
		// Move the top hitbox down
		this.roofHitbox.y -= Config.BALL_DIAM;
		// Move all the balls down
		for (Ball b : this.ballStaticList) {
			b.moveDown(Config.BALL_DIAM);
		}
	}

	/**
	 * Draw the Balls managed by BallManager.
	 *
	 * @param batch the batch used to draw with
	 * @param delta the delta
	 */
	public void draw(SpriteBatch batch, float delta) {
		
		/* Update the ball lists and graph */
		updateBalls(delta);

		/* Draw shot ball */
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
		
		/* Check if balls need to move down */
		if (this.ball_count >= Config.NBALLS_ROW_DOWN && canShoot()) {
			System.out.println("Move balls down");
			moveRowDown();
			this.ball_count = 0;
		}
	}
	
	/**
	 * Bounce the ball of the edges if needed.
	 *
	 * @param ball the ball
	 */
	private void bounceEdge(Ball ball) {
		/* Check if an edge is hit */
		if (ball.getX() - ball.getRadius() <= Config.BOUNCE_X_MIN
				&& Math.toDegrees(ball.getAngle()) > 90) {
			// LEFT EDGE
			ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
		} else if (ball.getX() + ball.getRadius() >= Config.BOUNCE_X_MAX
				&& Math.toDegrees(ball.getAngle()) < 90) {
			// RIGHT EDGE
			ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
		}
	}
	
	/**
	 * Start popping animation.
	 *
	 * @param b the ball
	 */
	private void startPop(Ball b) {
		b.popStart();
		ballPopList.add(b);
	}
	
	/**
	 * Update balls, this includes the ball lists and the graph.
	 *
	 * @param delta the delta
	 */
	private void updateBalls(float delta) {
		/* Check shooting balls */
		// NB. Currently only 1 ball can be shot at a time in the game
		// nevertheless the current BallList implementation is kept for
		// versatility and to be future proof
		for (Ball ball : ballList) {
			ball.update(delta);
			if (ball.isDead()) {
				ballDeadList.add(ball);
			}
			for (Ball t : ballStaticList) {
				/* Does the ball hit a target ball? */
				if (t.doesHit(ball.getHitbox())) {
					ball.setSpeed(0);
					ballDeadList.add(ball);
					ballToBeAdded.add(ball);
				}
			}
			bounceEdge(ball);
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
					startPop(e);
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
					startPop(e);
				}
			}
		}
		
		/* Check if the popping balls are done */
		for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
			Ball b = it.next();
			if (b.popDone() == true) {
				it.remove();
			}
		}
	}
}

