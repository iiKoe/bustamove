package com.group66.game.logging;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConsoleLoggerTest extends LoggerTest {
    /**
     * Test to see if the object can be created
     */
    @Test
    public void creationTest() {
        new ConsoleLogger(MessageType.Default);
    }
    
    /**
     * Test if a sent message is logged correctly
     */
    @Test
    public void consoleLoggerSendTest() {
        ConsoleLogger cl = new ConsoleLogger(MessageType.Warning);
        cl.log(MessageType.Warning, "This is a warning");
        
        String testString = "Warning: This is a warning";
        String outString = outContent.toString();
        outString = outString.substring(outString.indexOf(']') + 2);
        outString = outString.substring(0, 26);
        
        assertEquals(testString.length(), outString.length());
        assertEquals(testString, outString);
        
        outContent.reset();
    }
}
