package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.YouWinScreenRandom;

public class YouWinRandomControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        YouWinScreenRandom screenMock = mock(YouWinScreenRandom.class);
        return new YouWinRandomController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        YouWinRandomController controller = (YouWinRandomController) getMenuController();
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
