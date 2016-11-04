package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayNextLevelButton;
import com.group66.game.screencontrollers.actions.ShopButton;
import com.group66.game.screens.YouWinScreenCareer;

public class YouWinCareerControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        YouWinScreenCareer screenMock = mock(YouWinScreenCareer.class);
        return new YouWinCareerController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        YouWinCareerController controller = (YouWinCareerController) getMenuController();
        try {
            controller.performUserAction(new ShopButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((ShopButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new PlayNextLevelButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((PlayNextLevelButton)null);
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
