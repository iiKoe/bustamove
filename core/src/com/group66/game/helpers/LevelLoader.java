package com.group66.game.helpers;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.group66.game.cannon.BallManager;
import com.group66.game.cannon.BallType;
import com.group66.game.settings.Config;

public class LevelLoader {
    
    /**
     * Load a test level.
     * 
     * @param ballManager which should handle the level.
     * @param level to load
     */
    public static void loadLevel(BallManager ballManager, int level, boolean isSplit) {
        String levelFilePath = "levels/level" + new DecimalFormat("00").format(level) + ".txt";

        try {
            //load the file
            FileHandle handle = Gdx.files.internal(levelFilePath);
            Scanner scanner = new Scanner(handle.read(), "UTF-8");
            int linenr = 0;
            //read every line of the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - (2 * linenr + 1) * Config.BALL_RAD;
                for (int i = 0; i < line.length(); i++) {
                    float xpos = Config.SINGLE_PLAYER_OFFSET + Config.BORDER_SIZE_SIDES + (2 * i + 1)
                            * Config.BALL_RAD;
                    if (isSplit) {
                        xpos = Config.BORDER_SIZE_SIDES + (2 * i + 1) * Config.BALL_RAD;
                    }

                    // shift odd rows
                    if (linenr % 2 != 0) {
                        xpos += Config.BALL_RAD;
                    }
                    //spaces or dashes are used for empty spaces
                    if (line.charAt(i) != ' ' && line.charAt(i) != '-') {
                        int ballIndex = Integer.parseInt("" + line.charAt(i));
                        ballManager.getBallsStaticManager().addStaticBall(ballIndex, xpos, ypos);
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
     * Generate a random level for players.
     */
    public static void generateLevel(BallManager ballManager, boolean isSplit) {
        Random rand = new Random();
        DifficultyManager difficultyManager = new DifficultyManager();
        int numRows = difficultyManager.numRows();
        System.out.println("Number of rows is " + difficultyManager.numRows());
        //go over each row
        for (int i = 0; i < numRows; i++) {
            int ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - (2 * i + 1) * Config.BALL_RAD;

            int numBalls = Config.NUM_BALLS_ROW;
            int xoffset = 0;
            if (i % 2 != 0) {
                numBalls--; // one less on the odd rows
                xoffset = Config.BALL_RAD;
            }
            //fill the row with balls
            for (int j = 0; j < numBalls; j++) {
                int xpos = Config.SINGLE_PLAYER_OFFSET + (2 * j + 1) * Config.BALL_RAD + xoffset;
                if (isSplit) {
                    xpos = Config.BORDER_SIZE_SIDES + (2 * j + 1) * Config.BALL_RAD + xoffset;
                }
                
                int ballIndex = rand.nextInt(BallType.MAX_COLORS.ordinal() + 1);
                if (ballIndex != BallType.MAX_COLORS.ordinal()) { // max_colors is no ball
                    ballManager.getBallsStaticManager().addStaticBall(ballIndex, xpos, ypos);
                }
            }
        }
    }
}
