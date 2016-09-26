package com.group66.game.logging;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public abstract class Logger {
    protected Logger nextLogger;
    protected MessageType verbosity;
    
    /**
     * Set the next logger to call
     * @param nextLogger the next logger
     */
    public void nextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
    
    /**
     * Log a message
     * @param mt the message type
     * @param message the message to log
     */
    public void log(MessageType mt, String message) {
        if (this.verbosity.level <= mt.level) {
            String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
            write("[" + date + "] " + mt.toString() + ": " + message);
        }
        if (nextLogger != null) {
            nextLogger.log(mt, message);
        }
    }
    
    /**
     * Write a message to a logger
     * @param message the message
     */
    protected abstract void write(String message);
}
