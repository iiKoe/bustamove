package com.group66.game.input;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Input.Keys;
import com.group66.game.input.InputHandler.KeyCommand;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class InputHandlerTest {
    @Test
    public void creationTest() {
        new InputHandler();
    }
    
    @Test
    public void registerTest() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.registerKeyMap("Jump", Keys.SPACE);
        inputHandler.registerKeyMap("abcd", 0);
        
        KeyCommand comm = new KeyCommand() {
            public void runCommand() { }
        };
        KeyCommand comm2 = new KeyCommand() {
            public void runCommand() { }
        };
        
        inputHandler.registerKeyPressedFunc("Jump", comm);
        inputHandler.registerKeyPressedFunc("Jump", comm2);
        inputHandler.registerKeyPressedFunc("Jump", null);
        inputHandler.registerKeyPressedFunc(null, null);
        inputHandler.registerKeyJustPressedFunc("Jump", comm);
        inputHandler.registerKeyJustPressedFunc("Jump", comm2);
        inputHandler.registerKeyJustPressedFunc("Jump", null);
        inputHandler.registerKeyJustPressedFunc(null, null);
    }
    
    @Test
    public void runTest() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.registerKeyMap("Jump", Keys.SPACE);
        inputHandler.registerKeyMap("abcd", 0);
        
        KeyCommand comm = new KeyCommand() {
            public void runCommand() { }
        };
        
        inputHandler.registerKeyPressedFunc("Jump", comm);
        inputHandler.registerKeyJustPressedFunc("Jump", comm);
        
        inputHandler.run();
    }
}
