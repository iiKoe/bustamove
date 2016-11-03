package com.group66.game.screencontrollers;

import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ThreePlayerGameControllerTest extends AbstractGameControllerTest {
    @Override
    protected AbstractGameController getGameController() {
        return new ThreePlayerGameController(false, 3);
    }
}
