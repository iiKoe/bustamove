package com.group66.game.screencontrollers;

import org.junit.Test;

import com.group66.game.BustaMove;

public class OnePlayerGameControllerTest extends AbstractGameControllerTest {
    @Override
    protected AbstractGameController getGameController() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OnePlayerGameController(false, 3);
    }
    
    @Test
    public void randomTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new OnePlayerGameController(true, 1);
    }
}
