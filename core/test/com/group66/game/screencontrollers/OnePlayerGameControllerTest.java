package com.group66.game.screencontrollers;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class OnePlayerGameControllerTest extends AbstractGameControllerTest {
    
    @Override
    protected AbstractGameController getGameController() {
        
        return new OnePlayerGameController(false, 3);
    }
    
    @Test
    public void randomTest() {
        new OnePlayerGameController(true, 1);
    }
    
}
