package com.group66.game.shop;

import com.group66.game.settings.DynamicSettings;


public interface BuyState {
    public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings);
    public String getNextStateInfo(BuyStateInstance instance);
    public int getNextStateCost(BuyStateInstance instance);
}
