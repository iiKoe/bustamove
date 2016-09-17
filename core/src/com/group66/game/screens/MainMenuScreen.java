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

/**
 * A Class for the MainMenuScreen of the game.
 */
public class MainMenuScreen implements Screen {

    /** The Constant PLAY_BUTTON_WIDTH. */
    private static final int PLAY_BUTTON_WIDTH = 50;

    /** The Constant PLAY_BUTTON_HEIGHT. */
    private static final int PLAY_BUTTON_HEIGHT = 50;

    // TODO: either make scalable or move to config
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_SPACING = 20;

    /** A place to store the game instance. */
    private BustaMove game;

    /** The play button active texture. */
    private Texture playButtonActive;

    /** The play button inactive texture. */
    private Texture playButtonInactive;

    private Stage stage;
    private Skin skin;

    /**
     * Instantiates a new main menu screen.
     *
     * @param game
     *            the game instance
     */
    public MainMenuScreen(BustaMove game) {
        this.game = game;
        playButtonActive = new Texture("ball-red.png");
        playButtonInactive = new Texture("ball-blue.png");

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
        Pixmap pixmap = new Pixmap(BUTTON_WIDTH, BUTTON_HEIGHT, Format.RGBA8888);
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

        TextButton levelButton = new TextButton("Play: Level 1", textButtonStyle);
        TextButton randomButton = new TextButton("Play: Random Level", textButtonStyle);
        TextButton scoresButton = new TextButton("High scores", textButtonStyle);
        TextButton exitButton = new TextButton("Exit", textButtonStyle);

        int yoffset = Gdx.graphics.getHeight() / 2 + 2 * (BUTTON_HEIGHT + BUTTON_SPACING);
        levelButton.setPosition((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2, yoffset - BUTTON_HEIGHT - BUTTON_SPACING);
        randomButton.setPosition((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2,
                yoffset - 2 * (BUTTON_HEIGHT + BUTTON_SPACING));
        scoresButton.setPosition((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2,
                yoffset - 3 * (BUTTON_HEIGHT + BUTTON_SPACING));
        exitButton.setPosition((Gdx.graphics.getWidth() - BUTTON_WIDTH) / 2,
                yoffset - 4 * (BUTTON_HEIGHT + BUTTON_SPACING));
        stage.addActor(levelButton);
        stage.addActor(randomButton);
        stage.addActor(scoresButton);
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
                game.setScreen(new GameScreen(game, false));
            }
        });
        randomButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, true));
            }
        });
        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
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
        game.batch.begin();

        /*
         * if (Gdx.input.getX() < getCenterWidth(PLAY_BUTTON_WIDTH) +
         * PLAY_BUTTON_WIDTH && Gdx.input.getX() >
         * getCenterWidth(PLAY_BUTTON_WIDTH) && game.getGameHeight() -
         * Gdx.input.getY() < getCenterHeight(PLAY_BUTTON_HEIGHT) +
         * PLAY_BUTTON_HEIGHT && game.getGameHeight() - Gdx.input.getY() >
         * getCenterHeight(PLAY_BUTTON_HEIGHT)) {
         * game.batch.draw(playButtonActive, getCenterWidth(PLAY_BUTTON_WIDTH),
         * getCenterHeight(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
         * PLAY_BUTTON_HEIGHT); if (Gdx.input.isTouched()) { this.dispose();
         * game.setScreen(new GameScreen(game)); } } else {
         * game.batch.draw(playButtonInactive,
         * getCenterWidth(PLAY_BUTTON_WIDTH),
         * getCenterHeight(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
         * PLAY_BUTTON_HEIGHT); }
         */

        game.batch.end();

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

    }

    /**
     * Gets the center width.
     *
     * @param size
     *            the size
     * @return the center width
     */
    private int getCenterWidth(int size) {
        return game.getGameWidth() / 2 - size / 2;
    }

    /**
     * Gets the center height.
     *
     * @param size
     *            the size
     * @return the center height
     */
    private int getCenterHeight(int size) {
        return game.getGameHeight() / 2 - size / 2;
    }
}
