package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class BuyScoreMultiplier extends BuyStateInstance {
    
    public BuyScoreMultiplier() {
        this.setCurrent(new None());
    }
    
    class None implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SCORE_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SCORE_INCR_COST);
                dynamicSettings.setScoreMultiplier(1.05);
                instance.setCurrent(new Mulp5());
                BustaMove.logger.log(MessageType.Info, "Set new state to Mulp5");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
            
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+5%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SCORE_INCR_COST;
        }
    }
    
    class Mulp5 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SCORE_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SCORE_INCR_COST);
                dynamicSettings.setScoreMultiplier(1.1);
                instance.setCurrent(new Mulp10());
                BustaMove.logger.log(MessageType.Info, "Set new state to Mulp10");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+10%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SCORE_INCR_COST;
        }
    }
    
    class Mulp10 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SCORE_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SCORE_INCR_COST);
                dynamicSettings.setScoreMultiplier(1.2);
                instance.setCurrent(new Mulp20());
                instance.setIsFinalState(true);
                BustaMove.logger.log(MessageType.Info, "Set new state to Mulp20");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SCORE_INCR_COST;
        }
    }
    
    class Mulp20 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            instance.setCurrent(this);
            BustaMove.logger.log(MessageType.Info, "Stay in the top state (Mulp20)");
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return 0;
        }
    }
}
