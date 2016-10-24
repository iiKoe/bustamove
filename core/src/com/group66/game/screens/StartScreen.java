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
import com.group66.game.BustaMove;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.input.NameInputListener;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * A Class for the StarScreen of the game.
 */
public class StartScreen extends AbstractMenuScreen {

    private static DynamicSettings dynamicSettings = new DynamicSettings();
    
    /** screen buttons */
    private TextButton setName;
    private TextButton startButton;
    
    /**
     * Instantiates a new start screen.
     */
    public StartScreen() {
        this.game = BustaMove.getGameInstance();
        BustaMove.getGameInstance().getHighScoreManager().loadData();
        createScreen();
        BustaMove.getGameInstance().log(MessageType.Info, "Loaded the startup menu screen");
    }

    /**
     * instantiates a new start screen object
     * @param dynamicSettings
     */
    public StartScreen(DynamicSettings dynamicSettings) {
        this();
    }

    private void createScreen() {
        loadRelatedGraphics();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        loadButtonMaterials();
        setupButtons();
        stage.addActor(setName);
        stage.addActor(startButton);
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
        BustaMove.getGameInstance().batch.draw(mmbg, Config.SINGLE_PLAYER_OFFSET, 0, Config.LEVEL_WIDTH,
                Gdx.graphics.getHeight());
        BustaMove.getGameInstance().batch.end();
        stage.act();
        stage.draw();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#show()
     */
    @Override
    public void show() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        // game.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#pause()
     */
    @Override
    public void pause() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#resume()
     */
    @Override
    public void resume() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#hide()
     */
    @Override
    public void hide() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.Screen#dispose()
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    
    public void setupButtons() {
        loadButtonMaterials();
        //all magic numbers in this section are offsets values adjusted to get better looks
        int yoffset = Gdx.graphics.getHeight() / 2 + Config.BUTTON_HEIGHT + Config.BUTTON_SPACING - 50;
        int leftcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH - 250) / 2;
        int rightcol = (Gdx.graphics.getWidth() - Config.BUTTON_WIDTH + 250) / 2;

        setName = new TextButton("Enter name", textButtonStyle);
        setName.setPosition(leftcol, yoffset);
        startButton = new TextButton("Start game!", textButtonStyle);
        startButton.setPosition(rightcol, yoffset);
        // Add a listener to the button. ChangeListener is fired when the
        // button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the
        // event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked.
        // Also, canceling a ClickListener event won't
        // revert the checked state.
        setName.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                NameInputListener listener = new NameInputListener(dynamicSettings);
                Gdx.input.getTextInput(listener, "Enter your name", "", "");
            }
        });
        startButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                if (!dynamicSettings.getName().equals("")) {
                    AssetLoader.profileManager.readData(dynamicSettings.getName(), dynamicSettings);
                    dispose();
                    game.setScreen(new MainMenuScreen());
                }
            }
        });    
    }

}
