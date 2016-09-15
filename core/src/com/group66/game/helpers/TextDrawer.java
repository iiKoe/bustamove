package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TextDrawer {
	
	BitmapFont myFont;
	private int score;
		
	// Generates the font and its settings. Need to run once.
	public TextDrawer(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Courier New.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 12; //font size
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>? "; //list of printable chars
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
	}
	
	//function for drawing the player score
	public void draw(SpriteBatch batch, int score){
		myFont.draw(batch, "Score:", 0, 30);
	}
	
	//function for drawing any text needed. Starts coordinates from the top left corner
	public void draw(SpriteBatch batch, String text, int x, int y){
		myFont.draw(batch, text, x, y);
		
	}
	
}

 