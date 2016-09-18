package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.group66.game.settings.Config;

/**
 * The Class TextDrawer.
 */
public class TextDrawer {
	
	/** The used font. */
	public BitmapFont myFont;
		
	/**
	 * Generates the font and its settings.
	 */
	public TextDrawer() {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Courier.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Config.FONT_SIZE; //font size
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()?:"; 
		//list of printable chars
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
	}
	
	/**
	 * Draw the score.
	 *
	 * @param batch the batch
	 * @param score the score
	 */
	public void drawScore(SpriteBatch batch, int score) {
		myFont.draw(batch, "Score: " + Integer.toString(score), Config.SCORE_X_LOCATION, 
				Config.SCORE_Y_LOCATION);
	}
	
	/**
	 * Draw text, coordinates start in the top left corner.
	 *
	 * @param batch the batch
	 * @param text the text to draw
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void draw(SpriteBatch batch, String text, int x, int y) {
		myFont.draw(batch, text, x, y);
		
	}
}

 