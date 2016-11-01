package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.group66.game.settings.Config;

/**
 * A Class for the YouWinuScreen of the game.
 */
public abstract class AbstractYouWinScreen implements Screen {

    protected Stage stage;
    protected Skin skin;
    
    
    /**
     * Textures for the sprites
     */
    /** YouWin Screen background texture. */
    private Texture youwinbgTexture;

    /** YouWin Screen background texture region. */
    protected TextureRegion youwinbg;
    
    /** YouWin Screen background texture. */
    private Texture youwinAllbgTexture;

    /** YouWin Screen background texture region. */
    protected TextureRegion youwinAllbg;
    
    
    /**
     * Instantiates a new main menu screen.
     */
    public AbstractYouWinScreen() {
        try {
            createScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createScreen() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        skin.add("default", bfont);

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

        makeButtons(textButtonStyle);

    }

    protected void makeButtons(TextButtonStyle textButtonStyle) { };

    /** 
     * @see com.badlogic.gdx.Screen#render(float)
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @see com.badlogic.gdx.Screen#show()
     */
    @Override
    public void show() {

    }

    /**
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        // game.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    /**
     * @see com.badlogic.gdx.Screen#pause()
     */
    @Override
    public void pause() {
    }

    /**
     * @see com.badlogic.gdx.Screen#resume()
     */
    @Override
    public void resume() {
    }

    /**
     * @see com.badlogic.gdx.Screen#hide()
     */
    @Override
    public void hide() {
    }

    /**
     * @see com.badlogic.gdx.Screen#dispose()
     */
    @Override
    public void dispose() {
        youwinbgTexture.dispose();
        youwinbg.getTexture().dispose();
        youwinAllbgTexture.dispose();
        youwinAllbg.getTexture().dispose();
        stage.dispose();
        skin.dispose();
    }
    
    /**
     * loads related graphics
     */
    public void loadRelatedGraphics() {
        //Creating the YouWin screen background
        youwinbgTexture = new Texture(Gdx.files.internal("youwin.png"));
        youwinbgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        youwinbg = new TextureRegion(youwinbgTexture, 0, 0, 600, 880);

        //Creating the YouWin screen background
        youwinAllbgTexture = new Texture(Gdx.files.internal("kicker.gif"));
        youwinAllbgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        youwinAllbg = new TextureRegion(youwinAllbgTexture, 0, 0, 600, 880);      
    }
}
