package com.group66.game.screens;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.group66.game.BustaMove;
import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BallManager;
import com.group66.game.cannon.Cannon;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.input.InputHandler;

public class GameScreen implements Screen {
	public static final float MOVE_SPEED = 100;

	/* Main game entity */
	BustaMove game;

	/* Input handler */
	InputHandler inputHandler = new InputHandler();

	/* The cannon */
	Cannon cannon = new Cannon(BustaMove.WIDTH / 2, 20, 50, 50);

	/* Ball manager */
	BallManager ballManager = new BallManager(cannon);

	/* Render border */
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	/* Keep track of the runtime for animations */
	private float runTime=0;

	//// TODO MAKE ALL THIS DEPENDEND ON BALL SIZE AND RES
	// used for localizing startingBalls
	private int[] oddRow = { 0, 16, 32, 48, 64, 80, 96, 112 };
	private int[] evenRow = { 8, 24, 40, 56, 72, 88, 104 };
	// used for random color generation, for getColors function
	int[] ballColors = new int[30];
	
	public GameScreen(BustaMove game) {
		this.game = game;
		setup_keys();
		AssetLoader.load();

		/* OMAR */
		//// TODO MAKE ALL THIS DEPENDEND ON BALL SIZE AND RES
		//ballManager.addRandomStaticBall(20, 50);
		// first row have 8 balls, second 7, third 8, forth 7
		/* TODO THIS IS UGLY AS HELL, make a better level loading mechanism */
		/*
		for (int i = 0; i < 8; i++) {
			ballManager.addRandomStaticBall(BustaMove.BOUNCE_X_MIN + Ball.BALL_RAD + oddRow[i], 
					BustaMove.BOUNCE_Y_MAX - Ball.BALL_RAD - 0);
		}
		for (int i = 8; i < 15; i++) {
			ballManager.addRandomStaticBall(BustaMove.BOUNCE_X_MIN + Ball.BALL_RAD + evenRow[i - 8], 
					BustaMove.BOUNCE_Y_MAX - Ball.BALL_RAD - 16);
		}
		for (int i = 15; i < 23; i++) {
			ballManager.addRandomStaticBall(BustaMove.BOUNCE_X_MIN + Ball.BALL_RAD + oddRow[i - 15], 
					BustaMove.BOUNCE_Y_MAX  - Ball.BALL_RAD - 32);
		}
		for (int i = 23; i < 30; i++) {
			ballManager.addRandomStaticBall(BustaMove.BOUNCE_X_MIN + Ball.BALL_RAD + evenRow[i - 23], 
					BustaMove.BOUNCE_Y_MAX - Ball.BALL_RAD - 48);
		}
		*/
		loadLevel();
	}

	/* Setup keys ued in the game */
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
						cannon.cannonAimAdjust(1.0f);
					}
				});

		inputHandler.registerKeyPressedFunc("Aim Right",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						cannon.cannonAimAdjust(-1.0f);
					}
				});

		inputHandler.registerKeyJustPressedFunc("Place Ball",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						ballManager.addStaticBall();
					}
				});

		inputHandler.registerKeyJustPressedFunc("Shoot",
				new InputHandler.KeyCommand() {
					public void runCommand() {
						ballManager.shootBall();
					}
				});
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		/* Update the runtime */
		runTime+=delta;

		/* Handle input keys */
		inputHandler.run();

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(BustaMove.BOUNCE_X_MIN, BustaMove.BOUNCE_Y_MIN,
				BustaMove.BOUNCE_X_MAX - BustaMove.BOUNCE_X_MIN,
				BustaMove.BOUNCE_Y_MAX - BustaMove.BOUNCE_Y_MIN);
		shapeRenderer.end();
		*/

		game.batch.begin();
		game.batch.enableBlending();
		
		/* Draw the background */
		game.batch.draw(AssetLoader.bg, BustaMove.BOUNCE_X_MIN, BustaMove.BOUNCE_Y_MIN, 128, 220);
		
		/* Draw the balls */
		ballManager.draw(game.batch, runTime);
		
		/* Draw the cannon */
		cannon.draw(game.batch);

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {

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
		// img.dispose();
	}

	private void loadLevel() {
		String levelFilePath = "testlevel.txt";
		
		try{
			FileHandle handle = Gdx.files.internal(levelFilePath);
			Scanner s = new Scanner(handle.read());
			int linenr = 0;
			while(s.hasNextLine()) {
				String line = s.nextLine();
				int ypos = BustaMove.BOUNCE_Y_MAX - (2*linenr + 1)*Ball.BALL_RAD;
				for (int i = 0; i < line.length(); i++) {
					int xpos = BustaMove.BOUNCE_X_MIN + (2*i+1)*Ball.BALL_RAD;
					
					//shift odd rows
					if(linenr % 2 != 0)
						xpos += Ball.BALL_RAD;
					
					ballManager.addRandomStaticBall(xpos, ypos);
				}
				linenr++;
			}
			
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
