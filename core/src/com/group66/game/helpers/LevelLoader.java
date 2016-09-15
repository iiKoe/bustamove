package com.group66.game.helpers;

import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BallManager;
import com.group66.game.settings.Config;

public class LevelLoader {

	/**
	 * Load a test level.
	 */
	public static void loadLevel(BallManager ballManager) {
		String levelFilePath = "testlevel.txt";

		try {
			FileHandle handle = Gdx.files.internal(levelFilePath);
			Scanner s = new Scanner(handle.read());
			int linenr = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				int ypos = Config.BOUNCE_Y_MAX - (2 * linenr + 1) * Config.BALL_RAD;
				for (int i = 0; i < line.length(); i++) {
					int xpos = Config.BOUNCE_X_MIN + (2 * i + 1) * Config.BALL_RAD;
					System.out.println("X pos: " + xpos);

					// shift odd rows
					if (linenr % 2 != 0)
						xpos += Config.BALL_RAD;

					if(line.charAt(i) != ' ') {
						int ballIndex = Integer.parseInt(""+line.charAt(i));
						ballManager.addStaticBall(ballIndex, xpos, ypos);
					}
				}
				linenr++;
			}

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a random level
	 */
	public static void generateLevel(BallManager ballManager) {
		Random r = new Random();
		int numRows = 2 + r.nextInt(5); //2-6 rows
		for(int i = 0; i < numRows; i++) {
			int ypos = Config.BOUNCE_Y_MAX - (2 * i + 1) * Config.BALL_RAD;
			
			int numBalls = Config.NUM_BALLS_ROW;
			int xoffset = 0;
			if(i % 2 != 0) {
				numBalls--; //one less on the odd rows
				xoffset = Config.BALL_RAD;
			}
			for(int j = 0; j < numBalls; j++) {
				int xpos = Config.BOUNCE_X_MIN + (2 * j + 1) * Config.BALL_RAD + xoffset;
				int ballIndex = r.nextInt(Ball.MAX_COLORS+1); 
				if(ballIndex != Ball.MAX_COLORS) { //max_colors is no ball
					ballManager.addStaticBall(ballIndex, xpos, ypos);
				}
			}
		}
	}
}
