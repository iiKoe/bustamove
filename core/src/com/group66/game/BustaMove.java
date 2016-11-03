package com.group66.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.HighScoreManager;
import com.group66.game.helpers.ProfileManager;
import com.group66.game.logging.ConsoleLogger;
import com.group66.game.logging.FileLogger;
import com.group66.game.logging.Logger;
import com.group66.game.logging.MessageType;
import com.group66.game.screens.StartScreen;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * The BustaMove main game class.
 */
public class BustaMove extends Game {   
    
    /** The batch. */
    public SpriteBatch batch;
    
    /** The logger. */
    private Logger logger;
    
    /** The high score manager. */
    private HighScoreManager highScoreManager;
    
    /** The dynamic settings object. */
    private DynamicSettings dynamicSettings;
    
    /** The profile manager. */
    private ProfileManager profileManager;
    
    /** Create the only object of this class */
    private static BustaMove game = new BustaMove();
   
    private BustaMove() {
    }
    
    /**
     * Gets the only object available.
     * 
     * @return the only available object
     */
    public static BustaMove getGameInstance() {
        return game;
    }
    
    /**
     * 
     * @return
     */
    public SpriteBatch getBatch() {
        return batch;
    }
    
    /**
     * Gets the game height.
     *
     * @return the game height
     */
    public int getGameHeight() {
        return Config.HEIGHT;
        //return Gdx.graphics.getHeight();
    }
    
    /**
     * Gets the game width.
     *
     * @return the game width
     */
    public int getGameWidth() {
        return Config.WIDTH;
        //return Gdx.graphics.getWidth();
    }
    
    /**
     * Gets the first logger
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }
    
    /**
     * Send a message to the loggers
     * @param mt the message type
     * @param message the message
     */
    public void log(MessageType mt, String message) {
        if (logger == null || mt == null || message == null || "".equals(message)) {
            return;
        }
        logger.log(mt, message);
    }
    
    /**
     * Gets the high score manager
     * @return the high score manager
     */
    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    /**
     * Gets the dynamic settings
     * @return the dynamic settings
     */
    public DynamicSettings getDynamicSettings() {
        return dynamicSettings;
    }

    /**
     * Gets the profile manager
     * @return the profileManager
     */
    public ProfileManager getProfileManager() {
        return profileManager;
    }

    /*
     * @see com.badlogic.gdx.ApplicationListener#create()
     */
    @Override
    public void create() {
        highScoreManager = new HighScoreManager();
        dynamicSettings = new DynamicSettings();
        profileManager = new ProfileManager();
        
        Logger fileLogger = new FileLogger(MessageType.Debug);
        Logger consoleLogger = new ConsoleLogger(MessageType.Info);

        fileLogger.nextLogger(consoleLogger);
        logger = fileLogger;
        
        /* Log start time */
        logger.log(MessageType.Default, "Game started");
        
        AudioManager.load();
        batch = new SpriteBatch();
        this.setScreen(new StartScreen());
    }
}
