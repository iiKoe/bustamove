package com.group66.game.shop;

import org.junit.Test;

import com.group66.game.settings.DynamicSettings;

public abstract class BuyStateInstanceTest {
    
    public abstract BuyStateInstance getInstance();
    
    @SuppressWarnings("unused")
    @Test
    public void currentTest() {
        BuyStateInstance instance = getInstance();
        BuyState state = instance.getCurrent();
    }
    
    @SuppressWarnings("unused")
    @Test
    public void getterTest() {
        BuyStateInstance instance = getInstance();
        BuyState state = instance.getCurrent();
        String info = instance.getNextStateInfo();
        int cost = instance.getNextStateCost();
        boolean fin = instance.isFinalState();
    }
    
    @Test
    public void buyTest() {
        BuyStateInstance instance = getInstance();
        DynamicSettings dynamicSettings = new DynamicSettings();
        instance.buy(dynamicSettings);
    }
    
    @Test
    public void buyAllTest() {
        BuyStateInstance instance = getInstance();
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.addCurrency(1000, true);
        instance.buy(dynamicSettings);
        instance.buy(dynamicSettings);
        instance.buy(dynamicSettings);
        instance.buy(dynamicSettings);
    }    
}
