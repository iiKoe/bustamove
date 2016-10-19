package com.group66.game.shop;

import org.junit.Test;

public class BuySpeedBoostTest extends BuyStateInstanceTest {
    @Test
    public void creationTest() {
        new BuySpeedBoost();
    }

    @Override
    public BuyStateInstance getInstance() {
        return new BuySpeedBoost();
    }
}
