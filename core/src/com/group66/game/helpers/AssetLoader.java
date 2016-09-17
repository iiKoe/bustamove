package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// TODO: Auto-generated Javadoc
/**
 * A AssetLoader for sprite textures.
 */
public class AssetLoader {

    /** MainMenuScreen background texture. */
    public static Texture mmbgTexture;
    
    /** MainMenu Screen background texture region. */
    public static TextureRegion mmbg;
    
    /** YouWin Screen background texture. */
    public static Texture youwinbgTexture;
    
    /** YouWin Screen background texture region. */
    public static TextureRegion youwinbg;
    
    /** YouLose Screen background texture. */
    public static Texture youlosebgTexture;
    
    /** YouLose Screen background texture region. */
    public static TextureRegion youlosebg;
        
    /** GameScreen background texture. */
    public static Texture bgTexture;
    
    /** GameScreen background texture region. */
    public static TextureRegion bg;
    
    /** The ball texture. */
    public static Texture ballTexture;
    
    /** The ball texture regions. */
    public static TextureRegion blue1, blue2, blue3, green1, green2, green3, 
    	red1, red2, red3, yellow1, yellow2, yellow3;

    
    /** The ball animations. */
    public static Animation blueAnimation, greenAnimation, redAnimation, yellowAnimation;

    /**
     * Load the sprites.
     */
    public static void load() {
    	
    	/**
    	 * MainMenu, YouWin, and YouLose screens Sprites
    	 */
    	//Creating the MainMenu screen background
    	mmbgTexture = new Texture(Gdx.files.internal("main_menu.png"));
        mmbgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        mmbg = new TextureRegion(mmbgTexture, 0, 0, 600, 880);
        
    	//Creating the YouWin screen background
    	youwinbgTexture = new Texture(Gdx.files.internal("youwin.png"));
        youwinbgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        youwinbg = new TextureRegion(youwinbgTexture, 0, 0, 600, 880);
        
    	//Creating the YouLose screen background
    	youlosebgTexture = new Texture(Gdx.files.internal("youlose.png"));
        youlosebgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        youlosebg = new TextureRegion(youlosebgTexture, 0, 0, 600, 880);
        
        /** 
         * GameScreen Sprites 
         */
    	//creating the background
        bgTexture = new Texture(Gdx.files.internal("purplebg.png"));
        bgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(bgTexture, 0, 0, 128, 220);
        
        //loading the textures for the balls
        ballTexture = new Texture(Gdx.files.internal("ballTextures.png"));
        ballTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        //creating the blue ball animation
        blue1 = new TextureRegion(ballTexture, 0, 0, 16, 16);
        blue2 = new TextureRegion(ballTexture, 20, 0, 16, 16);
        blue3 = new TextureRegion(ballTexture, 40, 0, 16, 16);
        TextureRegion[] blueTR = { blue1, blue2, blue3};
        blueAnimation = new Animation(0.1f, blueTR);
        blueAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        //creating the green ball animation
        green1 = new TextureRegion(ballTexture, 0, 17, 16, 16);
        green2 = new TextureRegion(ballTexture, 20, 17, 16, 16);
        green3 = new TextureRegion(ballTexture, 40, 17, 16, 16);
        TextureRegion[] greenTR = { green1, green2, green3};
        greenAnimation = new Animation(0.1f, greenTR);
        greenAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        //creating the red ball animation
        red1 = new TextureRegion(ballTexture, 0, 34, 16, 16);
        red2 = new TextureRegion(ballTexture, 20, 34, 16, 16);
        red3 = new TextureRegion(ballTexture, 40, 34, 16, 16);
        TextureRegion[] redTR = { red1, red2, red3};
        redAnimation = new Animation(0.1f, redTR);
        redAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        //creating the yellow ball animation
        yellow1 = new TextureRegion(ballTexture, 0, 51, 16, 16);
        yellow2 = new TextureRegion(ballTexture, 20, 51, 16, 16);
        yellow3 = new TextureRegion(ballTexture, 40, 51, 16, 16);
        TextureRegion[] yellowTR = { yellow1, yellow2, yellow3};
        yellowAnimation = new Animation(0.1f, yellowTR);
        yellowAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    }

    /**
     * Dispose of the textures.
     */
    public static void dispose() { // TODO dispose all textures
        // We must dispose of the texture when we are finished.
        bgTexture.dispose();
        ballTexture.dispose();
    }

}
