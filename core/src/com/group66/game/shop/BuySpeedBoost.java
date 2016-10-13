package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * The Class BuySpeedBoost State Machine.
 */
public class BuySpeedBoost extends BuyStateInstance {
    
    /**
     * Instantiates a new buy speed boost State Machine.
     */
    public BuySpeedBoost() {
        this.setCurrent(new None());
    }
    
    /**
     * The State None.
     */
    static class None implements BuyState {
        
        /* (non-Javadoc)
         */
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SPEED_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SPEED_INCR_COST);
                dynamicSettings.setBallSpeedMultiplier(1.25);
                instance.setCurrent(new Speedp25());
                BustaMove.logger.log(MessageType.Info, "Set new state to Speedp25");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        
        /* (non-Javadoc)
         */
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+25%";
        }
        
        /* (non-Javadoc)
         */
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SPEED_INCR_COST;
        }
    }
    
    /**
     * The State Speedp25.
     */
    static class Speedp25 implements BuyState {
        
        /* (non-Javadoc)
         */
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SPEED_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SPEED_INCR_COST);
                dynamicSettings.setBallSpeedMultiplier(1.5);
                instance.setCurrent(new Speedp50());
                BustaMove.logger.log(MessageType.Info, "Set new state to Speedp50");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        
        /* (non-Javadoc)
         */
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+50%";
        }
        
        /* (non-Javadoc)
         */
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SPEED_INCR_COST;
        }
    }
    
    /**
     * The State Speedp50.
     */
    static class Speedp50 implements BuyState {
        
        /* (non-Javadoc)
         */
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            if (dynamicSettings.getCurrency() >= Config.SPEED_INCR_COST) {
                dynamicSettings.addCurrency(-1 * Config.SPEED_INCR_COST);
                dynamicSettings.setBallSpeedMultiplier(1.75);
                instance.setCurrent(new Speedp75());
                instance.setIsFinalState(true);
                BustaMove.logger.log(MessageType.Info, "Set new state to Speedp75");
                BustaMove.logger.log(MessageType.Info, "Money: " + dynamicSettings.getCurrency());
            } else {
                BustaMove.logger.log(MessageType.Info, "Not enough money");
            }
        }
        
        /* (non-Javadoc)
         */
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+75%";
        }
        
        /* (non-Javadoc)
         */
        public int getNextStateCost(BuyStateInstance instance) {
            return Config.SPEED_INCR_COST;
        }
    }
    
    /**
     * The State Speedp75.
     */
    static class Speedp75 implements BuyState {
        
        /* (non-Javadoc)
         */
        public void buy(BuyStateInstance instance, DynamicSettings dynamicSettings) {
            instance.setCurrent(this);
            BustaMove.logger.log(MessageType.Info, "Stay in the top state (Speedp20)");
        }
        
        /* (non-Javadoc)
         */
        public String getNextStateInfo(BuyStateInstance instance) {
            return "+75%";
        }
        
        /* (non-Javadoc)
         */
        public int getNextStateCost(BuyStateInstance instance) {
            return 0;
        }
    }
}
