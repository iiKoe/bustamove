package com.group66.game.helpers;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Logger {
    private static FileHandle handle;
    
    /**
     * Create a new instance of the logger (singleton)
     */
    private static void startLogger() {
        String date = DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmss");
        try {
            handle = Gdx.files.external("log_" + date + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Send a string to the log file
     * @param s the input string
     */
    public static void log(String s) {
        if (handle == null) {
            //create logging file if it is not opened yet
            startLogger();
        }
        try {
            handle.writeString(s + System.getProperty("line.separator"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
    
    /**
     * Send a warning message to the log file
     * @param s the message to log
     */
    public static void logWarning(String s) {
        log("[Warning] " + s);
    }
    
    /**
     * Send an error message to the log file
     * @param s the message to log
     */
    public static void logError(String s) {
        log("[Error] " + s);
    }
}
