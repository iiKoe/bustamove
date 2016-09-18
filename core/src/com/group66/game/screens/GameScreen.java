package com.group66.game.screens;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallManager;
import com.group66.game.cannon.Cannon;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.input.InputHandler;
import com.group66.game.settings.Config;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.helpers.TimeKeeper;

/**
 * The Class for the main GameScreen of the game.
 */
public class GameScreen implements Screen {

	/** A place to store the game instance. */
	public static BustaMove game;
	
	/*
	 * ^^^^ made this object public static so it can be used in other classes 
	 * (we would like to use this game instance because we're playing in it, without
	 * creating a new one in the other class that's calling this object because that won't make any sense)
	 * but, does making it "static" has any affect?
	 */
	
	/** The TimeKeeper */
	public static TimeKeeper timeKeeper = new TimeKeeper();

	/** The input handler. */
	private InputHandler inputHandler = new InputHandler();

	/** The cannon. */
	private Cannon cannon = new Cannon(new Texture("cannon.png"),
			Config.WIDTH / 2, Config.CANNON_Y_OFFSET, Config.CANNON_WIDTH,
			Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);

	/** The ball manager. */
	private BallManager ballManager = new BallManager(cannon, Config.BALL_RAD,
			Config.BALL_SPEED);
	
	//for testing
	//ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	/** needed to draw text, draw score */
	private TextDrawer textDrawer = new TextDrawer();

	/**
	 * Instantiates the game screen.
	 * 
	 * @param game
	 *            the game instance
	 * @param randomLevel
	 *            determines if a set level or a random level is used
	 */
	public GameScreen(BustaMove game, Boolean randomLevel) {
		this.game = game;
		setup_keys();
		AssetLoader.load();
		loadLevel();
	}
	
	/**
	 * Instantiates the game screen.
	 * @param game
	 *            the game instance
	 */
	public GameScreen(BustaMove game) {
		this(game, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		
		/* Handle input keys */
		inputHandler.run();

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.enableBlending();

		/* Draw the background */
		game.batch.draw(AssetLoader.bg, Config.BOUNCE_X_MIN,
				Config.BOUNCE_Y_MIN, Config.BOUNCE_X_MAX - Config.BOUNCE_X_MIN,
				Config.BOUNCE_Y_MAX - Config.BOUNCE_Y_MIN);
		
		/* Start counting time*/
		timeKeeper.universalTimeCounter(delta);
		
		/* Check if a ball hasn't been shot in the past 10 seconds */
		timeKeeper.didHeShoot();
		
		/* Draw the score */
		textDrawer.drawScore(game.batch, 99999);
		
		
		/* Draw the balls */
		ballManager.draw(game.batch, delta);

		/* Draw the cannon */
		cannon.draw(game.batch);

		game.batch.end();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {

	}

	/*
	 * (non-Javadoc)
	 * 
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

	/**
	 * Load a test level.
	 */
	private void loadLevel() {
		String levelFilePath = "testlevel.txt";

		try {
			FileHandle handle = Gdx.files.internal(levelFilePath);
			Scanner s = new Scanner(handle.read(), "UTF-8");
			int linenr = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				int ypos = Config.BOUNCE_Y_MAX - (2 * linenr + 1)
						* Config.BALL_RAD;
				for (int i = 0; i < line.length(); i++) {
					int xpos = Config.BOUNCE_X_MIN + (2 * i + 1)
							* Config.BALL_RAD;
					System.out.println("X pos: " + xpos);

					// shift odd rows
					if (linenr % 2 != 0) {
						xpos += Config.BALL_RAD;
					}

					 //TODO get the color from the text file
					ballManager.addStaticBall(0, xpos, ypos);
					
				}
				linenr++;
			}
			

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
