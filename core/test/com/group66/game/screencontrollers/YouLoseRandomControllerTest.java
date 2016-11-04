package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.YouLoseScreenRandom;

public class YouLoseRandomControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        YouLoseScreenRandom screenMock = mock(YouLoseScreenRandom.class);
        return new YouLoseRandomController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        YouLoseRandomController controller = (YouLoseRandomController) getMenuController();
        try {
            controller.performUserAction(new ExitButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((ExitButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            controller.performUserAction(new MainMenuButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((MainMenuButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }


}
