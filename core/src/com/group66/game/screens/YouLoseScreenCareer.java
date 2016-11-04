/**
 * 
 */
package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.screencontrollers.YouLoseCareerController;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.TryAgainButton;
import com.group66.game.settings.Config;



public class YouLoseScreenCareer extends AbstractYouLoseScreen {

    /** The controller. */
    private YouLoseCareerController controller;
    /** The text drawer. */
    private TextDrawer textDrawer;
    /**
     */
    public YouLoseScreenCareer() {
        super();
        controller = new YouLoseCareerController(this);
    }

    protected void createScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        loadRelatedGraphics();

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        bfont.setColor(Color.BLACK);
        skin.add("default", bfont);

        // Setup the text drawer to show the amount of coins
        textDrawer = new TextDrawer();
        textDrawer.myFont.setColor(Color.WHITE);

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Configure a TextButtonStyle and name it "default". Skin resources are
        // stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;

        TextButton levelButton = new TextButton("Main Menu", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);

        if (BustaMove.getGameInstance().getDynamicSettings().hasExtraLife()) {
            TextButton tryAgainButton = new TextButton("Try again", textButtonStyle);
            tryAgainButton.setPosition(centercol, yoffset - Config.BUTTON_HEIGHT - Config.BUTTON_SPACING);
            stage.addActor(tryAgainButton);
            tryAgainButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    controller.performUserAction(new TryAgainButton());
                }
            });
        }
        

        stage.addActor(levelButton);


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
        
        /* Draw the background */
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        BustaMove.getGameInstance().batch.draw(youlosebg, 
                Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        
        if (!BustaMove.getGameInstance().getDynamicSettings().hasExtraLife()) { 
            BustaMove.getGameInstance().getDynamicSettings().reset();
            textDrawer.draw(BustaMove.getGameInstance().batch, "You died.....Your career is reset.", 
                    Config.WIDTH / 2 - Config.LEVEL_WIDTH / 2 + Config.CURRENCY_X - 100, Config.CURRENCY_Y - 50);
        }
        BustaMove.getGameInstance().batch.end();

        
        stage.act();
        stage.draw();
    }

}
