package com.group66.game.helpers;

import com.group66.game.cannon.GameManager;

/**
 * The Class TimeKeeper.
 */
public class TimeKeeper {
    /**The ball manager of this timekeeper*/
    private GameManager ballManager;
    
    /**Time since the start of this round. */
    private double universalTime;
    
    /** Time since the last ball shot. */
    private double lastShotTime;
    
    /* TextDrawer object to draw stuff on the screen */
    //private TextDrawer textDrawer = new TextDrawer();
        
    /**
     * Instantiates a new time keeper.
     */
    public TimeKeeper(GameManager ballManager) {
        this.ballManager = ballManager;
        this.universalTime = 0;
        this.lastShotTime = 0;
    }
    
    /**
     * Universal time counter.
     *
     * @param delta the delta
     */
    public void universalTimeCounter(float delta) {
        this.universalTime += delta;
        //Draw text on screen -- testing purposes
        //textDrawer.draw(GameScreen.game.batch, Double.toString(this.universalTime), 250, 250);
        //System.out.println(this.universalTime);
    }
    
    /**
     * Delta time reset.
     */
    public void shotTimeReset() {
        this.lastShotTime = this.universalTime;
    }
    
    /**
     * Did he shoot.
     */
    public void didHeShoot() {
        if ((this.universalTime - this.lastShotTime) > 10) {
            System.out.println("10 secs passed!");          
            ballManager.shootBall();
        }   
    }
}
