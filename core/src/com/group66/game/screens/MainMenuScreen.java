package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.group66.game.BustaMove;

public class MainMenuScreen implements Screen {

	private static final int PLAY_BUTTON_WIDTH = 50;
	private static final int PLAY_BUTTON_HEIGHT = 50;

	private BustaMove game;

	private Texture playButtonActive;
	private Texture playButtonInactive;

	public MainMenuScreen(BustaMove game) {
		this.game = game;
		playButtonActive = new Texture("ball-red.png");
		playButtonInactive = new Texture("ball-blue.png");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		if (Gdx.input.getX() < center_width(PLAY_BUTTON_WIDTH)
				+ PLAY_BUTTON_WIDTH
				&& Gdx.input.getX() > center_width(PLAY_BUTTON_WIDTH)
				&& game.getGameHeight() - Gdx.input.getY() < center_height(PLAY_BUTTON_HEIGHT)
						+ PLAY_BUTTON_HEIGHT
				&& game.getGameHeight() - Gdx.input.getY() > center_height(PLAY_BUTTON_HEIGHT)) {
			game.batch.draw(playButtonActive, center_width(PLAY_BUTTON_WIDTH),
					center_height(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
					PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive,
					center_width(PLAY_BUTTON_WIDTH),
					center_height(PLAY_BUTTON_HEIGHT), PLAY_BUTTON_WIDTH,
					PLAY_BUTTON_HEIGHT);
		}

		game.batch.end();
	}
	

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {
		// game.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {

	}
	
	private int center_width(int size) {
		return game.getGameWidth() / 2 - size / 2;
	}

	private int center_height(int size) {
		return game.getGameHeight() / 2 - size / 2;
	}
}
