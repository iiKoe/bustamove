package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AudioManager {
    private static AudioStateMachine audioStateMachine;
    public static Sound shoot, wallhit, ballpop;
    public static Music gameMusic;
    
    /**
     * Load all the audioclips from files
     */
    public static void load() {
        try {
            shoot = Gdx.audio.newSound(Gdx.files.internal("audio/shoot.wav"));
            wallhit = Gdx.audio.newSound(Gdx.files.internal("audio/wallhit.wav"));
            ballpop = Gdx.audio.newSound(Gdx.files.internal("audio/ballpop.wav"));
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/gamemusic.wav"));
            gameMusic.setVolume(0.5f);
            gameMusic.setLooping(true);
            audioStateMachine = new AudioStateMachine();
        } catch (GdxRuntimeException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Mute the music and sounds
     */
    public static void mute() {
        audioStateMachine.setState(new AudioStateMachine.Muted());
    }
    
    /**
     * Unmute the music and sounds
     */
    public static void unmute() {
        audioStateMachine.setState(new AudioStateMachine.Active());
    }
    
    /**
     * Toggle the mute for music and sounds
     */
    public static void toggleMute() {
        audioStateMachine.toggleMute();
    }
    
    /**
     * See if the audiomanager is muted
     * @return the mute status
     */
    public static Boolean isMuted() {
        return audioStateMachine.muted();
    }
    
    /**
     * Start the background music
     */
    public static void startMusic() {
        //System.out.println("Starting music");
        audioStateMachine.playMusic();
    }
    
    /**
     * Stop the background music
     */
    public static void stopMusic() {
        audioStateMachine.playMusic();
    }
    
    /**
     * Play the sound for shooting
     */
    public static void shoot() {
        audioStateMachine.playShoot();
    }
    
    /**
     * Play the sound for when a ball hits the wall
     */
    public static void wallhit() {
        audioStateMachine.playWall();
    }
    
    /**
     * Play the sound for when a ball pops
     */
    public static void ballpop() {
        try {
            audioStateMachine.playPop();
        } catch (NullPointerException e) {
            return;
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
