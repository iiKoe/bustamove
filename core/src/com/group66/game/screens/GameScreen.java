package com.group66.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallManager;
import com.group66.game.cannon.Cannon;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.LevelLoader;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.input.InputHandler;
import com.group66.game.settings.Config;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.helpers.TimeKeeper;

/**
 * The Class for the main GameScreen of the game.
 */
public class GameScreen implements Screen {

	/**
	 * The Enum GameState.
	 */
	private enum GameState {
		
		/** The game is running. */
		RUNNING,
		
		/** The game is paused. */
		PAUSED
	}
	
	/** A place to store the game instance. */
	public static BustaMove game;
	/*
	 * ^^^^ made this object public static so it can be used in other classes 
	 * (we would like to use this game instance because we're playing in it, without
	 * creating a new one in the other class that's calling this object because that won't make any sense)
	 * but, does making it "static" has any affect?
	 */
	
	/** The game state. */
	private GameState gameState;
	
	/**  The TimeKeeper. */
	public static TimeKeeper timeKeeper = new TimeKeeper();
	
	/**  The score keeper. */
	public static ScoreKeeper scoreKeeper = new ScoreKeeper();

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
	
	/**  needed to draw text, draw score. */
	private TextDrawer textDrawer = new TextDrawer();
	
	/**  Used to draw the roof. */
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	/**
	 * Instantiates the game screen.
	 * 
	 * @param game
	 *            the game instance
	 * @param randomLevel
	 *            determines if a set level or a random level is used
	 */
	public GameScreen(BustaMove game, Boolean randomLevel) {
		GameScreen.game = game;
		gameState = GameState.RUNNING;
		setup_keys();
		AssetLoader.load();
		AudioManager.startMusic();

		if (!randomLevel) {
		    LevelLoader.loadLevel(ballManager);
		} else {
		    LevelLoader.generateLevel(ballManager);
		}

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
		
		/* Don't update and render when the game is paused */
		if (gameState == GameState.PAUSED) {
			game.batch.begin();
			game.batch.draw(AssetLoader.pausebg, 0, 0, Config.WIDTH, Config.HEIGHT);
			game.batch.end();
			return;
		}

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
		textDrawer.drawScore(game.batch, scoreKeeper.getCurrentScore());
		
		/* Draw the balls */
		ballManager.draw(game.batch, delta);
		
		/* Check if balls need to move down */
		if (ballManager.getBallCount() >= Config.NBALLS_ROW_DOWN 
				&& ballManager.canShoot()) {
			System.out.println("Move balls down");
			ballManager.moveRowDown();
			ballManager.setBallCount(0);
		}

		/* Draw the cannon */
		cannon.draw(game.batch);
		
		/* Check if game-over condition is reached */
		if (ballManager.isGameOver()) {
			game.setScreen(new YouLoseScreen(game));
		}

		/* Draw the brick wall */
		Rectangle hitbox = ballManager.getRoofHitbox();
		game.batch.draw(AssetLoader.bw, hitbox.x + Config.BOUNCE_X_MIN,
				hitbox.y + 10, Config.BOUNCE_X_MAX - Config.BOUNCE_X_MIN,
				hitbox.y);
		
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
	    AudioManager.stopMusic();
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
		inputHandler.registerKeyMap("Toggle Pause", Keys.ESCAPE);


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
		
		inputHandler.registerKeyJustPressedFunc("Toggle Pause",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						switch (gameState) {
						case PAUSED:
							gameState = GameState.RUNNING;
							AudioManager.startMusic();
							System.out.println("Resume the game");
							break;
						case RUNNING:
							gameState = GameState.PAUSED;
							AudioManager.stopMusic();
							System.out.println("Pause the game");
							break;
						default:
							break;
						}
					}
				});
	}
}
