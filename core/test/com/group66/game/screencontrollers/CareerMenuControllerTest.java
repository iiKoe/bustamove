package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.ResetButton;
import com.group66.game.screencontrollers.actions.ShopButton;
import com.group66.game.screens.CareerScreen;

public class CareerMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        CareerScreen screenMock = mock(CareerScreen.class);
        return new CareerMenuController(screenMock);
    }
    
    @Test
    public void performUserActionTest() {
        CareerMenuController controller = (CareerMenuController) getMenuController();
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
            controller.performUserAction(new ResetButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((ResetButton)null);
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
            controller.performUserAction(new PlayLevelButton(1));
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
