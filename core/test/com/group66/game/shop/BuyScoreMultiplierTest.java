package com.group66.game.shop;

import org.junit.Test;

public class BuyScoreMultiplierTest extends BuyStateInstanceTest {
    @Test
    public void creationTest() {
        new BuyScoreMultiplier();
    }

    @Override
    public BuyStateInstance getInstance() {
        return new BuyScoreMultiplier();
    }
    
}
