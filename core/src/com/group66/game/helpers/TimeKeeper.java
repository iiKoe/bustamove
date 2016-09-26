package com.group66.game.helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeKeeper.
 */
public class TimeKeeper {
    
    /**Time since the start of this round. */
    public double universalTime;
    
    /** Time since the last ball shot. */
    private double lastShotTime;
    
    /* TextDrawer object to draw stuff on the screen */
    //private TextDrawer textDrawer = new TextDrawer();
        
    /**
     * Instantiates a new time keeper.
     */
    public TimeKeeper() {
        this.universalTime = 0;
        this.lastShotTime = 0;
    }
    
    /**
     * Universal time counter.
     *
     * @param delta the delta
     */
    public void universalTimeCounter(float delta) {
        this.universalTime = this.universalTime + delta;
        //Draw text on screen -- testing purposes
        //textDrawer.draw(GameScreen.game.batch, Double.toString(this.universalTime), 250, 250);
        //System.out.println(this.universalTime);
    }
    
    /**
     * Delta time reset.
     */
    public void shotTimeReset() {
        this.lastShotTime = this.universalTime;
        System.out.println("Last shot time Reset!");
    }
    
    /**
     * Did he shoot.
     */
    public void didHeShoot() {
        if ((this.universalTime - this.lastShotTime) > 10) {
            System.out.println("10 secs passed!");          
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE); 
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }   
    }
}
