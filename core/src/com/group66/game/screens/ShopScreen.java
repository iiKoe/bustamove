package com.group66.game.screens;

import java.lang.reflect.InvocationTargetException;

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
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.logging.MessageType;
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

    /** The dynamic settings. */
    private DynamicSettings dynamicSettings;

    /** The stage. */
    private Stage stage;

    /** The skin. */
    private Skin skin;

    /** The buy score multiplier button. */
    private TextButton buyScoreMultiplierButton;

    /** The buy bomb chance button. */
    private TextButton buyBombChanceButton;

    /** The buy speed multiplier button. */
    private TextButton buySpeedMultiplierButton;

    /** The buy extra life button. */
    private TextButton buyExtraLifeButton;

    /** The buy score multiplier state machine. */
    private BuyScoreMultiplier buyScoreMultiplierStateMachine;

    /** The buy special bomb chance state machine. */
    private BuySpecialBombChance buySpecialBombChanceStateMachine;

    /** The buy speed boost state machine. */
    private BuySpeedBoost buySpeedBoostStateMachine;

    /** The text drawer. */
    private TextDrawer textDrawer;

    private Screen origin;

    /**
     * Instantiates a new main menu screen.
     *
     * @param dynamicSettings the dynamic settings
     */
    public ShopScreen(DynamicSettings dynamicSettings, Screen origin) {
        this.dynamicSettings = dynamicSettings;
        this.origin = origin;
        AssetLoader.load();
        createScreen();
    }

    /**
     * Creates the screen.
     */
    private void createScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);

        // Setup the text drawer to show the amount of coins
        textDrawer = new TextDrawer();

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

        TextButton levelButton = new TextButton("Back", textButtonStyle);
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

        /* Buy Extra Life */ 
        buyExtraLifeButton = new TextButton("Buy: ", 
                textButtonStyle);
        buyExtraLifeButton.setPosition(centercol, yoffset - 4 * (Config.BUTTON_HEIGHT + Config.BUTTON_SPACING));

        stage.addActor(levelButton);
        stage.addActor(buyScoreMultiplierButton);
        stage.addActor(buyBombChanceButton);
        stage.addActor(buySpeedMultiplierButton);
        stage.addActor(buyExtraLifeButton);

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
                try {
                    BustaMove.getGameInstance().setScreen(origin.getClass()
                            .getConstructor(DynamicSettings.class).newInstance(dynamicSettings));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

        buyExtraLifeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                if (!dynamicSettings.hasExtraLife() && dynamicSettings.getCurrency() >= Config.EXTRA_LIFE_COST) {
                    dynamicSettings.setExtraLife(true);
                    dynamicSettings.addCurrency(-1 * Config.EXTRA_LIFE_COST);
                    BustaMove.getGameInstance().log(MessageType.Info, "Extra Life bought");
                }
            }
        });
    }

    /**
     * Update the buttons.
     */
    private void update() {
        /* Update the Buy Score Multiplier */
        if (!buyScoreMultiplierStateMachine.isFinalState()) {
            buyScoreMultiplierButton.setText("Buy a score increase of "
                    + buyScoreMultiplierStateMachine.getNextStateInfo()
                    + " For " + buyScoreMultiplierStateMachine.getNextStateCost() + " Coins!");
        } else {
            buyScoreMultiplierButton.setText("The maximum Score increase of "
                    + buyScoreMultiplierStateMachine.getNextStateInfo() + " is reached");
            buyScoreMultiplierButton.setColor(Color.DARK_GRAY);
        }

        /* Update Buy Special Bomb Chance */
        if (!buySpecialBombChanceStateMachine.isFinalState()) {
            buyBombChanceButton.setText("Buy a Special Bomb Chance increase of "
                    + buySpecialBombChanceStateMachine.getNextStateInfo()
                    + " For " + buySpecialBombChanceStateMachine.getNextStateCost() + " Coins!");
        } else {
            buyBombChanceButton.setText("The maximum Special Bomb Chance increase of "
                    + buySpecialBombChanceStateMachine.getNextStateInfo() + " is reached");
            buyBombChanceButton.setColor(Color.DARK_GRAY);
        }

        /* Update Buy Ball Speed Multiplier */ 
        if (!buySpeedBoostStateMachine.isFinalState()) {
            buySpeedMultiplierButton.setText("Buy a Ball Speed increase of "
                    + buySpeedBoostStateMachine.getNextStateInfo()
                    + " For " + buySpeedBoostStateMachine.getNextStateCost() + " Coins!");
        } else {
            buySpeedMultiplierButton.setText("The maximum Ball Speed increase of "
                    + buySpeedBoostStateMachine.getNextStateInfo() + " is reached");
            buySpeedMultiplierButton.setColor(Color.DARK_GRAY);
        }

        /* Update Extra Life */
        if (!dynamicSettings.hasExtraLife()) {
            buyExtraLifeButton.setText("Buy an Extra Life For " + Config.EXTRA_LIFE_COST + " Coins!");
        } else {
            buyExtraLifeButton.setText("You already have an Extra Life");
            buyExtraLifeButton.setColor(Color.DARK_GRAY);
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
        BustaMove.getGameInstance().batch.begin();
        BustaMove.getGameInstance().batch.enableBlending();
        BustaMove.getGameInstance().batch.draw(AssetLoader.shopbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        textDrawer.draw(BustaMove.getGameInstance().batch, "You have " + dynamicSettings.getCurrency() + " Coins", 
                Config.WIDTH / 2 - Config.LEVEL_WIDTH / 2 + Config.CURRENCY_X, Config.CURRENCY_Y);
        BustaMove.getGameInstance().batch.end();

        stage.act();
        stage.draw();
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#show()
     */
    @Override
    public void show() {
        
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        

    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#pause()
     */
    @Override
    public void pause() {
       
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#resume()
     */
    @Override
    public void resume() {
        
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#hide()
     */
    @Override
    public void hide() {
       
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.Screen#dispose()
     */
    @Override
    public void dispose() {
        
    }
}
