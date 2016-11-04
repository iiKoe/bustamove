package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.screens.SettingsScreen;

public class SettingsMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        SettingsScreen screenMock = mock(SettingsScreen.class);
        return new SettingsMenuController(screenMock);
    }

    @Test
    public void performUserActionTest() {
        SettingsMenuController controller = (SettingsMenuController) getMenuController();
        try {
            controller.performUserAction(new SetDifficultyButton("easy"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((SetDifficultyButton)null);
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
