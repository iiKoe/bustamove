package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A AssetLoader for sprite textures.
 */
public class AssetLoader {


    /** The background texture. */
    public static Texture bgTexture;
    
    /** The background texture region. */
    public static TextureRegion bg;

	/** The ball texture. */
	public static Texture ballTexture;
	
	/** The ball pop texture. */
	public static Texture ballPopTexture;
	
	/** The ball texture regions. */
	public static TextureRegion blue1, blue2, blue3, green1, green2, green3,
			red1, red2, red3, yellow1, yellow2, yellow3;

	/** The ball animations. */
	public static Animation blueAnimation, greenAnimation, redAnimation,
			yellowAnimation;
	
	/** The ball animation texture regions. */
	public static TextureRegion[] BluePopTextureRegions = new TextureRegion[7];
	
	/** The Green pop texture regions. */
	public static TextureRegion[] GreenPopTextureRegions = new TextureRegion[7];
	
	/** The Red pop texture regions. */
	public static TextureRegion[] RedPopTextureRegions = new TextureRegion[7];
	
	/** The Yellow pop texture regions. */
	public static TextureRegion[] YellowPopTextureRegions = new TextureRegion[7];

    /**
     * Load the sprites.
     */
    public static void load() {

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
		
		/* 
		 * Ball pop animations 
		 */
		// loading the textures for the balls
        ballPopTexture = new Texture(Gdx.files.internal("ballPopTextures.png"));
        ballPopTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		// creating the blue ball animation
        BluePopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 0, 20, 20);
        BluePopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 0, 23, 23);
        BluePopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 0, 24, 24);
        BluePopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 0, 33, 30);
        BluePopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 0, 32, 32);
        BluePopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 0, 34, 31);
        BluePopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 0, 32, 32);

		// creating the green ball animation
        GreenPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 33, 20, 20);
        GreenPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 33, 23, 23);
        GreenPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 33, 24, 24);
        GreenPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 33, 33, 30);
        GreenPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 33, 32, 32);
        GreenPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 33, 34, 31);
        GreenPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 33, 32, 32);

		// creating the red ball animation
        RedPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 66, 20, 20);
        RedPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 66, 23, 23);
        RedPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 66, 24, 24);
        RedPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 66, 33, 30);
        RedPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 66, 32, 32);
        RedPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 66, 34, 31);
        RedPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 66, 32, 32);

		// creating the yellow ball animation
        YellowPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 99, 20, 20);
        YellowPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 99, 23, 23);
        YellowPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 99, 24, 24);
        YellowPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 99, 33, 30);
        YellowPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 99, 32, 32);
        YellowPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 99, 34, 34);
        YellowPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 99, 32, 32);
    }
    
    /**
     * Gets the blue pop animation.
     *
     * @return a blue pop animation instance
     */
    public static Animation getBluePopAnimation() {
    	Animation bluePopAnimation;
    	
		TextureRegion[] bluePopTR = { BluePopTextureRegions[0], 
				BluePopTextureRegions[1], BluePopTextureRegions[2],
				BluePopTextureRegions[3], BluePopTextureRegions[4], 
				BluePopTextureRegions[5], BluePopTextureRegions[6]};
		bluePopAnimation = new Animation(0.1f, bluePopTR);
		bluePopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		return bluePopAnimation;
    }
    
    /**
     * Gets a green pop animation instance.
     *
     * @return the green pop animation
     */
    public static Animation getGreenPopAnimation() {
    	Animation greenPopAnimation;
    	
		TextureRegion[] greenPopTR = { GreenPopTextureRegions[0], 
				GreenPopTextureRegions[1], GreenPopTextureRegions[2],
				GreenPopTextureRegions[3], GreenPopTextureRegions[4], 
				GreenPopTextureRegions[5], GreenPopTextureRegions[6]};
		greenPopAnimation = new Animation(0.1f, greenPopTR);
		greenPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		return greenPopAnimation;
    }
    
    /**
     * Gets a red pop animation instance.
     *
     * @return the red pop animation
     */
    public static Animation getRedPopAnimation() {
    	Animation redPopAnimation;
    	
		TextureRegion[] redPopTR = { RedPopTextureRegions[0], 
				RedPopTextureRegions[1], RedPopTextureRegions[2],
				RedPopTextureRegions[3], RedPopTextureRegions[4], 
				RedPopTextureRegions[5], RedPopTextureRegions[6]};
		redPopAnimation = new Animation(0.1f, redPopTR);
		redPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		return redPopAnimation;
    }
    
    /**
     * Gets a yellow pop animation instance.
     *
     * @return the yellow pop animation
     */
    public static Animation getYellowPopAnimation() {
    	Animation yellowPopAnimation;
    	
		TextureRegion[] yellowPopTR = { YellowPopTextureRegions[0], 
				YellowPopTextureRegions[1], YellowPopTextureRegions[2],
				YellowPopTextureRegions[3], YellowPopTextureRegions[4], 
				YellowPopTextureRegions[5], YellowPopTextureRegions[6]};
		yellowPopAnimation = new Animation(0.1f, yellowPopTR);
		yellowPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		return yellowPopAnimation;
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
