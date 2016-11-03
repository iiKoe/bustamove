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

/**
 * The Class ShopMenuController.
 */
public class ShopMenuController extends AbstractMenuController {
    
    /** The buy score multiplier state machine. */
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;

    /** The buy special bomb chance state machine. */
    private BuySpecialBombChance buySpecialBombChanceStateMachine;

    /** The buy speed boost state machine. */
    private BuySpeedBoost buySpeedBoostStateMachine;


    /**
     * Instantiates a new shop menu controller.
     *
     * @param screen the screen
     */
    public ShopMenuController(Screen screen) {
        super(screen);
        buyScoreMultiplierStateMachine = 
                BustaMove.getGameInstance().getDynamicSettings().getBuyScoreMultiplierStateMachine();
        buySpecialBombChanceStateMachine = 
                BustaMove.getGameInstance().getDynamicSettings().getBuySpecialBombChanceStateMachine();
        buySpeedBoostStateMachine = BustaMove.getGameInstance().getDynamicSettings().getBuySpeedBoostStateMachine();
        
    }

    /* (non-Javadoc)
     * @see com.group66.game.screencontrollers.AbstractMenuController#setupKeys()
     */
    @Override
    public void setupKeys() { }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(MainMenuButton action) {
        if (action != null) {
            screen.dispose();
            try {
                BustaMove.getGameInstance().setScreen(action.getOrigin().getClass()
                        .getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(BuyScoreMultiplierButton action) {
        if (action != null) {
            buyScoreMultiplierStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }

    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(BuyBombChanceButton action) {
        if (action != null) {
            buySpecialBombChanceStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(BuySpeedMultiplierButton action) {
        if (action != null) {
            buySpeedBoostStateMachine.buy(BustaMove.getGameInstance().getDynamicSettings());
        }
    }
    
    /**
     * Perform user action.
     *
     * @param action the action
     */
    public void performUserAction(BuyExtraLifeButton action) {
        if (action != null && !BustaMove.getGameInstance().getDynamicSettings().hasExtraLife() 
                && BustaMove.getGameInstance().getDynamicSettings().getCurrency() >= Config.EXTRA_LIFE_COST) {
            BustaMove.getGameInstance().getDynamicSettings().setExtraLife(true, true);
            BustaMove.getGameInstance().getDynamicSettings().addCurrency(-1 * Config.EXTRA_LIFE_COST, true);
            BustaMove.getGameInstance().log(MessageType.Info, "Extra Life bought");
        }
    }

    /**
     * Gets the buy score multiplier state machine.
     *
     * @return the buy score multiplier state machine
     */
    public BuyScoreMultiplier getBuyScoreMultiplierStateMachine() {
        return buyScoreMultiplierStateMachine;
    }

    /**
     * Gets the buy special bomb chance state machine.
     *
     * @return the buy special bomb chance state machine
     */
    public BuySpecialBombChance getBuySpecialBombChanceStateMachine() {
        return buySpecialBombChanceStateMachine;
    }

    /**
     * Gets the buy speed boost state machine.
     *
     * @return the buy speed boost state machine
     */
    public BuySpeedBoost getBuySpeedBoostStateMachine() {
        return buySpeedBoostStateMachine;
    }

}
