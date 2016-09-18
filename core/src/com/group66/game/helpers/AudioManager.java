package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static Boolean mute;
    private static Sound shoot, wallhit, ballpop;
    private static Music gameMusic;
    
    /**
     * Load all the audioclips from files
     */
    public static void load() {
        mute = false;
        shoot = Gdx.audio.newSound(Gdx.files.internal("audio/shoot.wav"));
        wallhit = Gdx.audio.newSound(Gdx.files.internal("audio/wallhit.wav"));
        ballpop = Gdx.audio.newSound(Gdx.files.internal("audio/ballpop.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/gamemusic.wav"));
        gameMusic.setVolume(0.5f);
        gameMusic.setLooping(true);
    }
    
    /**
     * Mute the music and sounds
     */
    public static void mute() {
        mute = true;
        stopMusic();
    }
    
    /**
     * Unmute the music and sounds
     */
    public static void unmute() {
        mute = false;
        startMusic();
    }
    
    /**
     * Start the background music
     */
    public static void startMusic() {
        if (!mute) {
            gameMusic.play();
        }
    }
    
    /**
     * Stop the background music
     */
    public static void stopMusic() {
        gameMusic.stop();
    }
    
    /**
     * Play the sound for shooting
     */
    public static void shoot() {
        if (!mute) {
            shoot.play();
        }
    }
    
    /**
     * Play the sound for when a ball hits the wall
     */
    public static void wallhit() {
        if (!mute) {
            wallhit.play();
        }
    }
    
    /**
     * Play the sound for when a ball pops
     */
    public static void ballpop() {
        if (!mute) {
            ballpop.play();
        }
    }
    
    /**
     * Dispose of all audioclips
     */
    public static void dispose() {
        shoot.dispose();
        wallhit.dispose();
        ballpop.dispose();
        gameMusic.dispose();
    }
}
