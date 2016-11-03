package com.group66.game.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group66.game.BustaMove;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.logging.MessageType;
import com.group66.game.screencontrollers.MultiplayerMenuController;
import com.group66.game.screencontrollers.actions.MainMenuButton;
import com.group66.game.screencontrollers.actions.PlayLevelButton;
import com.group66.game.screencontrollers.actions.RandomButton;
import com.group66.game.screencontrollers.actions.SetDifficultyButton;
import com.group66.game.settings.Config;

public class MultiplayerMenuScreen extends AbstractMenuScreen {
    
    /** The controller. */
    private MultiplayerMenuController controller;
    
    private TextDrawer textDrawer;
    private int numPlayers = 2;
    private int yoffset;
    
    /**
     * Instantiates a new multiplayer menu screen.
     */
    public MultiplayerMenuScreen() {
        controller = new MultiplayerMenuController(this);
        yoffset = Gdx.graphics.getHeight() / 2 + 200;
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the main menu screen");
    }

    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // Setup the text drawer to show the amount of coins
        textDrawer = new TextDrawer();
        textDrawer.myFont.setColor(Color.BLACK);
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
        SpriteBatch batch = BustaMove.getGameInstance().batch;
        batch.begin();
        batch.enableBlending();
        batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH, Gdx.graphics.getHeight());
        textDrawer.draw(batch, "Players", (Config.WIDTH - Config.LEVEL_WIDTH) / 2, yoffset + 20);
        //textDrawer.draw(batch, ""+numPlayers, (Config.WIDTH) / 2, yoffset - Config.BUTTON_HEIGHT + 20);
        textDrawer.draw(batch, "Level", 
                (Config.WIDTH - Config.LEVEL_WIDTH) / 2, yoffset - 2 * Config.BUTTON_HEIGHT + 20);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks

        //players
        int leftcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH - 250) / 2;
        final TextButton twoPlayerButton = new TextButton("2 players", textButtonStyle);
        twoPlayerButton.setPosition(leftcol, yoffset - Config.BUTTON_HEIGHT);
        stage.addActor(twoPlayerButton);

        int rightcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH + 250) / 2;
        final TextButton threePlayerButton = new TextButton("3 players", textButtonStyle);
        threePlayerButton.setPosition(rightcol, yoffset - Config.BUTTON_HEIGHT);
        stage.addActor(threePlayerButton);
        
        twoPlayerButton.setChecked(numPlayers == 2);
        threePlayerButton.setChecked(numPlayers == 3);
        
        int centercol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH) / 2;
        //random
        TextButton easyButton = new TextButton("Random: Easy", textButtonStyle);
        easyButton.setPosition(centercol - Config.BUTTON_WIDTH - Config.BUTTON_SPACING,
                yoffset - 3 * Config.BUTTON_HEIGHT);
        stage.addActor(easyButton);
        
        TextButton mediumButton = new TextButton("Random: Medium", textButtonStyle);
        mediumButton.setPosition(centercol, yoffset - 3 * Config.BUTTON_HEIGHT);
        stage.addActor(mediumButton);
        
        TextButton hardButton = new TextButton("Random: Hard", textButtonStyle);
        hardButton.setPosition(centercol + Config.BUTTON_WIDTH + Config.BUTTON_SPACING,
                yoffset - 3 * Config.BUTTON_HEIGHT);
        stage.addActor(hardButton);
        
        for (int i = 1; i <= Config.NUMBER_OF_LEVELS; i++) {
            addLevelButton(i);
        }
        
        //back
        TextButton mainMenuButton = new TextButton("Back", textButtonStyle);
        mainMenuButton.setPosition(centercol, yoffset - 9 * Config.BUTTON_HEIGHT);
        stage.addActor(mainMenuButton);
        
        // Add a listener to the buttons
        twoPlayerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float xpos, float ypos) {
                numPlayers = 2;
                twoPlayerButton.setChecked(true);
                threePlayerButton.setChecked(false);
            }
        });
        threePlayerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float xpos, float ypos) {
                numPlayers = 3;
                twoPlayerButton.setChecked(false);
                threePlayerButton.setChecked(true);
            }
        });
        easyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("easy"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to easy");
                controller.performUserAction(new RandomButton(numPlayers));
            }
        });
        mediumButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("medium"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to medium");
                controller.performUserAction(new RandomButton(numPlayers));
            }
        });
        hardButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new SetDifficultyButton("hard"));
                BustaMove.getGameInstance().log(MessageType.Default, "Difficulty set to hard");
                controller.performUserAction(new RandomButton(numPlayers));
            }
        });
        mainMenuButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new MainMenuButton());
            }
        });
    }
    
    /**
     * Adds a button for the selected level
     * @param level
     */
    private void addLevelButton(final int level) {
        String textureName = "levelimages/level" + new DecimalFormat("00").format(level) + ".png";
        if (!Gdx.files.internal(textureName).exists()) {
            //backup texture
            textureName = "ballTextures.png";
        }
        
        int xoffset = (Gdx.graphics.getWidth() - 500 - 4 * Config.BUTTON_SPACING) / 2;
        int xpos = xoffset + ((level - 1) % 5) * (100 + Config.BUTTON_SPACING);
        int ypos = yoffset - 5 * Config.BUTTON_HEIGHT - Config.BUTTON_SPACING - ((level - 1) / 5)
                * (100 + Config.BUTTON_SPACING);
        
        Texture myTexture = new Texture(Gdx.files.internal(textureName));
        TextureRegion myTextureRegion = new TextureRegion(myTexture, 100, 100);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        
        ImageButton imgButton = new ImageButton(myTexRegionDrawable);
        imgButton.setPosition(xpos, ypos);
        imgButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                controller.performUserAction(new PlayLevelButton(level, numPlayers));
            }
        });
        stage.addActor(imgButton);
    }
}
