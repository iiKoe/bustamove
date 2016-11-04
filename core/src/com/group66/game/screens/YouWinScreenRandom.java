/**
 * 
 */
package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.screencontrollers.YouWinRandomController;
import com.group66.game.screencontrollers.actions.ExitButton;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.settings.Config;

/**
 * @author Jeroen
 *
 */
public class YouWinScreenRandom extends AbstractYouWinScreen {
    
    /** The controller. */
    private YouWinRandomController controller;

    /**
     * Instantiates a new you win screen random.
     */
    public YouWinScreenRandom() {
        super();
        controller = new YouWinRandomController(this);
    }

    /* (non-Javadoc)
     * @see com.group66.game.screens.AbstractYouWinScreen#makeButtons
     * (com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle)
     */
    @Override
    protected void makeButtons(TextButtonStyle textButtonStyle) {
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;

        TextButton levelButton = new TextButton("Main Menu", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);

        TextButton exitButton = new TextButton("Exit", textButtonStyle);
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
                controller.performUserAction(new MainMenuButton());
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new ExitButton());
            }
        });
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        loadRelatedGraphics();
        
        /* Draw the background */
        SpriteBatch batch = BustaMove.getGameInstance().getBatch();
        batch.begin();
        batch.enableBlending();
        batch.draw(youwinbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

}
