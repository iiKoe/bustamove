package com.group66.game.settings;

import com.group66.game.shop.BuyScoreMultiplier;
import com.group66.game.shop.BuySpecialBombChance;
import com.group66.game.shop.BuySpeedBoost;

public class DynamicSettings {
    private int currency;
    private double scoreMultiplier;
    private double specialBombChanceMultiplier;
    private double ballSpeedMultiplier;
    
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;
    private BuySpecialBombChance buySpecialBombChanceStateMachine;
    private BuySpeedBoost buySpeedBoostStateMachine;
    
    public DynamicSettings() {
        this.currency = 0;
        this.scoreMultiplier = 1;
        this.specialBombChanceMultiplier = 1;
        this.ballSpeedMultiplier = 1;
        
        this.buyScoreMultiplierStateMachine = new BuyScoreMultiplier();
        this.buySpecialBombChanceStateMachine = new BuySpecialBombChance();
        this.buySpeedBoostStateMachine = new BuySpeedBoost();
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
    
    public void addCurrency(int dcurrency) {
        this.currency += dcurrency;
    }
    
    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    public void setScoreMultiplier(double scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }
    
    public double getSpecialBombChanceMultiplier() {
        return specialBombChanceMultiplier;
    }

    public void setSpecialBombChanceMultiplier(double specialBombChance) {
        this.specialBombChanceMultiplier = specialBombChance;
    }


    public double getBallSpeedMultiplier() {
        return ballSpeedMultiplier;
    }

    public void setBallSpeedMultiplier(double ballSpeedMultiplier) {
        this.ballSpeedMultiplier = ballSpeedMultiplier;
    }

    public BuyScoreMultiplier getBuyScoreMultiplierStateMachine() {
        return buyScoreMultiplierStateMachine;
    }
    
    public BuySpecialBombChance getBuySpecialBombChanceStateMachine() {
        return buySpecialBombChanceStateMachine;
    }

    public BuySpeedBoost getBuySpeedBoostStateMachine() {
        return buySpeedBoostStateMachine;
    } 
}
