package com.group66.game.screencontrollers;

import org.junit.Test;
import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.LevelButton;
import com.group66.game.screencontrollers.actions.RandomButton;
import com.group66.game.screencontrollers.actions.ScoresButton;
import com.group66.game.screencontrollers.actions.SettingsButton;
import com.group66.game.screencontrollers.actions.SplitButton;
import com.group66.game.screens.MainMenuScreen;
import static org.mockito.Mockito.mock;

public class MainMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        MainMenuScreen screenMock = mock(MainMenuScreen.class);
        return new MainMenuController(screenMock);
    }
    
    
    @Test
    public void performUserActionTest() {
        MainMenuController controller = (MainMenuController) getMenuController();
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
            controller.performUserAction(new LevelButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((LevelButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new ScoresButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((ScoresButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new RandomButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((RandomButton)null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new SettingsButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((SettingsButton)null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new SplitButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((SplitButton)null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
