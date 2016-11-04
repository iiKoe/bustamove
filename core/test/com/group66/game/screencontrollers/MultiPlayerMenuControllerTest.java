package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.RandomButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.screens.MultiplayerMenuScreen;

public class MultiPlayerMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        MultiplayerMenuScreen screenMock = mock(MultiplayerMenuScreen.class);
        return new MultiplayerMenuController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        MultiplayerMenuController controller = (MultiplayerMenuController) getMenuController();
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
            controller.performUserAction(new RandomButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new RandomButton(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((RandomButton)null);
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
        try {
            controller.performUserAction(new PlayLevelButton(1,2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new PlayLevelButton(1,3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((PlayLevelButton)null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
