package com.group66.game.screens;

import java.util.TreeSet;

import com.badlogic.gdx.Screen;
import com.group66.game.helpers.HighScoreItem;

public class HighScoreScreen implements Screen {
    private TreeSet<HighScoreItem> highscores;
    
    public HighScoreScreen() {
        highscores = new TreeSet<HighScoreItem>();
        loadData();
    }

    private void loadData() {
        //load data from file
        
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        
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
    }
}
