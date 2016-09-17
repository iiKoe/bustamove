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
	
	/** The ball animation texture. */
	public static Texture ballAnimTexture;

	/** The ball texture regions. */
	public static TextureRegion blue1, blue2, blue3, green1, green2, green3,
			red1, red2, red3, yellow1, yellow2, yellow3;

	/** The ball animations. */
	public static Animation blueAnimation, greenAnimation, redAnimation,
			yellowAnimation;
	
	/** The ball animation texture regions. */
	public static TextureRegion blue1_a, blue2_a, blue3_a, 
			green1_a, green2_a, green3_a,
			red1_a, red2_a, red3_a, 
			yellow1_a, yellow2_a, yellow3_a;
	
	/** The pop animations. */
	public static Animation bluePopAnimation, greenPopAnimation, 
	redPopAnimation, yellowPopAnimation;

	/**
	 * Load the sprites.
	 */
	public static void load() {

		// creating the background
		bgTexture = new Texture(Gdx.files.internal("data/purplebg.png"));
		bgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		bg = new TextureRegion(bgTexture, 0, 0, 128, 220);

		/*
		 * Ball animations
		 */
		// loading the textures for the balls
		ballTexture = new Texture(Gdx.files.internal("data/ballTextures.png"));
		ballTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		// creating the blue ball animation
		blue1 = new TextureRegion(ballTexture, 0, 0, 16, 16);
		blue2 = new TextureRegion(ballTexture, 20, 0, 16, 16);
		blue3 = new TextureRegion(ballTexture, 40, 0, 16, 16);
		TextureRegion[] blueTR = { blue1, blue2, blue3 };
		blueAnimation = new Animation(0.1f, blueTR);
		blueAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the green ball animation
		green1 = new TextureRegion(ballTexture, 0, 17, 16, 16);
		green2 = new TextureRegion(ballTexture, 20, 17, 16, 16);
		green3 = new TextureRegion(ballTexture, 40, 17, 16, 16);
		TextureRegion[] greenTR = { green1, green2, green3 };
		greenAnimation = new Animation(0.1f, greenTR);
		greenAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the red ball animation
		red1 = new TextureRegion(ballTexture, 0, 34, 16, 16);
		red2 = new TextureRegion(ballTexture, 20, 34, 16, 16);
		red3 = new TextureRegion(ballTexture, 40, 34, 16, 16);
		TextureRegion[] redTR = { red1, red2, red3 };
		redAnimation = new Animation(0.1f, redTR);
		redAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the yellow ball animation
		yellow1 = new TextureRegion(ballTexture, 0, 51, 16, 16);
		yellow2 = new TextureRegion(ballTexture, 20, 51, 16, 16);
		yellow3 = new TextureRegion(ballTexture, 40, 51, 16, 16);
		TextureRegion[] yellowTR = { yellow1, yellow2, yellow3 };
		yellowAnimation = new Animation(0.1f, yellowTR);
		yellowAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		/* 
		 * Ball pop animations 
		 */
		// loading the textures for the balls
		ballAnimTexture = new Texture(Gdx.files.internal("data/ballAnimationTextures.png"));
		ballAnimTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		// creating the blue ball animation
		blue1_a = new TextureRegion(ballAnimTexture, 0, 0, 16, 16);
		blue2_a = new TextureRegion(ballAnimTexture, 20, 0, 16, 16);
		blue3_a = new TextureRegion(ballAnimTexture, 40, 0, 16, 16);
		TextureRegion[] blueTR_a = { blue1_a, blue2_a, blue3_a };
		blueAnimation = new Animation(0.1f, blueTR_a);
		blueAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the green ball animation
		green1_a = new TextureRegion(ballAnimTexture, 0, 17, 16, 16);
		green2_a = new TextureRegion(ballAnimTexture, 20, 17, 16, 16);
		green3_a = new TextureRegion(ballAnimTexture, 40, 17, 16, 16);
		TextureRegion[] greenTR_a = { green1_a, green2_a, green3_a };
		greenAnimation = new Animation(0.1f, greenTR_a);
		greenAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the red ball animation
		red1_a = new TextureRegion(ballAnimTexture, 0, 34, 16, 16);
		red2_a = new TextureRegion(ballAnimTexture, 20, 34, 16, 16);
		red3_a = new TextureRegion(ballAnimTexture, 40, 34, 16, 16);
		TextureRegion[] redTR_a = { red1_a, red2_a, red3_a };
		redAnimation = new Animation(0.1f, redTR_a);
		redAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// creating the yellow ball animation
		yellow1 = new TextureRegion(ballAnimTexture, 0, 51, 16, 16);
		yellow2 = new TextureRegion(ballAnimTexture, 20, 51, 16, 16);
		yellow3 = new TextureRegion(ballAnimTexture, 40, 51, 16, 16);
		TextureRegion[] yellowTR_a = { yellow1_a, yellow2_a, yellow3_a };
		yellowAnimation = new Animation(0.1f, yellowTR_a);
		yellowAnimation.setPlayMode(Animation.PlayMode.NORMAL); //TODO check playmodes

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
