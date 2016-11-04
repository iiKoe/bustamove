package com.group66.game.screencontrollers;

import org.junit.Test;

import com.group66.game.BustaMove;
import com.group66.game.cannon.GameManager;

public abstract class AbstractGameControllerTest {
    protected abstract AbstractGameController getGameController();
    
    @Test
    public void pauseTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AbstractGameController controller = getGameController();
        controller.togglePause();
        controller.update(0);
    }
    
    @Test
    public void updateTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AbstractGameController controller = getGameController();
        controller.update(0);
    }
    
    @Test
    public void winTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AbstractGameController controller = getGameController();
        try {
            GameManager gameManager = controller.getGameManager1();
            //gameManager.getBallManager().
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.update(0);
    }
    
    @Test
    public void loseTest() {
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AbstractGameController controller = getGameController();
        try {
            GameManager gameManager = controller.getGameManager1();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
            gameManager.getBallManager().moveRowDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.update(0);
    }
}
