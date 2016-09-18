package com.group66.game.helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.group66.game.helpers.TextDrawer;
import com.group66.game.screens.GameScreen;

public class TimeKeeper {
	
	/* Time since the start of this round */
	public double universalTime;
	
	/* Time since the last ball shot */
	private double deltaTime;
	
	/* TextDrawer object to draw stuff on the screen */
	//private TextDrawer textDrawer = new TextDrawer();
		
	public TimeKeeper() {
		this.universalTime = 0;
		this.deltaTime = 0;
	}
	
	// universalTime counter
	public void universalTimeCounter(float delta) {
		this.universalTime = this.universalTime + delta;
		//Draw text on screen -- testing purposes
		//textDrawer.draw(GameScreen.game.batch, Double.toString(this.universalTime), 250, 250);
		//System.out.println(this.universalTime);
	}
	
	
	// reset the delta time.
	public void deltaTimeReset() {
		this.deltaTime = this.universalTime;
		System.out.println("Delta Time Reset!");
	}
	
	// Calculate the time difference
	public void didHeShoot() {
		if ((this.universalTime - this.deltaTime) > 10) {
			System.out.println("10 secs passed!");	        
	        try
	        {
	        Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_SPACE);
	        robot.keyRelease(KeyEvent.VK_SPACE); 
	        }
	        catch (AWTException e)
	        {
	        e.printStackTrace();
	        }
		}
			
	}
	
	

}
