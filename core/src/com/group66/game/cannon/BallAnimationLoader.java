package com.group66.game.cannon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A AssetLoader for sprite textures.
 */
public class BallAnimationLoader {

    /** The ball texture. */
    private static Texture ballTexture;

    /** The ball texture regions. */
    private static TextureRegion blue1, blue2, blue3, green1, green2, green3,
        red1, red2, red3, yellow1, yellow2, yellow3;
    
    /** The ball pop texture. */
    private static Texture ballPopTexture;

    /** The ball animations. */
    private static Animation blueAnimation, greenAnimation, redAnimation,
        yellowAnimation, bombAnimation;
    
    /** The ball animation texture regions. */
    private static TextureRegion[] bluePopTextureRegions = new TextureRegion[7];

    /** The Green pop texture regions. */
    private static TextureRegion[] greenPopTextureRegions = new TextureRegion[7];

    /** The Red pop texture regions. */
    private static TextureRegion[] redPopTextureRegions = new TextureRegion[7];

    /** The Yellow pop texture regions. */
    private static TextureRegion[] yellowPopTextureRegions = new TextureRegion[7];

    /** The bomb texture. */
    private static Texture bomb;

    /**
     * Load the sprites.
     */
    public static void load() {

        /**
         * MainMenu, YouWin, and YouLose screens Sprites
         */
        
        /** 
         * GameScreen Sprites 
         */

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

        //creating texture bomb
        bomb = new Texture(Gdx.files.internal("bomb.png"));
        bomb.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        TextureRegion bombTextureRegion = new TextureRegion(bomb);
        TextureRegion[] bombTR = { bombTextureRegion };
        bombAnimation = new Animation(0.1f, bombTR);
        bombAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);


        /* 
         * Ball pop animations 
         */
        // loading the textures for the balls
        ballPopTexture = new Texture(Gdx.files.internal("ballPopTextures.png"));
        ballPopTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // creating the blue ball animation
        bluePopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 0, 20, 20);
        bluePopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 0, 23, 23);
        bluePopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 0, 24, 24);
        bluePopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 0, 33, 30);
        bluePopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 0, 32, 32);
        bluePopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 0, 34, 31);
        bluePopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 0, 32, 32);

        // creating the green ball animation
        greenPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 33, 20, 20);
        greenPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 33, 23, 23);
        greenPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 33, 24, 24);
        greenPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 33, 33, 30);
        greenPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 33, 32, 32);
        greenPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 33, 34, 31);
        greenPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 33, 32, 32);

        // creating the red ball animation
        redPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 66, 20, 20);
        redPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 66, 23, 23);
        redPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 66, 24, 24);
        redPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 66, 33, 30);
        redPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 66, 32, 32);
        redPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 66, 34, 31);
        redPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 66, 32, 32);

        // creating the yellow ball animation
        yellowPopTextureRegions[0] = new TextureRegion(ballPopTexture, 0, 99, 20, 20);
        yellowPopTextureRegions[1] = new TextureRegion(ballPopTexture, 28, 99, 23, 23);
        yellowPopTextureRegions[2] = new TextureRegion(ballPopTexture, 61, 99, 24, 24);
        yellowPopTextureRegions[3] = new TextureRegion(ballPopTexture, 95, 99, 33, 30);
        yellowPopTextureRegions[4] = new TextureRegion(ballPopTexture, 134, 99, 32, 32);
        yellowPopTextureRegions[5] = new TextureRegion(ballPopTexture, 171, 99, 34, 34);
        yellowPopTextureRegions[6] = new TextureRegion(ballPopTexture, 212, 99, 32, 32);
    }

    /**
     * Gets the blue animation.
     *
     * @return the blue animation
     */
    public static Animation getBlueAnimation() {
        return blueAnimation;
    }
    
    /**
     * Gets the green animation.
     *
     * @return the green animation
     */
    public static Animation getGreenAnimation() {
        return greenAnimation;
    }
    
    /**
     * Gets the red animation.
     *
     * @return the red animation
     */
    public static Animation getRedAnimation() {
        return redAnimation;
    }
    
    /**
     * Gets the yellow animation.
     *
     * @return the yellow animation
     */
    public static Animation getYellowAnimation() {
        return yellowAnimation;
    }

    /**
     * Gets the bomb  animation.
     *
     * @return the yellow animation
     */
    public static Animation getBombAnimation() {
        return bombAnimation;
    }
    

    
    /**
     * Gets the blue pop animation.
     *
     * @return a blue pop animation instance
     */
    public static Animation getBluePopAnimation() {
        Animation bluePopAnimation;

        TextureRegion[] bluePopTR = { bluePopTextureRegions[0], 
                bluePopTextureRegions[1], bluePopTextureRegions[2],
                bluePopTextureRegions[3], bluePopTextureRegions[4], 
                bluePopTextureRegions[5], bluePopTextureRegions[6]};
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

        TextureRegion[] greenPopTR = { greenPopTextureRegions[0], 
                greenPopTextureRegions[1], greenPopTextureRegions[2],
                greenPopTextureRegions[3], greenPopTextureRegions[4], 
                greenPopTextureRegions[5], greenPopTextureRegions[6]};
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

        TextureRegion[] redPopTR = { redPopTextureRegions[0], 
                redPopTextureRegions[1], redPopTextureRegions[2],
                redPopTextureRegions[3], redPopTextureRegions[4], 
                redPopTextureRegions[5], redPopTextureRegions[6]};
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

        TextureRegion[] yellowPopTR = { yellowPopTextureRegions[0], 
                yellowPopTextureRegions[1], yellowPopTextureRegions[2],
                yellowPopTextureRegions[3], yellowPopTextureRegions[4], 
                yellowPopTextureRegions[5], yellowPopTextureRegions[6]};
        yellowPopAnimation = new Animation(0.1f, yellowPopTR);
        yellowPopAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        return yellowPopAnimation;
    }

    /**
     * Dispose of the textures.
     */
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        ballTexture.dispose();
        ballPopTexture.dispose();
        blue1.getTexture().dispose();
        blue2.getTexture().dispose();
        blue3.getTexture().dispose();
        green1.getTexture().dispose();
        green2.getTexture().dispose();
        green3.getTexture().dispose();
        red1.getTexture().dispose();
        red2.getTexture().dispose();
        red3.getTexture().dispose();
        yellow1.getTexture().dispose();
        yellow2.getTexture().dispose();
        yellow3.getTexture().dispose();
        //blueAnimation.dispose();
        //greenAnimation.dispose();
        //redAnimation.dispose();
        //yellowAnimation.dispose();
        //bluePopTextureRegions.dispose();
        //greenPopTextureRegions.dispose();
        //redPopTextureRegions.dispose();
        //yellowPopTextureRegions.dispose();
        bomb.dispose();
    }
}
