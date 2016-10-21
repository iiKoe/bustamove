package com.group66.game.settings;

/**
 * A Class used for static configurations/setting to have it all localized at one place.
 */
public class Config {
    
    /** The Constant NBALLS_ROW_DOWN. */
    /* General game settings */
    public static final int NBALLS_ROW_DOWN = 8;
    
    /* Resolution settings */
    /** The Constant for the border size BORDER_SIZE_X (the sides). */
    public static final int BORDER_SIZE_SIDES = 10;
    
    /** The Constant for the border size BORDER_SIZE_TOP (the top). */
    public static final int BORDER_SIZE_TOP = 10;
    
    /** The Constant for the border size BORDER_SIZE_BOT (the bottom). */
    public static final int BORDER_SIZE_BOT = 10;
            
    /** The Constant LEVEL_WIDTH. */
    public static final int LEVEL_WIDTH = 600;
    
    /** The Constant LEVEL_HEIGHT. */
    public static final int LEVEL_HEIGHT = 880;
    
    /** The Constant SEGMENT_OFFSET. */
    public static final int SEGMENT_OFFSET = LEVEL_WIDTH + BORDER_SIZE_SIDES;
    
    /** The Constant for the game WIDTH. */
    public static final int WIDTH = LEVEL_WIDTH * 3 + BORDER_SIZE_SIDES * 4;
    
    /** The Constant for the game HEIGHT. */
    public static final int HEIGHT = LEVEL_HEIGHT + BORDER_SIZE_TOP + BORDER_SIZE_BOT;
    
    /** The Constant SINGLE_PLAYER_OFFSET. */
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
    
    /**  The chance a Bomb ball is the next ball (0-100%). */
    public static final int BOMB_BALL_CHANCE = 10;
    
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
    
    /**  The font size used in drawing text. */
    public static final int FONT_SIZE = 24;

    /**  The x and y offset of the displayed player score. */
    public static final int SCORE_OFFSET = 20;
    
    /** The Constant BUTTON_WIDTH. */
    public static final int BUTTON_WIDTH = 200;
    
    /** The Constant BUTTON_HEIGHT. */
    public static final int BUTTON_HEIGHT = 50;
    
    /** The Constant BUTTON_SPACING. */
    public static final int BUTTON_SPACING = 20;
    
    /* Shop settings */
    /** The Constant START_CURRENCY. */
    public static final int START_CURRENCY = 2000;
    
    /** The Constant SCORE_INCR_COST. */
    public static final int SCORE_INCR_COST = 100;
    
    /** The Constant BOMB_INCR_COST. */
    public static final int BOMB_INCR_COST = 100;
    
    /** The Constant SPEED_INCR_COST. */
    public static final int SPEED_INCR_COST = 100;
    
    /** The Constant EXTRA_LIFE_COST. */
    public static final int EXTRA_LIFE_COST = 1000;
    
    /** The Constant SCORE_CURRENCY_DIV. */
    public static final int SCORE_CURRENCY_DIV = 10;
    
    /** The Constant CURRENCY_Xfor the text drawing the the ShopScreen. */
    //TODO make dependent on size? But needs to fit in field in background image.
    public static final int CURRENCY_X = 145;
    
    /** The Constant CURRENCY_Y for the text drawing the the ShopScreen. */
    public static final int CURRENCY_Y = 600;
    
    /** the number of levels in the game (and present in the assets folder. */
    public static final int NUMBER_OF_LEVELS = 10;
}
