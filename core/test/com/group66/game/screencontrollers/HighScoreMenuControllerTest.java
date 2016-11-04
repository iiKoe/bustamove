package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.HighScoreScreen;

public class HighScoreMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        HighScoreScreen screenMock = mock(HighScoreScreen.class);
        return new HighScoreMenuController(screenMock);
    }

    @Test
    public void performUserActionTest() {
        HighScoreMenuController controller = (HighScoreMenuController) getMenuController();
        
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
