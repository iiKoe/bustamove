package com.group66.game.logging;

import org.junit.Test;

public class FileLoggerTest extends LoggerTest {
    @Test
    public void fileLoggerCreationTest() {
        new FileLogger(MessageType.Default);
    }
}
