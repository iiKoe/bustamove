package com.group66.game.screens;

import org.junit.Test;
import com.group66.game.settings.DynamicSettings;

public class OnePlayerGameScreenTest extends AbstractGameScreenTest {
    /*
    @Test
    public void creationTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        new OnePlayerGameScreen(dynamicSettings);
    }
    */
    protected AbstractGameScreen getGameScreen() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        return new OnePlayerGameScreen(dynamicSettings); 
    }
    
    /*
    @Test
    public void badlogicLogoFileExists() {
        assertTrue("This test will only pass when the badlogic.jpg file coming with a new project 
            setup has not been deleted.",
            Gdx.files.internal("../android/assets/badlogic.jpg").exists());
    }
    */
}
