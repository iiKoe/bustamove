package com.group66.game.logging;

public abstract class Logger {
    protected Logger nextLogger;
    protected MessageType type;
    
    /**
     * Set the next logger to call
     * @param nextLogger the next logger
     */
    public void setNextLogger(Logger nextLogger){
        this.nextLogger = nextLogger;
    }
    
    /**
     * Log a message
     * @param mt the message type
     * @param message the message to log
     */
    public void logMessage(MessageType mt, String message){
        if(this.type.level <= mt.level){
            write(message);
        }
        if(nextLogger != null){
            nextLogger.logMessage(mt, message);
        }
    }
    
    /**
     * Write a message to a logger
     * @param message the message
     */
    abstract protected void write(String message);
}
