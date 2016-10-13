package com.group66.game.shop;

import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;

public class BuyScoreMultiplier extends BuyStateInstance {
    
    public BuyScoreMultiplier() {
        this.setCurrent(new None());
    }
    
    class None implements BuyState {
        public void buy(BuyStateInstance instance) {
            /* Set the current state to the next one */
            instance.setCurrent(new Mulp5());
            BustaMove.logger.log(MessageType.Info, "Set new state to Mulp5");
        }
    }
    
    class Mulp5 implements BuyState {
        public void buy(BuyStateInstance instance) {
            instance.setCurrent(new Mulp10());
            BustaMove.logger.log(MessageType.Info, "Set new state to Mulp10");
        }
        
    }
    
    class Mulp10 implements BuyState {
        public void buy(BuyStateInstance instance) {
            instance.setCurrent(new Mulp20());
            BustaMove.logger.log(MessageType.Info, "Set new state to Mulp20");
        }
    }
    
    class Mulp20 implements BuyState {
        public void buy(BuyStateInstance instance) {
            instance.setCurrent(this);
            BustaMove.logger.log(MessageType.Info, "Stay in the top state (Mulp20)");
        }
    }
}
