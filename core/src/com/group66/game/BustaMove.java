package com.group66.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.settings.Config;

public class BustaMove extends Game {
	
	//public static final int WIDTH = 400;
	//public static final int HEIGHT = 600;
	
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	public int getGameHeight() {
		return Config.HEIGHT;
		//return Gdx.graphics.getHeight();
	}
	
	public int getGameWidth() {
		return Config.WIDTH;
		//return Gdx.graphics.getWidth();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
