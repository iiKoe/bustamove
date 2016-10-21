package com.group66.game.shop;

import org.junit.Test;

public class BuySpecialBombChanceTest extends BuyStateInstanceTest {
    @Test
    public void creationTest() {
        new BuySpecialBombChance();
    }

    @Override
    public BuyStateInstance getInstance() {
        return new BuySpecialBombChance();
    }

}
