package com.group66.game.screencontrollers;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TwoPlayerGameControllerTest extends AbstractGameControllerTest {

    @Override
    protected AbstractGameController getGameController() {
        return new TwoPlayerGameController(false, 3);
    }
    
    @Test
    public void randomTest() {
        new TwoPlayerGameController(true, 1);
    }
}
