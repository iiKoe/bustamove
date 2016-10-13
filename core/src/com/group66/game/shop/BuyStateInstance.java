package com.group66.game.shop;

import com.group66.game.settings.DynamicSettings;

public abstract class BuyStateInstance {
    private BuyState current;
    private boolean finalState = false;
    
    public BuyStateInstance() {
        //this.current = startState;
    }
    
    public void setCurrent(BuyState state) {
        this.current = state;
    }
    
    public BuyState getCurrent() {
        return this.current;
    }
    
    public void buy(DynamicSettings dynamicSettings) {
        this.getCurrent().buy(this, dynamicSettings);
    }
    
    public String getNextStateInfo() {
        return this.getCurrent().getNextStateInfo(this);
    }

    public int getNextStateCost() {
        return this.getCurrent().getNextStateCost(this);
    }
    
    public boolean isFinalState() {
        return this.finalState;
    }

    public void setIsFinalState(boolean finalState) {
        this.finalState = finalState;
    }
}
