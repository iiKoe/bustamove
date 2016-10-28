package com.group66.game.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggerTest {
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Creating the stream to read output
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Closing the stream to read output
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
    /*
    public void writeLogTest(Logger l) {
        l.log(MessageType.Info, "This is general info");
        
        //l.verbosity
    }
    */
    
    /**
     * Test for next logger
     */
    @Test
    public void nextLoggerTest() {
        ConsoleLogger cl1 = new ConsoleLogger(MessageType.Warning);
        ConsoleLogger cl2 = new ConsoleLogger(MessageType.Error);
        ConsoleLogger cl3 = new ConsoleLogger(MessageType.Info);
        
        cl1.nextLogger(cl2);
        cl2.nextLogger(cl3);
        
        cl1.log(MessageType.Warning, "This is a warning");
        
        String outString = outContent.toString();
        assertNotEquals("", outString);
        String[] lines = outString.split("(\r)?\n");
        assertEquals(2, lines.length);
        String cl1out = lines[0];
        cl1out = cl1out.substring(cl1out.indexOf(']') + 2);
        //String cl2out = lines[1];
        String cl3out = lines[1];
        cl3out = cl3out.substring(cl3out.indexOf(']') + 2);
        
        assertEquals("Warning: This is a warning", cl1out);
        //assertEquals("Warning: This is a warning", cl2out);
        assertEquals("Warning: This is a warning", cl3out);
        
        outContent.reset();
    }
}
