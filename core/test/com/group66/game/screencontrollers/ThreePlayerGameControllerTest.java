package com.group66.game.screencontrollers;

public class ThreePlayerGameControllerTest extends AbstractGameControllerTest {
    @Override
    protected AbstractGameController getGameController() {
        return new ThreePlayerGameController(false, 3);
    }
}
