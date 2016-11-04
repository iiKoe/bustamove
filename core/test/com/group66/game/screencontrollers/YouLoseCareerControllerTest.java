package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.TryAgainButton;
import com.group66.game.screens.YouLoseScreenCareer;

public class YouLoseCareerControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        YouLoseScreenCareer screenMock = mock(YouLoseScreenCareer.class);
        return new YouLoseCareerController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        YouLoseCareerController controller = (YouLoseCareerController) getMenuController();
        try {
            controller.performUserAction(new TryAgainButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BustaMove.getGameInstance().getDynamicSettings().setExtraLife(true, false);
        try {
            controller.performUserAction(new TryAgainButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((TryAgainButton)null);
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
