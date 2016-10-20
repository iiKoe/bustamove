/**
 * 
 */
package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * @author Jeroen
 *
 */
public class YouWinScreenCareer extends AbstractYouWinScreen {

    /**
     * @param dynamicSettings TODO
     * 
     */
    public YouWinScreenCareer(DynamicSettings dynamicSettings) {
        super(dynamicSettings);
    }
    
    /* (non-Javadoc)
     * @see com.group66.game.screens.AbstractYouWinScreen#makeButtons(com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle)
     */
    @Override
    protected void makeButtons(TextButtonStyle textButtonStyle) {
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;

        TextButton levelButton = new TextButton("Play next level", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);

        TextButton exitButton = new TextButton("Main menu", textButtonStyle);
        exitButton.setPosition(centercol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);

        stage.addActor(levelButton);
        stage.addActor(exitButton);

        // Add a listener to the button. ChangeListener is fired when the
        // button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the
        // event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't
        // revert the checked state.
        levelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(dynamicSettings));
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new MainMenuScreen());
            }
        });
    }

}
