package com.group66.game.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.group66.game.BustaMove;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.CareerMenuController;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.ResetButton;
import com.group66.game.screencontrollers.actions.ShopButton;
import com.group66.game.settings.Config;

/**
 * A Class for the MainMenuScreen of the game.
 */
public class CareerScreen extends AbstractMenuScreen {
   
    /** The text drawer. */
    private TextDrawer textDrawer;

    /** The controller. */
    private CareerMenuController controller;

    /**
     * Instantiates a new main menu screen.
     */
    public CareerScreen() {
        controller = new CareerMenuController(this);
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the career menu screen");
    }


    /**
     * creates the screen parts
     */
    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // Setup the text drawer to show the progress of career
        textDrawer = new TextDrawer();
        textDrawer.getFont().setColor(Color.BLACK);
        setupButtons();
    }

    /*
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Draw the background */
        SpriteBatch batch = BustaMove.getGameInstance().getBatch();
        batch.begin();
        batch.enableBlending();
        batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        textDrawer.draw(batch, "You have cleared " + BustaMove.getGameInstance().getDynamicSettings().getLevelCleared()
                + " out of " + Config.NUMBER_OF_LEVELS + " levels!", 
                (Config.WIDTH - Config.LEVEL_WIDTH) / 2 + Config.CURRENCY_X - 100, Config.CURRENCY_Y - 40);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + 20;

        addLevelButtons();
        
        int leftcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH - 250) / 2;
        TextButton shopButton = new TextButton("Shop", textButtonStyle);
        shopButton.setPosition(leftcol, yoffset - 3 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        stage.addActor(shopButton);

        int rightcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH + 250) / 2;
        TextButton resetButton = new TextButton("Reset career", textButtonStyle);
        resetButton.setPosition(rightcol, yoffset - 3 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        stage.addActor(resetButton);

        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;
        TextButton mainMenuButton = new TextButton("Back", textButtonStyle);

        mainMenuButton.setPosition(centercol, yoffset - 4 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        stage.addActor(mainMenuButton);
        
        // Add a listener to the buttons
        shopButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new ShopButton());
            }
        });
        resetButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new ResetButton());
                addLevelButtons();
            }
        });
        mainMenuButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new MainMenuButton());
            }
        });
    }
    
    /**
     * Adds the level buttons.
     */
    private void addLevelButtons() {
        for (int i = 1; i <= Config.NUMBER_OF_LEVELS; i++) {
            addLevelButton(i);
        }
    }
    /**
     * Adds a button for the selected level
     * @param level
     */
    private void addLevelButton(final int level) {
        String textureName = "levelimages/level" + new DecimalFormat("00").format(level) + ".png";
        if (BustaMove.getGameInstance().getDynamicSettings().getLevelCleared() < level - 1) {
            textureName = "levelimages/level" + new DecimalFormat("00").format(level) + "_grey.png";
        }
        if (!Gdx.files.internal(textureName).exists()) {
            //backup texture
            textureName = "ballTextures.png";
        }
        
        int xoffset = (Gdx.graphics.getWidth() - 500 - 4 * Config.BUTTON_SPACING) / 2;
        int xpos = xoffset + ((level - 1) % 5) * (100 + Config.BUTTON_SPACING);
        int ypos = Gdx.graphics.getHeight() / 2 - 10 - ((level - 1) / 5) * (100 + Config.BUTTON_SPACING);
        
        Texture myTexture = new Texture(Gdx.files.internal(textureName));
        TextureRegion myTextureRegion = new TextureRegion(myTexture, 100, 100);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        
        ImageButton imgButton = new ImageButton(myTexRegionDrawable);
        imgButton.setPosition(xpos, ypos);
        imgButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new PlayLevelButton(level));
            }
        });
        stage.addActor(imgButton);
    }

}
