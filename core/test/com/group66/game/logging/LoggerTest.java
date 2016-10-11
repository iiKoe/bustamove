package com.group66.game.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
    public void writeLogTest(Logger l) {
        l.log(MessageType.Info, "This is general info");
        
        //l.verbosity
    }
    
    @Test
    public void nextLoggerTest() {
        ConsoleLogger cl1 = new ConsoleLogger(MessageType.Warning);
        ConsoleLogger cl2 = new ConsoleLogger(MessageType.Error);
        ConsoleLogger cl3 = new ConsoleLogger(MessageType.Info);
        
        cl1.nextLogger(cl2);
        cl2.nextLogger(cl3);
        
        cl1.log(MessageType.Warning, "This is a warning");
        
        String outString = outContent.toString();
        String[] lines = outString.split("(\r)?\n");
        assertEquals(lines.length, 3);
        String cl1out = lines[0];
        String cl2out = lines[1];
        String cl3out = lines[2];
        
        assertEquals(cl1out, "Warning: This is a warning");
        assertNotEquals(cl2out, "Warning: This is a warning");
        assertNotEquals(cl3out, "Warning: This is a warning");
    }
}
