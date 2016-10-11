package com.group66.game.logging;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConsoleLoggerTest extends LoggerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
    @Test
    public void consoleLoggerCreationTest() {
        new ConsoleLogger(MessageType.Default);
    }
    
    @Test
    public void consoleLoggerSendTest() {
        ConsoleLogger cl = new ConsoleLogger(MessageType.Warning);
        cl.log(MessageType.Warning, "This is a warning");
        
        String testString = "Warning: This is a warning";
        String outString = outContent.toString();
        outString = outString.substring(outString.indexOf(']')+2);
        outString = outString.substring(0, 26);
        
        assertEquals(testString.length(), outString.length());
        assertEquals(testString, outString);
    }
}
