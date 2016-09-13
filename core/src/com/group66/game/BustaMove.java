package com.group66.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.screens.MainMenuScreen;

public class BustaMove extends Game {
	
	//public static final int WIDTH = 400;
	//public static final int HEIGHT = 600;
	
	// TODO make a bit nicer
	public static final int WIDTH = 128 + 20;
	public static final int HEIGHT = 220 + 20;
	
	public static final int BOUNCE_X_MIN = 10;
	public static final int BOUNCE_X_MAX = WIDTH - 10;

	public static final int BOUNCE_Y_MIN = 10;
	public static final int BOUNCE_Y_MAX = HEIGHT - 10;

	
	public SpriteBatch batch;
	public OrthographicCamera camera;
	
	public int getGameHeight() {
		return HEIGHT;
		//return Gdx.graphics.getHeight();
	}
	
	public int getGameWidth() {
		return WIDTH;
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
