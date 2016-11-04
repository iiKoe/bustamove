package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.actions.BuyBombChanceButton;
import com.group66.game.screencontrollers.actions.BuyExtraLifeButton;
import com.group66.game.screencontrollers.actions.BuyScoreMultiplierButton;
import com.group66.game.screencontrollers.actions.BuySpeedMultiplierButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screens.CareerScreen;
import com.group66.game.screens.ShopScreen;

public class ShopMenuControllerTest extends AbstractMenuControllerTest {

    @Override
    protected AbstractMenuController getMenuController() {
        try {
            ShopScreen screenMock = mock(ShopScreen.class);
            return new ShopMenuController(screenMock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void performUserActionTest() {
        ShopMenuController controller = (ShopMenuController) getMenuController();
        try {
            controller.performUserAction(new BuyScoreMultiplierButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((BuyScoreMultiplierButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new BuyBombChanceButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((BuyBombChanceButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new BuySpeedMultiplierButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((BuySpeedMultiplierButton)null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new BuyExtraLifeButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction(new BuyExtraLifeButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        BustaMove.getGameInstance().getDynamicSettings().setExtraLife(false, false);
        BustaMove.getGameInstance().getDynamicSettings().setCurrency(0, false);
        try {
            controller.performUserAction(new BuyExtraLifeButton());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.performUserAction((BuyExtraLifeButton)null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CareerScreen screenMock = mock(CareerScreen.class);
            controller.performUserAction(new MainMenuButton(screenMock));
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
