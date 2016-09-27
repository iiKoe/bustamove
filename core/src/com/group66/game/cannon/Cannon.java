package com.group66.game.cannon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A Cannon class to shoot Balls
 */
public class Cannon {
    
    /** The angle of the cannon. */
    private float angle;
    
    /** The x coordinate of the cannon. */
    private int xpos;
    
    /** The y coordinate of the cannon. */
    private int ypos;
    
    /** The height of the cannon. */
    private int height;
    
    /** The width of the cannon. */
    private int width;
    
    /** The cannon texture. */
    private Texture cannonTexture;
    
    /** The cannon texture region. */
    private TextureRegion cannonTextureRegion;
    
    /** The minimum angle of the cannon. */
    private float minAngle;
    
    /** The maximum angle of the cannon. */
    private float maxAngle;
    
    /**
     * Instantiates a new cannon.
     *
     * @param texture the texture of the cannon
     * @param xpos the x coordinate of the cannon
     * @param ypos the y coordinate of the cannon
     * @param height the height of the cannon
     * @param width the width of the cannon
     */
    public Cannon(Texture texture, int xpos, int ypos, int height, int width,
            float minAngle, float maxAngle) {
        this.angle =  90;
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        
        this.cannonTexture = texture;
        this.cannonTextureRegion = new TextureRegion(cannonTexture);
    }
    
    /**
     * Adjust the angle of the Cannon.
     *
     * @param angleAdjust the angle which we add to the current Cannon angle
     */
    public void cannonAimAdjust(float angleAdjust) {
        this.angle = checkAngle(this.angle + angleAdjust);
    }
    
    /**
     * Sets the angle of the cannon.
     *
     * @param angle the new angle
     */
    public void setAngle(float angle) {
        this.angle = checkAngle(this.angle + angle);
    }
    
    /**
     * Gets the angle of the cannon.
     *
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }
    
    /**
     * Gets the height of the cannon.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the width of the cannon.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the x coordinate of the cannon.
     *
     * @return the x
     */
    public int getX() {
        return xpos;
    }
    
    /**
     * Gets the y coordinate of the cannon.
     *
     * @return the y
     */
    public int getY() {
        return ypos;
    }
    
    /**
     * Update.
     *
     * @param delta the delta
     */
    public void update(float delta) {
        
    }
    
    /**
     * Draw the Cannon.
     *
     * @param batch the batch used to draw with
     */
    public void draw(SpriteBatch batch) {
        batch.draw(cannonTextureRegion, xpos - width / 2f + 11, ypos - height / 2f + 7, 
                width / 2f - 11, height / 2f, width, height, 1, 1, angle, true);
    }
    
    /**
     * Check if the cannon angle is within boundaries.
     *
     * @param angle the angle
     * @return the angle within boundaries
     */
    private float checkAngle(float angle) {
        if (angle > this.maxAngle) {
            angle = this.maxAngle;
        } else if (angle < this.minAngle) {
            angle = this.minAngle;
        }
        return angle;
    }
}
