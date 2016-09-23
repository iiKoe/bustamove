package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.BustaMove;
import com.group66.game.helpers.AudioManager;
import com.group66.game.logging.Logger;
import com.group66.game.logging.MessageType;
import com.group66.game.screens.GameScreen;
import com.group66.game.screens.YouWinScreen;
import com.group66.game.settings.Config;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {
	
	/** The cannon instance to shoot out. */
	private Cannon cannon;
	
	/** The roof hitbox. */
	private Rectangle roofHitbox;

	/** The graph where all the connections between balls are stored. */
	private BallGraph ballGraph;

	/** The ball speed. */
	private int ball_speed;

	/** The ball radius. */
	private float ball_rad;
	
	/** The ball count. */
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
	
	/** The balls the canon will shoot. */
	private ArrayList<Ball> cannonBallList = new ArrayList<Ball>();
	
	/** The roof hitbox offset. */
	private float ROOF_OFFSET = 10;

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
		this.roofHitbox  = new Rectangle(0.0f, Config.BOUNCE_Y_MAX - ROOF_OFFSET, Config.WIDTH, 10.0f);
		this.ballGraph = new BallGraph(roofHitbox);
		
		//addStaticBall(-1, 0, 0);
		int rand = ThreadLocalRandom.current().nextInt(Ball.MAX_COLORS);
		cannonBallList.add(new Ball(rand, cannon.getX(), cannon.getY(), ball_rad, 0, 0.0f));
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
	public void addStaticBall(int color, float x, float y) { 
		ballStaticList.add(new Ball(color, x, y, ball_rad, 0, 0.0f));
		ballStaticList.get(ballStaticList.size() - 1).addToGraph(ballGraph);
	}

	/**
	 * Adds a random static ball.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void addRandomStaticBall(float x, float y) {
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

			ballList.add(cannonBallList.get(0));
			ballList.get(ballList.size() - 1).setAngle((float) Math.toRadians(cannon.getAngle()));
			ballList.get(ballList.size() - 1).setSpeed(ball_speed);
			cannonBallList.remove(0);
			/*ballList.add(new Ball(color, cannon.getX(), cannon.getY(), ball_rad,
					ball_speed, (float) Math.toRadians(cannon.getAngle())));*/
			cannonBallList.add(new Ball(color, cannon.getX(), cannon.getY(), ball_rad,
					0, (float) Math.toRadians(cannon.getAngle())));
			AudioManager.shoot();
			GameScreen.timeKeeper.shotTimeReset();
			BustaMove.logger.log(MessageType.Info, "Shot a " + color
					+ " ball at angle " + cannon.getAngle());
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
		if (ballList.size() > 0 || ballPopList.size() > 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the ball count.
	 *
	 * @return the ball count
	 */
	public int getBallCount() {
		return this.ball_count;
	}
	
	/**
	 * Sets the ball count.
	 *
	 * @param bc the new ball count
	 */
	public void setBallCount(int bc) {
		this.ball_count = bc;
	}
	
	/**
	 * Gets the roof hitbox.
	 *
	 * @return the roof hitbox
	 */
	public Rectangle getRoofHitbox() {
		return this.roofHitbox;
	}
	
	/**
	 * Move row down.
	 */
	public void moveRowDown() {
		// Move the top hitbox down
		this.roofHitbox.y -= Config.BALL_DIAM;
		// Move all the balls down
		for (Ball b : this.ballStaticList) {
			b.moveDown(Config.BALL_DIAM);
		}
		BustaMove.logger.log(MessageType.Info, "Moved the roof a row down");
	}
	
	/**
	 * Checks if it's game over.
	 *
	 * @return true, if is game over
	 */
	public boolean isGameOver() {
		for (Ball b : ballStaticList) {
			// TODO fix the != check
			if (b.getY() - Config.BALL_DIAM <= Config.BOUNCE_Y_MIN  && b.getY() != 0) {
				return true;
			}
		}
		return false;
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
		
		/* Draw cannon balls */
		for (Ball ball: cannonBallList) {
			ball.draw(batch, delta);
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
    		AudioManager.wallhit();
    		BustaMove.logger.log(MessageType.Info, "Ball hit the wall");
		} else if (ball.getX() + ball.getRadius() >= Config.BOUNCE_X_MAX
				&& Math.toDegrees(ball.getAngle()) < 90) {
			// RIGHT EDGE
			ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
    		AudioManager.wallhit();
    		BustaMove.logger.log(MessageType.Info, "Ball hit the wall");
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
	 * Snap ball to grid. TODO Make less HACKY
	 *
	 * @param b the ball
	 * @param hitb the hitb
	 */
	private void snapBallToGrid(Ball b, Ball hitb) {
		// Check what the closest "snap" coordinate is
		
		float hit_x = hitb.getX();
		float hit_y = hitb.getY();
		
		float o1_x = hit_x - Config.BALL_DIAM;
		float o1_y = hit_y;
		
		float o2_x = hit_x - Config.BALL_RAD;
		float o2_y = hit_y - Config.BALL_DIAM;
		
		float o3_x = hit_x + Config.BALL_RAD;
		float o3_y = hit_y - Config.BALL_DIAM;
		
		float o4_x = hit_x + Config.BALL_DIAM;
		float o4_y = hit_y;
		
		float o5_x = hit_x + Config.BALL_RAD;
		float o5_y = hit_y + Config.BALL_DIAM;
		
		float o6_x = hit_x - Config.BALL_RAD;
		float o6_y = hit_y + Config.BALL_DIAM;
		
		float x = b.getX();
		float y = b.getY();
		
		float do1 = Math.abs(x - o1_x) + Math.abs(y - o1_y);
		float do2 = Math.abs(x - o2_x) + Math.abs(y - o2_y);
		float do3 = Math.abs(x - o3_x) + Math.abs(y - o3_y);
		float do4 = Math.abs(x - o4_x) + Math.abs(y - o4_y);
		float do5 = Math.abs(x - o5_x) + Math.abs(y - o5_y);
		float do6 = Math.abs(x - o6_x) + Math.abs(y - o6_y);
		
		// Check bounds
		float redge = Config.BOUNCE_X_MAX - Config.BALL_RAD;
		if (o4_x > redge) {
			do4 = Float.MAX_VALUE;
		}
		if (o3_x > redge) {
			do3 = Float.MAX_VALUE;
		}
		if (o5_x > redge) {
			do5 = Float.MAX_VALUE;
		}
		
		float ledge = Config.BOUNCE_X_MIN + Config.BALL_RAD;
		if (o1_x < ledge) {
			do1 = Float.MAX_VALUE;
		}
		if (o2_x < ledge) {
			do2 = Float.MAX_VALUE;
		}
		if (o6_x < ledge) {
			do6 = Float.MAX_VALUE;
		}
			
		// Check best option
		if (do1 < do2 && do1 < do3 && do1 < do4 && do1 < do5 && do1 < do6) {
			b.setX(o1_x);
			b.setY(o1_y);
			//System.out.println("Option 1");
		} else if (do2 < do1 && do2 < do3 && do2 < do4 && do2 < do5 && do2 < do6) {
			b.setX(o2_x);
			b.setY(o2_y);
			//System.out.println("Option 2");
		} else if (do3 < do1 && do3 < do2 && do3 < do4 && do3 < do5 && do3 < do6) {
			b.setX(o3_x);
			b.setY(o3_y);
			//System.out.println("Option 3");
		} else if (do4 < do1 && do4 < do2 && do4 < do3 && do4 < do5 && do4 < do6) {
			b.setX(o4_x);
			b.setY(o4_y);
			//System.out.println("Option 4");
		} else if (do5 < do1 && do5 < do2 && do5 < do3 && do5 < do4 && do5 < do6) {
			b.setX(o5_x);
			b.setY(o5_y);
		} else if (do6 < do1 && do6 < do2 && do6 < do3 && do6 < do4 && do6 < do5) {
			b.setX(o6_x);
			b.setY(o6_y);
		}
	}
	
	/**
	 * Snap ball to roof.
	 *
	 * @param b the ball
	 */
	private void snapBallToRoof(Ball b, float roof_y) {
		float new_x;
		
		for (double xpos = Config.BOUNCE_X_MAX - Config.BALL_RAD; 
				xpos >= Config.BOUNCE_X_MIN + Config.BALL_RAD; xpos -= Config.BALL_DIAM) {
			new_x = (float)xpos;
			if (Math.abs(new_x - b.getX()) <=  Config.BALL_RAD) {
				b.setX(new_x);
				b.setY(roof_y - Config.BALL_RAD);
				return;
			}
		}
		BustaMove.logger.log(MessageType.Info, "Ball snapped into place");
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
			if (ball.getTopHitbox().overlaps(roofHitbox)) {
				System.out.println("Attach ball to top");
				ball.setSpeed(0);
				snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET);
				ballDeadList.add(ball);
				ballToBeAdded.add(ball);
			}
			for (Ball t : ballStaticList) {
				/* Does the ball hit a target ball? */
				if (t.doesHit(ball.getHitbox())) {
					ball.setSpeed(0);
					// A hack for now
					snapBallToGrid(ball, t);
					ballDeadList.add(ball);
					ballToBeAdded.add(ball);
					BustaMove.logger.log(MessageType.Info, "Ball hit");
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
			BustaMove.logger.log(MessageType.Info, "Number of balls in grid: " + ballGraph.numberOfBalls());
			//System.out.println("number of balls left: " + ballGraph.numberOfBalls());
			GameScreen.scoreKeeper.setCurrentScore(0, ballGraph.getFreeBalls().size());
			if (ballStaticDeadList.size() == 0) {
				for (Ball e:ballGraph.getFreeBalls()) {
					ballStaticDeadList.add(e);
					if (!ballPopList.contains(e)) {
						startPop(e);
					}
					//System.out.println("ball added to deadlist(free)");
				}
			}
		}

		while (ballToBeAdded.size() != 0) {
			addStaticBall(ballToBeAdded.get(0).getColor(), 
					(int)ballToBeAdded.get(0).getX(), (int)ballToBeAdded.get(0).getY());
			ballToBeAdded.remove(0);
			if (ballGraph.numberOfAdjacentBalls(ballStaticList.get(ballStaticList.size() - 1)) >= 3) {
				//int score = 0;
				for (Ball e:ballGraph.getAdjacentBalls(ballStaticList.get(ballStaticList.size() - 1))) {
					//System.out.println("ball added to deadlist (adjacent)");
					//score++;
					ballStaticDeadList.add(e);
					if (!ballPopList.contains(e)) {
						startPop(e);
					}
				}
				BustaMove.logger.log(MessageType.Info, "Started popping "
						+ ballStaticDeadList.size() + " balls");
				//GameScreen.scoreKeeper.setCurrentScore(score, 0);
				//TODO 
			}
		}
		
		/* Check if the popping balls are done */
		for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
			Ball b = it.next();
			if (b.popDone() == true) {
				it.remove();
				//System.out.println("Pop list size: " + ballPopList.size());
			}
		}
		
		/* Check if there are no balls left i.e. player wins */
		if (ballGraph.numberOfBalls() == 0) {
		    BustaMove.logger.log(MessageType.Info, "Level completed");
			GameScreen.game.setScreen(new YouWinScreen(GameScreen.game));
		}
	}
}

