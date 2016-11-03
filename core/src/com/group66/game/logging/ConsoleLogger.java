package com.group66.game.logging;

public class ConsoleLogger extends Logger {

    /**
     * Constructor for a console logger
     * @param mt the message type to write
     */
    public ConsoleLogger(MessageType mt) {
        this.verbosity = mt;
    }

    @Override
    protected void write(String message) {
        try {
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}