package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.group66.game.helpers.AudioManager;
import com.group66.game.input.InputHandler;

public abstract class AbstractGameScreen implements Screen {
    
    /**
     * The Enum GameState.
     */
    protected enum GameState {
        
        /** The game is running. */
        RUNNING,
        
        /** The game is paused. */
        PAUSED
    }
    
    /** The game state. */
    protected GameState gameState;
    
    /** The input handler. */
    protected InputHandler inputHandler;
    
    
    /**
     * Initialization of all graphic related o
     */
   
    /** GameScreen background texture. */
    private static Texture bgTexture;

    /** GameScreen background texture region. */
    public static TextureRegion bg;
    /** GameScreen brick wall texture. */
    private static Texture bwTexture;

    /** GameScreen brick wall texture region. */
    public static TextureRegion bw;

    /** GameScreen Pause background texture. */
    private static Texture pausebgTexture;

    /** GameScreen Pause background texture region. */
    public static TextureRegion pausebg;

    
    /**
     * Show
     * @see com.badlogic.gdx.Screen#show()
     */
    public void show() { }

    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     * @see com.badlogic.gdx.Screen#render(float)
     */
    public void render(float delta) { }
    
    /**
     * Resize
     * @see com.badlogic.gdx.Screen#resize(int, int)
     */
    public void resize(int width, int height) { }

    /**
     * Pause
     * @see com.badlogic.gdx.Screen#pause()
     */
    public void pause() { }

    /**
     * Unpause
     * @see com.badlogic.gdx.Screen#resume()
     */
    public void resume() { }

    /**
     * Hide
     * @see com.badlogic.gdx.Screen#hide()
     */
    public void hide() { }

    /**
     * Dispose
     * @see com.badlogic.gdx.Screen#dispose()
     */
    public void dispose() {
        
        AudioManager.stopMusic();
        bwTexture.dispose();
        bw.getTexture().dispose();
        bgTexture.dispose();
        bg.getTexture().dispose();
        pausebgTexture.dispose();
        pausebg.getTexture().dispose();
        
    }
    
    public void loadRelatedGraphics() {
        
        //creating the brick wall
        bwTexture = new Texture(Gdx.files.internal("roof.png"));
        bwTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bw = new TextureRegion(bwTexture, 0, 0, 600, 880);

        //creating the background
        bgTexture = new Texture(Gdx.files.internal("purplebg.png"));
        bgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(bgTexture, 0, 0, 128, 220);

        //creating the pause screen
        pausebgTexture = new Texture(Gdx.files.internal("pause_screen.png"));
        pausebgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        pausebg = new TextureRegion(pausebgTexture, 0, 0, 1800, 880);
        
    }

}
