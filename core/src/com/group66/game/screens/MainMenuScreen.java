package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.group66.game.BustaMove;

/**
 * A Class for the MainMenuScreen of the game.
 */
public class MainMenuScreen implements Screen {

	/** The Constant PLAY_BUTTON_WIDTH. */
	private static final int PLAY_BUTTON_WIDTH = 50;

	/** The Constant PLAY_BUTTON_HEIGHT. */
	private static final int PLAY_BUTTON_HEIGHT = 50;

	/** A place to store the game instance. */
	private BustaMove game;

	/** The play button active texture. */
	private Texture playButtonActive;

	/** The play button inactive texture. */
	private Texture playButtonInactive;

	/**
	 * Instantiates a new main menu screen.
	 *
	 * @param game the game instance
	 */
	public MainMenuScreen(BustaMove game) {
		this.game = game;
		playButtonActive = new Texture("ball-red.png");
		playButtonInactive = new Texture("ball-blue.png");
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		if (Gdx.input.getX() < getCenterWidth(PLAY_BUTTON_WIDTH)
				+ PLAY_BUTTON_WIDTH
				&& Gdx.input.getX() > getCenterWidth(PLAY_BUTTON_WIDTH)
				&& game.getGameHeight() - Gdx.input.getY() < getCenterHeight(PLAY_BUTTON_HEIGHT)
				+ PLAY_BUTTON_HEIGHT
				&& game.getGameHeight() - Gdx.input.getY() > getCenterHeight(PLAY_BUTTON_HEIGHT)) {
			game.batch.draw(playButtonActive,
					getCenterWidth(PLAY_BUTTON_WIDTH),
					getCenterHeight(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
					PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive,
					getCenterWidth(PLAY_BUTTON_WIDTH),
					getCenterHeight(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
					PLAY_BUTTON_HEIGHT);
		}

		game.batch.end();
	}


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// game.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {

	}

	/**
	 * Gets the center width.
	 *
	 * @param size the size
	 * @return the center width
	 */
	private int getCenterWidth(int size) {
		return game.getGameWidth() / 2 - size / 2;
	}

	/**
	 * Gets the center height.
	 *
	 * @param size the size
	 * @return the center height
	 */
	private int getCenterHeight(int size) {
		return game.getGameHeight() / 2 - size / 2;
	}
}
