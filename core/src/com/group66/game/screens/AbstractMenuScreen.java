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
import com.group66.game.BustaMove;
import com.group66.game.settings.Config;

public abstract class AbstractMenuScreen implements Screen {
    /** A place to store the game instance. */
    public BustaMove game = BustaMove.getGameInstance();

    public Stage stage;
    
    protected Skin skin;
    
    protected TextButtonStyle textButtonStyle;
    
    /** MainMenuScreen background texture. */
    private static Texture mmbgTexture;
    
    /** MainMenu Screen background texture region. */
    public static TextureRegion mmbg;

    
    public void render(float delta) {
    }

    public void show() {
    
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    
    /**
     * Loads related graphics
     */
    public void loadRelatedGraphics() {
        //Creating the MainMenu screen background
        mmbgTexture = new Texture(Gdx.files.internal("main_menu.png"));
        mmbgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        mmbg = new TextureRegion(mmbgTexture, 0, 0, 600, 880);
    }
    
    /**
     * sets up screen related buttons
     */
    public void setupButtons() {        
    }
    
    public void loadButtonMaterials() {
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
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

}