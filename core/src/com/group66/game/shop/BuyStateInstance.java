package com.group66.game.shop;

import com.group66.game.settings.DynamicSettings;

/**
 * The Class BuyStateInstance State Machine framework.
 */
public class BuyStateInstance {
    
    /** The current. */
    private BuyState current;
    
    /** The final state. */
    private boolean finalState = false;
    
    /**
     * Sets the current state.
     *
     * @param state the new state
     */
    public void setCurrent(BuyState state) {
        this.current = state;
    }
    
    /**
     * Gets the current state.
     *
     * @return the current state
     */
    public BuyState getCurrent() {
        return this.current;
    }
    
    /**
     * Buy.
     *
     * @param dynamicSettings the dynamic settings
     */
    public void buy(DynamicSettings dynamicSettings) {
        this.getCurrent().buy(this, dynamicSettings);
    }
    
    /**
     * Gets the next state info.
     *
     * @return the next state info
     */
    public String getNextStateInfo() {
        return this.getCurrent().getNextStateInfo();
    }

    /**
     * Gets the next state cost.
     *
     * @return the next state cost
     */
    public int getNextStateCost() {
        return this.getCurrent().getNextStateCost();
    }
    
    /**
     * Checks if is final state.
     *
     * @return true, if is final state
     */
    public boolean isFinalState() {
        return this.finalState;
    }

    /**
     * Sets if is final state.
     *
     * @param finalState is it the final state
     */
    public void setIsFinalState(boolean finalState) {
        this.finalState = finalState;
    }
}
