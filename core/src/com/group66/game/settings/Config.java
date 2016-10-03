package com.group66.game.settings;

/**
 * A Class used for static configurations/setting to have it all localized at one place.
 */
public class Config {
    // TODO make a bit nicer
    /* General game settings */
    public static final int NBALLS_ROW_DOWN = 8;
    
    /* Resolution settings */
    /** The Constant for the border size BORDER_SIZE_X (the sides). */
    public static final int BORDER_SIZE_SIDES = 10;
    
    /** The Constant for the border size BORDER_SIZE_TOP (the top). */
    public static final int BORDER_SIZE_TOP = 10;
    
    /** The Constant for the border size BORDER_SIZE_BOT (the bottom). */
    public static final int BORDER_SIZE_BOT = 10;
            
    public static final int LEVEL_WIDTH = 600;
    public static final int LEVEL_HEIGHT = 880;
    public static final int SEGMENT_OFFSET = LEVEL_WIDTH + BORDER_SIZE_SIDES;
    
    /** The Constant for the game WIDTH. */
    public static final int WIDTH = LEVEL_WIDTH * 3 + BORDER_SIZE_SIDES * 4;
    
    /** The Constant for the game HEIGHT. */
    public static final int HEIGHT = LEVEL_HEIGHT + BORDER_SIZE_TOP + BORDER_SIZE_BOT;
    
    public static final int SINGLE_PLAYER_OFFSET = (WIDTH - LEVEL_WIDTH) / 2;
    
    /* Ball settings */
    /** The Constant for the BALL_SPEED. */
    public static final int BALL_SPEED = 400;
    
    /** The Constant for the NUM_BALLS_ROW (number of balls per row). */
    public static final int NUM_BALLS_ROW = 8;
    
    /** The Constant for the BALL_DIAM (diameter). */
    public static final int BALL_DIAM = LEVEL_WIDTH / NUM_BALLS_ROW;
    
    /** The Constant for the BALL_RAD (radius). */
    public static final int BALL_RAD = BALL_DIAM / 2;
    
    /* Cannon settings */
    /** The Constant CANNON_HEIGHT. */
    public static final int CANNON_HEIGHT = 184;
    
    /** The Constant CANNON_WIDTH. */
    public static final int CANNON_WIDTH = 76;
    
    /** The Constant CANNON_Y_OFFSET. */
    public static final int CANNON_Y_OFFSET = 50;
    
    /** The Constant for the CANNON_AIM_DELTA (the step size of a cannon aim adjust). */
    public static final float CANNON_AIM_DELTA = 1;
    
    /** The Constant CANNON_MIN_ANGLE. */
    public static final float CANNON_MIN_ANGLE = 1;
    
    /** The Constant CANNON_MAX_ANGLE. */
    public static final float CANNON_MAX_ANGLE = 179;
    
    /** The font size used in drawing text */
    public static final int FONT_SIZE = 24;

    /** The x and y offset of the displayed player score */
    public static final int SCORE_OFFSET = 20;
}
