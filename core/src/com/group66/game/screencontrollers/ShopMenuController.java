package com.group66.game.screencontrollers;

import com.badlogic.gdx.Screen;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.actions.BuyBombChanceButton;
import com.group66.game.screencontrollers.actions.BuyExtraLifeButton;
import com.group66.game.screencontrollers.actions.BuyScoreMultiplierButton;
import com.group66.game.screencontrollers.actions.BuySpeedMultiplierButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.settings.Config;
import com.group66.game.shop.BuyScoreMultiplier;
import com.group66.game.shop.BuySpecialBombChance;
import com.group66.game.shop.BuySpeedBoost;

public class ShopMenuController extends AbstractMenuController {
    
    /** The buy score multiplier state machine. */
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;

    /** The buy special bomb chance state machine. */
    private BuySpecialBombChance buySpecialBombChanceStateMachine;

    /** The buy speed boost state machine. */
    private BuySpeedBoost buySpeedBoostStateMachine;


    public ShopMenuController(Screen screen) {
        super(screen);
        buyScoreMultiplierStateMachine = 
                BustaMove.getGameInstance().getDynamicSettings().getBuyScoreMultiplierStateMachine();
        buySpecialBombChanceStateMachine = 
                BustaMove.getGameInstance().getDynamicSettings().getBuySpecialBombChanceStateMachine();
        buySpeedBoostStateMachine = BustaMove.getGameInstance().getDynamicSettings().getBuySpeedBoostStateMachine();
        
    }

    @Override
    public void setupKeys() {
        // TODO Auto-generated method stub

    }
    
    public void performUserAction(MainMenuButton action) {
        if (action != null) {
            screen.dispose();
            try {
                BustaMove.getGameInstance().setScreen(action.getOrigin().getClass()
                        .getConstructor().newInstance());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void performUserAction(BuyScoreMultiplierButton action) {
        if (action != null) {
            buyScoreMultiplierStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }

    
    public void performUserAction(BuyBombChanceButton action) {
        if (action != null) {
            buySpecialBombChanceStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }
    
    public void performUserAction(BuySpeedMultiplierButton action) {
        if (action != null) {
            buySpeedBoostStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }
    
    public void performUserAction(BuyExtraLifeButton action) {
        if (action != null) {
            if (!BustaMove.getGameInstance().getDynamicSettings().hasExtraLife() 
                    && BustaMove.getGameInstance().getDynamicSettings().getCurrency() >= Config.EXTRA_LIFE_COST) {
                BustaMove.getGameInstance().getDynamicSettings().setExtraLife(true, true);
                BustaMove.getGameInstance().getDynamicSettings().addCurrency(-1 * Config.EXTRA_LIFE_COST, true);
                BustaMove.getGameInstance().log(MessageType.Info, "Extra Life bought");
            }
        }
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
