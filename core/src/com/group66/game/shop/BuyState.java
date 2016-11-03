package com.group66.game.shop;
import com.group66.game.settings.DynamicSettings;

/**
 * The Interface BuyState for use in the shop State Machines.
 */
public interface BuyState {
    
    /**
     * Buy.
     *
     * @param instance the state machine instance
     * @param dynamicSettings the dynamic settings
     */
    public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings);
    
    /**
     * Gets the next state info.
     *
     * @return the next state info (what does it do)
     */
    public String getNextStateInfo();
    
    /**
     * Gets the next state cost.
     *
     * @return the next state cost (coins/currency)
     */
    public int getNextStateCost();
}
