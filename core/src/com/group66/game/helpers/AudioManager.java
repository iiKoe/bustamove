package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static Boolean mute;
    private static Sound shoot, wallhit, ballpop;
    private static Music gameMusic;
    
    public static void load() {
        mute = false;
        shoot = Gdx.audio.newSound(Gdx.files.internal("audio/shoot.wav"));
        wallhit = Gdx.audio.newSound(Gdx.files.internal("audio/wallhit.wav"));
        ballpop = Gdx.audio.newSound(Gdx.files.internal("audio/ballpop.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/gamemusic.wav"));
        gameMusic.setVolume(0.5f);
        gameMusic.setLooping(true);
    }
    
    public static void mute() {
        mute = true;
        stopMusic();
    }
    
    public static void unmute() {
        mute = false;
        startMusic();
    }
    
    public static void startMusic() {
        if(!mute) {
            gameMusic.play();
        }
    }
    
    public static void stopMusic() {
        gameMusic.stop();
    }
    
    public static void shoot() {
        shoot.play();
    }
    
    public static void wallhit() {
        wallhit.play();
    }
    
    public static void ballpop() {
        ballpop.play();
    }
    
    public static void dispose() {
        shoot.dispose();
        wallhit.dispose();
        ballpop.dispose();
        gameMusic.dispose();
    }
}
