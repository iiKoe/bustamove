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
        handle = Gdx.files.classpath("log_" + date + ".txt");
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
        handle.writeString(s, true);
    }
}
