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
            //load the file
            FileHandle handle = Gdx.files.internal(levelFilePath);
            Scanner scanner = new Scanner(handle.read(), "UTF-8");
            int linenr = 0;
            //read every line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int ypos = Config.BOUNCE_Y_MAX - (2 * linenr + 1) * Config.BALL_RAD;
                for (int i = 0; i < line.length(); i++) {
                    float xpos = Config.BOUNCE_X_MIN + (2 * i + 1) * Config.BALL_RAD;
                    //System.out.println("X pos: " + xpos);

                    // shift odd rows
                    if (linenr % 2 != 0) {
                        xpos += Config.BALL_RAD;
                    }
                    //spaces or dashes are used for empty spaces
                    if (line.charAt(i) != ' ' && line.charAt(i) != '-') {
                        int ballIndex = Integer.parseInt("" + line.charAt(i));
                        ballManager.addStaticBall(ballIndex, xpos, ypos);
                    }
                }
                linenr++;
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a test level.
     */
    public static void loadLevel(BallManager ballManager1, BallManager ballManager2) {
        String levelFilePath = "testlevel.txt";

        try {
            //load the file
            FileHandle handle = Gdx.files.internal(levelFilePath);
            Scanner scanner = new Scanner(handle.read(), "UTF-8");
            int linenr = 0;
            //read every line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int ypos = Config.BOUNCE_Y_MAX - (2 * linenr + 1) * Config.BALL_RAD;
                for (int i = 0; i < line.length(); i++) {
                    float xpos1 = Config.BORDER_SIZE_SIDES + (2 * i + 1) * Config.BALL_RAD;
                    //float xpos2 = Config.SEGMENT_WIDTH + (2 * i + 1) * Config.BALL_RAD;
                    //System.out.println("X pos: " + xpos);

                    // shift odd rows
                    if (linenr % 2 != 0) {
                        xpos1 += Config.BALL_RAD;
                        //xpos2 += Config.BALL_RAD;
                    }
                    //spaces or dashes are used for empty spaces
                    if (line.charAt(i) != ' ' && line.charAt(i) != '-') {
                        int ballIndex = Integer.parseInt("" + line.charAt(i));
                        ballManager1.addStaticBall(ballIndex, xpos1, ypos);
                        ballManager2.addStaticBall(ballIndex, xpos1, ypos);
                    }
                }
                linenr++;
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Generate a random level for one player.
     */
    public static void generateLevel(BallManager ballManager) {
        Random rand = new Random();
        DifficultyManager difficultyManager = new DifficultyManager();
        int numRows = difficultyManager.numRows();
        System.out.println("Number of rows is " + difficultyManager.numRows());
        //go over each row
        for (int i = 0; i < numRows; i++) {
            int ypos = Config.BOUNCE_Y_MAX - (2 * i + 1) * Config.BALL_RAD;

            int numBalls = Config.NUM_BALLS_ROW;
            int xoffset = 0;
            if (i % 2 != 0) {
                numBalls--; // one less on the odd rows
                xoffset = Config.BALL_RAD;
            }
            //fill the row with balls
            for (int j = 0; j < numBalls; j++) {
                int xpos = Config.BOUNCE_X_MIN + (2 * j + 1) * Config.BALL_RAD + xoffset;
                int ballIndex = rand.nextInt(Ball.MAX_COLORS + 1);
                if (ballIndex != Ball.MAX_COLORS) { // max_colors is no ball
                    ballManager.addStaticBall(ballIndex, xpos, ypos);
                }
            }
        }
    }
    
    /**
     * Generate a random level for two players.
     */
    public static void generateLevel(BallManager ballManager1, BallManager ballManager2) {
        Random rand = new Random();
        DifficultyManager difficultyManager = new DifficultyManager();
        int numRows = difficultyManager.numRows();
        System.out.println("Number of rows is " + difficultyManager.numRows());
        //go over each row
        for (int i = 0; i < numRows; i++) {
            int ypos = Config.BOUNCE_Y_MAX - (2 * i + 1) * Config.BALL_RAD;

            int numBalls = Config.NUM_BALLS_ROW;
            int xoffset = 0;
            if (i % 2 != 0) {
                numBalls--; // one less on the odd rows
                xoffset = Config.BALL_RAD;
            }
            //fill the row with balls
            for (int j = 0; j < numBalls; j++) {
                int xpos1 = Config.BORDER_SIZE_SIDES + (2 * j + 1) * Config.BALL_RAD + xoffset;
                //int xpos2 = Config.WIDTH / 2 + (2 * j + 1) * Config.BALL_RAD + xoffset;
                
                int ballIndex = rand.nextInt(Ball.MAX_COLORS + 1);
                if (ballIndex != Ball.MAX_COLORS) { // max_colors is no ball
                    ballManager1.addStaticBall(ballIndex, xpos1, ypos);
                    ballManager2.addStaticBall(ballIndex, xpos1, ypos);
                }
            }
        }
    }
}
