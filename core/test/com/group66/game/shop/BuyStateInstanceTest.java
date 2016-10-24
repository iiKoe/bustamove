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
        dynamicSettings.setCurrency(0);
        instance.buy(dynamicSettings); //not enough money
        dynamicSettings.addCurrency(instance.getNextStateCost());
        instance.buy(dynamicSettings); //buy 5
        instance.buy(dynamicSettings); //not enough for 10
        dynamicSettings.addCurrency(instance.getNextStateCost());
        instance.buy(dynamicSettings); //buy 10
        instance.buy(dynamicSettings); //not enough for 20
        dynamicSettings.addCurrency(instance.getNextStateCost());
        instance.buy(dynamicSettings); //buy 20
        dynamicSettings.addCurrency(1000);
        instance.buy(dynamicSettings); //buy 20 again
    }
    
    @Test
    public void infoTest() {
        BuyStateInstance instance = getInstance();
        DynamicSettings dynamicSettings = new DynamicSettings();
        dynamicSettings.setCurrency(1000);
        instance.getNextStateInfo();
        instance.getNextStateCost();
        instance.buy(dynamicSettings); //buy 5
        instance.getNextStateInfo();
        instance.getNextStateCost();
        instance.buy(dynamicSettings); //buy 10
        instance.getNextStateInfo();
        instance.getNextStateCost();
        instance.buy(dynamicSettings); //buy 20
        instance.getNextStateInfo();
        instance.getNextStateCost();        
    }
}
