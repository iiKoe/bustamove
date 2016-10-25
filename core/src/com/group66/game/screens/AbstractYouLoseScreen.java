package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;
import com.group66.game.BustaMove;

/**
 * A Class for the MainMenuScreen of the game.
 */
public abstract class AbstractYouLoseScreen implements Screen {

    protected Stage stage;
    protected Skin skin;
    protected DynamicSettings dynamicSettings;

    /** 
     * Textures for the sprites
     */
    /** YouLose Screen background texture. */
    private Texture youlosebgTexture;

    /** YouLose Screen background texture region. */
    public TextureRegion youlosebg;
    
    /**
     * Instantiates a new main menu screen.
     */
    public AbstractYouLoseScreen(DynamicSettings dynamicSettings) {
        this.dynamicSettings = dynamicSettings;
        createScreen();
    }

    protected void createScreen() { }

    /**
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
        BustaMove.getGameInstance().batch.end();
        
        stage.act();
        stage.draw();
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
        youlosebgTexture.dispose();
        youlosebg.getTexture().dispose();
    }
    
    /**
     * loads related graphics
     */
    public void loadRelatedGraphics() {
      //Creating the YouLose screen background
        youlosebgTexture = new Texture(Gdx.files.internal("youlose.png"));
        youlosebgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        youlosebg = new TextureRegion(youlosebgTexture, 0, 0, 600, 880);

    }
    
}
