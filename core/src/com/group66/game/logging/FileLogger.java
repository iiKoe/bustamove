package com.group66.game.logging;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileLogger extends Logger {
    private FileHandle handle;

    /**
     * Constructor for a file logger
     * @param mt the message type to write
     */
    public FileLogger(MessageType mt) {
        String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        try {
            handle = Gdx.files.external("bustamove/log_" + date + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.verbosity = (mt == null ? MessageType.Default : mt);
    }

    @Override
    protected void write(String message) {
        try {
            handle.writeString(message + System.getProperty("line.separator"), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
