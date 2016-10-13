package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.DynamicSettings;

public class BuySpecialBombChance extends BuyStateInstance {
    
    public BuySpecialBombChance() {
        this.setCurrent(new None());
    }
    
    class None implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setSpecialBombChanceMultiplier(1.05);
            instance.setCurrent(new Chancep5());
            BustaMove.logger.log(MessageType.Info, "Set new state to Chancep5");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+5%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Chancep5 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setSpecialBombChanceMultiplier(1.1);
            instance.setCurrent(new Chancep10());
            BustaMove.logger.log(MessageType.Info, "Set new state to Chancep10");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+10%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Chancep10 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setSpecialBombChanceMultiplier(1.2);
            instance.setCurrent(new Chancep20());
            instance.setIsFinalState(true);
            BustaMove.logger.log(MessageType.Info, "Set new state to Chancep20");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Chancep20 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            instance.setCurrent(this);
            BustaMove.logger.log(MessageType.Info, "Stay in the top state (Chancep20)");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 0;
        }
    }
}
