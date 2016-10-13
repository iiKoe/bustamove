package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.DynamicSettings;

public class BuySpeedBoost extends BuyStateInstance {
    
    public BuySpeedBoost() {
        this.setCurrent(new None());
    }
    
    class None implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setBallSpeedMultiplier(1.05);
            instance.setCurrent(new Speedp5());
            BustaMove.logger.log(MessageType.Info, "Set new state to Speedp5");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+5%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Speedp5 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setBallSpeedMultiplier(1.1);
            instance.setCurrent(new Speedp10());
            BustaMove.logger.log(MessageType.Info, "Set new state to Speedp10");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+10%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Speedp10 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            dynamicSettings.setBallSpeedMultiplier(1.2);
            instance.setCurrent(new Speedp20());
            instance.setIsFinalState(true);
            BustaMove.logger.log(MessageType.Info, "Set new state to Speedp20");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 1000;
        }
    }
    
    class Speedp20 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            instance.setCurrent(this);
            BustaMove.logger.log(MessageType.Info, "Stay in the top state (Speedp20)");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 0;
        }
    }
}
