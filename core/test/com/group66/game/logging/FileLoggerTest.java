package com.group66.game.logging;

import org.junit.Test;

public class FileLoggerTest extends LoggerTest {
    
    /**
     * Object creation test
     */
    @Test
    public void fileLoggerCreationTest() {
        new FileLogger(MessageType.Default);
    }
    
    @Test
    public void writeTest() {
        FileLogger fileLogger = new FileLogger(MessageType.Default);
        fileLogger.log(MessageType.Default, "String");
    }
}
