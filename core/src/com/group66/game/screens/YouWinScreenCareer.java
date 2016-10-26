/**
 * 
 */
package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.settings.Config;

/**
 * @author Jeroen
 *
 */
public class YouWinScreenCareer extends AbstractYouWinScreen {

    private Screen ownInstance;

    /** The text drawer. */
    private TextDrawer textDrawer;
    /**
     * 
     */
    public YouWinScreenCareer() {
        super();
        ownInstance = this;
    }

    /* (non-Javadoc)
     * @see com.group66.game.screens.AbstractYouWinScreen#makeButtons
     * (com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle)
     */
    @Override
    protected void makeButtons(TextButtonStyle textButtonStyle) {


        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        bfont.setColor(Color.BLACK);
        skin.add("default", bfont);

        // Setup the text drawer to show the amount of coins
        textDrawer = new TextDrawer();
        textDrawer.myFont.setColor(Color.BLACK);

        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;        

        TextButton levelButton = new TextButton("Play next level", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);

        TextButton shopButton = new TextButton("Shop", textButtonStyle);
        shopButton.setPosition(centercol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);

        TextButton exitButton = new TextButton("Main menu", textButtonStyle);
        exitButton.setPosition(centercol, yoffset - 2 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));

        stage.addActor(levelButton);
        stage.addActor(shopButton);
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
                BustaMove.getGameInstance().setScreen(new OnePlayerGameScreen(BustaMove.getGameInstance().getDynamicSettings()));
            }
        });

        shopButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new ShopScreen(ownInstance));

            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                BustaMove.getGameInstance().setScreen(new MainMenuScreen());
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
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        TextureRegion bg = youwinbg;
        if (BustaMove.getGameInstance().getDynamicSettings().getLevelCleared() == Config.NUMBER_OF_LEVELS ) {
            bg = youwinAllbg;
        }
        BustaMove.getGameInstance().batch.draw(bg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        textDrawer.draw(BustaMove.getGameInstance().batch, "You have cleared " + BustaMove.getGameInstance().getDynamicSettings().getLevelCleared() 
                + " out of " + Config.NUMBER_OF_LEVELS + " levels!", 
                Config.WIDTH / 2 - Config.LEVEL_WIDTH / 2 + Config.CURRENCY_X - 100, Config.CURRENCY_Y - 50);

        BustaMove.getGameInstance().batch.end();

        stage.act();
        stage.draw();
    }

}
