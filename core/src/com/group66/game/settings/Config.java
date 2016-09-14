package com.group66.game.settings;

/**
 * A Class used for static configurations/setting to have it all localized at one place.
 */
public class Config {
	/* Resolution settings */
	// TODO make a bit nicer
	/** The Constant for the border size BORDER_SIZE_X (the sides). */
	public static final int BORDER_SIZE_SIDES = 10;
	
	/** The Constant for the border size BORDER_SIZE_TOP (the top). */
	public static final int BORDER_SIZE_TOP = 10;
	
	/** The Constant for the border size BORDER_SIZE_BOT (the bottom). */
	public static final int BORDER_SIZE_BOT = 10;
			
	/** The Constant for the game WIDTH. */
	public static final int WIDTH = 600 + BORDER_SIZE_SIDES*2;
	
	/** The Constant for the game HEIGHT. */
	public static final int HEIGHT = 880 + BORDER_SIZE_TOP + BORDER_SIZE_BOT;
	
	/** The Constant for the ball bounce border BOUNCE_X_MIN. */
	public static final int BOUNCE_X_MIN = BORDER_SIZE_SIDES;
	
	/** The Constant for the ball bounce border BOUNCE_X_MAX. */
	public static final int BOUNCE_X_MAX = WIDTH - BORDER_SIZE_SIDES;

	/** The Constant for the ball bounce border BOUNCE_Y_MIN. */
	public static final int BOUNCE_Y_MIN = BORDER_SIZE_BOT;
	
	/** The Constant for the ball bounce border BOUNCE_Y_MAX. */
	public static final int BOUNCE_Y_MAX = HEIGHT - BORDER_SIZE_TOP;
	
	/* Ball settings */
	/** The Constant for the BALL_SPEED. */
	public static final int BALL_SPEED = 400;
	
	/** The Constant for the BALL_RAD (radius). */
	public static final int BALL_RAD = 8;
	
	/** The Constant for the BALL_DIAM (diameter). */
	public static final int BALL_DIAM = BALL_RAD*2;
	
	/* Cannon settings */
	/** The Constant for theCANNON_AIM_DELTA (the step size of a cannon aim adjust). */
	public static final float CANNON_AIM_DELTA = 1;
}
