package com.group66.game.screencontrollers;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;

public abstract class AbstractMenuControllerTest {
    protected abstract AbstractMenuController getMenuController();

    @Test
    public void peformUserActionDefaultTest() {
        AbstractMenuController controller = getMenuController();
        try {
            controller.performUserAction(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new MainMenuButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test 
    public void inputHandlerTest() {
        AbstractMenuController controller = getMenuController();
        try {
            controller.runInputHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
