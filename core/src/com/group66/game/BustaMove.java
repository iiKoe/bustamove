package com.group66.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.helpers.AudioManager;
import com.group66.game.screens.MainMenuScreen;
import com.group66.game.settings.Config;

/**
 * The BustaMove main game class.
 */
public class BustaMove extends Game {	
	
	/** The batch. */
	public SpriteBatch batch;
	
	/** The camera. */
	public OrthographicCamera camera;
	
	/**
	 * Gets the game height.
	 *
	 * @return the game height
	 */
	public int getGameHeight() {
		return Config.HEIGHT;
		//return Gdx.graphics.getHeight();
	}
	
	/**
	 * Gets the game width.
	 *
	 * @return the game width
	 */
	public int getGameWidth() {
		return Config.WIDTH;
		//return Gdx.graphics.getWidth();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		AudioManager.load();
		this.setScreen(new MainMenuScreen(this));
	}
}
