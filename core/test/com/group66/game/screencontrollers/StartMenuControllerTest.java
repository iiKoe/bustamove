package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.StartGameButton;
import com.group66.game.screens.StartScreen;

public class StartMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        StartScreen screenMock = mock(StartScreen.class);
        return new StartMenuController(screenMock);
    }

    @Test
    public void performUserActionTest() {
        StartMenuController controller = (StartMenuController) getMenuController();
        try {
            controller.performUserAction(new StartGameButton("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new StartGameButton(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new StartGameButton(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((StartGameButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
