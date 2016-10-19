package com.group66.game.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AssetLoaderTest {
    @Test
    public void loadTest() {
        AssetLoader.load();
    }
    
    /*
    @SuppressWarnings("unused")
    @Test
    public void textureTest() {
        AssetLoader.load();
        
        assertEquals(AssetLoader.mmbgTexture, new Texture(Gdx.files.internal("main_menu.png")));
        Texture youwinbgTexture = new Texture(Gdx.files.internal("youwin.png"));
        Texture youlosebgTexture = new Texture(Gdx.files.internal("youlose.png"));
        
        Texture bwTexture = new Texture(Gdx.files.internal("roof.png"));
        Texture bgTexture = new Texture(Gdx.files.internal("purplebg.png"));
        Texture pausebgTexture = new Texture(Gdx.files.internal("pause_screen.png"));
        Texture ballTexture = new Texture(Gdx.files.internal("ballTextures.png"));
        Texture bomb = new Texture(Gdx.files.internal("bomb.png"));
    }
    */
    
    @SuppressWarnings("unused")
    @Test
    public void animationTest() {
        AssetLoader.load();
        Animation blue = AssetLoader.getBluePopAnimation();
        Animation green = AssetLoader.getGreenPopAnimation();
        Animation red = AssetLoader.getRedPopAnimation();
        Animation yellow = AssetLoader.getYellowPopAnimation();
    }
}
