package com.group66.game.screencontrollers;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.group66.game.BustaMove;
import com.group66.game.cannon.GameManager;

public abstract class AbstractGameControllerTest {
    
    @Before
    public void setupTests() {
        HeadlessNativesLoader.load();
        MockGraphics mockGraphics = new MockGraphics();
        Gdx.graphics = mockGraphics;
        HeadlessNet headlessNet = new HeadlessNet();
        Gdx.net = headlessNet;
        HeadlessFiles headlessFiles = new HeadlessFiles();
        Gdx.files = headlessFiles;
        Gdx.gl = mock(GL20.class);
        try {
            BustaMove.getGameInstance().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected abstract AbstractGameController getGameController();
    
    @Test
    public void pauseTest() {
        
        AbstractGameController controller = getGameController();
        controller.togglePause();
        controller.update(0);
    }
    
    @Test
    public void updateTest() {
        
        AbstractGameController controller = getGameController();
        controller.update(0);
    }
    
    @Test
    public void winTest() {
        
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
