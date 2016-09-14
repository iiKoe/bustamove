package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallManager;
import com.group66.game.cannon.Cannon;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.input.InputHandler;
import com.group66.game.settings.Config;

/**
 * The Class for the main GameScreen of the game.
 */
public class GameScreen implements Screen {
	
	/** A place to store the game instance. */
	private BustaMove game;

	/** The input handler. */
	private InputHandler inputHandler = new InputHandler();

	/** The cannon. */
	private Cannon cannon = new Cannon(new Texture("cannon.png"),
			Config.WIDTH / 2, 20, 50, 50);

	/** The ball manager. */
	private BallManager ballManager = new BallManager(cannon, Config.BALL_RAD,
			Config.BALL_SPEED);

	/** The run time needed for animations. */
	private float runTime = 0;

	// // TODO MAKE ALL THIS DEPENDEND ON BALL SIZE AND RES
	// used for localizing startingBalls
	/** The odd row. */
	private int[] oddRow = { 0, 16, 32, 48, 64, 80, 96, 112 };
	
	/** The even row. */
	private int[] evenRow = { 8, 24, 40, 56, 72, 88, 104 };

	/**
	 * Instantiates the game screen.
	 *
	 * @param game the game instance
	 */
	public GameScreen(BustaMove game) {
		this.game = game;
		setup_keys();
		AssetLoader.load();

		/* OMAR */
		// // TODO MAKE ALL THIS DEPENDEND ON BALL SIZE AND RES
		// ballManager.addRandomStaticBall(20, 50);
		// first row have 8 balls, second 7, third 8, forth 7
		/* TODO THIS IS UGLY AS HELL, make a better level loading mechanism */
		for (int i = 0; i < 8; i++) {
			ballManager.addRandomStaticBall(Config.BOUNCE_X_MIN
					+ Config.BALL_RAD + oddRow[i], Config.BOUNCE_Y_MAX
					- Config.BALL_RAD - 0);
		}
		for (int i = 8; i < 15; i++) {
			ballManager.addRandomStaticBall(Config.BOUNCE_X_MIN
					+ Config.BALL_RAD + evenRow[i - 8], Config.BOUNCE_Y_MAX
					- Config.BALL_RAD - 16);
		}
		for (int i = 15; i < 23; i++) {
			ballManager.addRandomStaticBall(Config.BOUNCE_X_MIN
					+ Config.BALL_RAD + oddRow[i - 15], Config.BOUNCE_Y_MAX
					- Config.BALL_RAD - 32);
		}
		for (int i = 23; i < 30; i++) {
			ballManager.addRandomStaticBall(Config.BOUNCE_X_MIN
					+ Config.BALL_RAD + evenRow[i - 23], Config.BOUNCE_Y_MAX
					- Config.BALL_RAD - 48);
		}
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		/* Update the runtime */
		runTime += delta;

		/* Handle input keys */
		inputHandler.run();

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.enableBlending();

		/* Draw the background */
		game.batch.draw(AssetLoader.bg, Config.BOUNCE_X_MIN,
				Config.BOUNCE_Y_MIN, 128, 220);

		/* Draw the balls */
		ballManager.draw(game.batch, runTime);

		/* Draw the cannon */
		cannon.draw(game.batch);

		game.batch.end();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

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
		// img.dispose();
	}

	/**
	 * Setup the keys used in the game screen keys.
	 */
	private void setup_keys() {
		// Setup the game keys
		inputHandler.registerKeyMap("Shoot", Keys.SPACE);
		inputHandler.registerKeyMap("Shoot", Keys.BACKSPACE);
		inputHandler.registerKeyMap("Aim Left", Keys.A);
		inputHandler.registerKeyMap("Aim Left", Keys.LEFT);
		inputHandler.registerKeyMap("Aim Right", Keys.D);
		inputHandler.registerKeyMap("Aim Right", Keys.RIGHT);
		inputHandler.registerKeyMap("Place Ball", Keys.ENTER);

		/* Register key names to functions */
		inputHandler.registerKeyPressedFunc("Aim Left",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						cannon.cannonAimAdjust(Config.CANNON_AIM_DELTA);
					}
				});

		inputHandler.registerKeyPressedFunc("Aim Right",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						cannon.cannonAimAdjust(-1f * Config.CANNON_AIM_DELTA);
					}
				});

		inputHandler.registerKeyJustPressedFunc("Shoot",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						ballManager.shootRandomBall();
					}
				});
	}
}
