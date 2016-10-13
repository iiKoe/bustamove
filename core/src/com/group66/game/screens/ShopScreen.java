package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.group66.game.cannon.BallManager;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;
import com.group66.game.shop.BuyScoreMultiplier;
import com.group66.game.shop.BuySpecialBombChance;
import com.group66.game.shop.BuySpeedBoost;
import com.group66.game.BustaMove;

/**
 * A Class for the MainMenuScreen of the game.
 */
public class ShopScreen implements Screen {
    /** A place to store the game instance. */
    private BustaMove game;
    private DynamicSettings dynamicSettings;

    private Stage stage;
    private Skin skin;
    
    private TextButton buyScoreMultiplierButton;
    private TextButton buyBombChanceButton;
    private TextButton buySpeedMultiplierButton;
    
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;
    private BuySpecialBombChance buySpecialBombChanceStateMachine;
    private BuySpeedBoost buySpeedBoostStateMachine;

    /**
     * Instantiates a new main menu screen.
     *
     * @param game the game instance
     */
    public ShopScreen(BustaMove game, DynamicSettings dynamicSettings) {
        this.game = game;
        this.dynamicSettings = dynamicSettings;
        AssetLoader.load();
        createScreen();
    }

    private void createScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap((int) (Config.BUTTON_WIDTH * 2.5), Config.BUTTON_HEIGHT, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Configure a TextButtonStyle and name it "default". Skin resources are
        // stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        //textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int centercol = (int) ((Gdx.graphics.getWidth() - Config.BUTTON_WIDTH * 2.5) / 2);
        
        TextButton levelButton = new TextButton("Main Menu", textButtonStyle);
        levelButton.setPosition(centercol, yoffset);
        
        /* Buy Score Multiplier */
        buyScoreMultiplierStateMachine = dynamicSettings.getBuyScoreMultiplierStateMachine();
        buyScoreMultiplierButton = new TextButton("Buy: ", 
                textButtonStyle);
        buyScoreMultiplierButton.setPosition(centercol, yoffset - (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        /* Buy Special Bomb Chance */
        buySpecialBombChanceStateMachine = dynamicSettings.getBuySpecialBombChanceStateMachine();
        buyBombChanceButton = new TextButton("Buy: ", 
                textButtonStyle);
        buyBombChanceButton.setPosition(centercol, yoffset - 2 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        /* Buy Ball Speed Multiplier */ 
        buySpeedBoostStateMachine = dynamicSettings.getBuySpeedBoostStateMachine();
        buySpeedMultiplierButton = new TextButton("Buy: ", 
                textButtonStyle);
        buySpeedMultiplierButton.setPosition(centercol, yoffset - 3 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));
        
        stage.addActor(levelButton);
        stage.addActor(buyScoreMultiplierButton);
        stage.addActor(buyBombChanceButton);
        stage.addActor(buySpeedMultiplierButton);

        // Add a listener to the button. ChangeListener is fired when the
        // button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the
        // event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't
        // revert the checked state.
        levelButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        
        buyScoreMultiplierButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                buyScoreMultiplierStateMachine.buy(dynamicSettings);
            }
        });
        
        buyBombChanceButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                buySpecialBombChanceStateMachine.buy(dynamicSettings);
            }
        });
        
        buySpeedMultiplierButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                buySpeedBoostStateMachine.buy(dynamicSettings);
            }
        });
    }
    
    private void update() {
        /* Update the Buy Score Multiplier */
        if (!buyScoreMultiplierStateMachine.isFinalState()) {
            buyScoreMultiplierButton.setText("Buy a score increase of " + buyScoreMultiplierStateMachine.getNextStateInfo() + 
                    " For " + buyScoreMultiplierStateMachine.getNextStateCost() + " Coins!");
        } else {
            buyScoreMultiplierButton.setText("The maximum Score increase of " + 
                    buyScoreMultiplierStateMachine.getNextStateInfo() + " is reached");
            buyScoreMultiplierButton.setColor(Color.DARK_GRAY);
        }
        
        /* Update Buy Special Bomb Chance */
        if (!buySpecialBombChanceStateMachine.isFinalState()) {
            buyBombChanceButton.setText("Buy a Special Bomb Chance increase of " + 
                    buySpecialBombChanceStateMachine.getNextStateInfo() + 
                    " For " + buySpecialBombChanceStateMachine.getNextStateCost() + " Coins!");
        } else {
            buyBombChanceButton.setText("The maximum Special Bomb Chance increase of " + 
                    buySpecialBombChanceStateMachine.getNextStateInfo() + " is reached");
            buyBombChanceButton.setColor(Color.DARK_GRAY);
        }
        
        /* Update Buy Ball Speed Multiplier */ 
        if (!buySpeedBoostStateMachine.isFinalState()) {
            buySpeedMultiplierButton.setText("Buy a Ball Speed increase of " + buySpeedBoostStateMachine.getNextStateInfo() + 
                    " For " + buySpeedBoostStateMachine.getNextStateCost() + " Coins!");
        } else {
            buySpeedMultiplierButton.setText("The maximum Ball Speed increase of " + 
                    buySpeedBoostStateMachine.getNextStateInfo() + " is reached");
            buySpeedMultiplierButton.setColor(Color.DARK_GRAY);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {
        update();
        
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        /* Draw the background */
        game.batch.begin();
        game.batch.enableBlending();
        game.batch.draw(AssetLoader.youwinbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        game.batch.end();
        
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
}
