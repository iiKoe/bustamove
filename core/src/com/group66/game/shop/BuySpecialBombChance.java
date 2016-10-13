package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class BuySpecialBombChance extends BuyStateInstance {
    
    public BuySpecialBombChance() {
        this.setCurrent(new None());
    }
    
    class None implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.BOMB_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.BOMB_INCR_COST);
                dynamicSettings.setSpecialBombChanceMultiplier(1.05);
                instance.setCurrent(new Chancep5());
                BustaMove.logger.log(MessageType.Info, "Set new state to Chancep5");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+5%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.BOMB_INCR_COST;
        }
    }
    
    class Chancep5 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.BOMB_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.BOMB_INCR_COST);
                dynamicSettings.setSpecialBombChanceMultiplier(1.1);
                instance.setCurrent(new Chancep10());
                BustaMove.logger.log(MessageType.Info, "Set new state to Chancep10");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+10%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.BOMB_INCR_COST;
        }
    }
    
    class Chancep10 implements BuyState {
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.BOMB_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.BOMB_INCR_COST);
                dynamicSettings.setSpecialBombChanceMultiplier(1.2);
                instance.setCurrent(new Chancep20());
                instance.setIsFinalState(true);
                BustaMove.logger.log(MessageType.Info, "Set new state to Chancep20");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+20%";
        }
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.BOMB_INCR_COST;
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
