package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.group66.game.settings.Config;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.settings.Config;


public class TextDrawer {
	
	public BitmapFont myFont;
		
	// Generates the font and its settings. Need to run once.
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
	
	//function for drawing the player score
	public void drawScore(SpriteBatch batch, int score){
		myFont.draw(batch, "Score: " + Integer.toString(score), Config.SCORE_X_LOCATION, 
				Config.SCORE_Y_LOCATION);
	}
	
	
	//function for drawing any text needed. Starts coordinates from the top left corner
	public void draw(SpriteBatch batch, String text, int x, int y) {
		myFont.draw(batch, text, x, y);
		
	}
	
}

 