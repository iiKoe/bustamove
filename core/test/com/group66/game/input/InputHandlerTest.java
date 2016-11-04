package com.group66.game.input;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Input.Keys;
import com.group66.game.input.InputHandler.KeyCommand;
import com.group66.game.settings.DynamicSettings;

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
        
        inputHandler.registerKeyPressedFunc("Jump", comm);
        inputHandler.registerKeyJustPressedFunc("Jump", comm);
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
