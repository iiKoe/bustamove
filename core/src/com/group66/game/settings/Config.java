package com.group66.game.settings;

/**
 * A Class used for static configurations/setting to have it all localized at one place.
 */
public class Config {
	/* Resolution settings */
	// TODO make a bit nicer
	/** The Constant for the game WIDTH. */
	public static final int WIDTH = 128 + 20;
	
	/** The Constant for the game HEIGHT. */
	public static final int HEIGHT = 220 + 20;
	
	/** The Constant for the ball bounce border BOUNCE_X_MIN. */
	public static final int BOUNCE_X_MIN = 10;
	
	/** The Constant for the ball bounce border BOUNCE_X_MAX. */
	public static final int BOUNCE_X_MAX = WIDTH - 10;

	/** The Constant for the ball bounce border BOUNCE_Y_MIN. */
	public static final int BOUNCE_Y_MIN = 10;
	
	/** The Constant for the ball bounce border BOUNCE_Y_MAX. */
	public static final int BOUNCE_Y_MAX = HEIGHT - 10;
	
	/* Ball settings */
	/** The Constant for the BALL_SPEED. */
	public static final int BALL_SPEED = 400;
	
	/** The Constant for the BALL_RAD (radius). */
	public static final int BALL_RAD = 8;
	
	/* Cannon settings */
	/** The Constant for theCANNON_AIM_DELTA (the step size of a cannon aim adjust). */
	public static final float CANNON_AIM_DELTA = 1;
}
