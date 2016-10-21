package com.group66.game.settings;

import org.junit.Test;

public class DynamicSettingsTest {
    @Test
    public void creationTest() {
        new DynamicSettings();
    }
    
    @Test
    public void resetTest() {
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.reset();
    }
}
