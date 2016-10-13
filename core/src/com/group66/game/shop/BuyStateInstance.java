package com.group66.game.shop;

public class BuyStateInstance {
    private BuyState current;
    
    public BuyStateInstance() {
        //this.current = startState;
    }
    
    public void setCurrent(BuyState state) {
        this.current = state;
    }
    
    public BuyState getCurrent() {
        return this.current;
    }
    
    public void buy() {
        this.getCurrent().buy(this);
    }
}
