package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AudioManager {
    private static AudioStateMachine audioStateMachine;
    private static Sound shoot, wallhit, ballpop;
    private static Music gameMusic;
    
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
     * Get the game music
     * @return the game music
     */
    public static Music getGameMusic() {
        return gameMusic;
    }
    
    /**
     * Get the shoot sound effect
     * @return the shoot sound
     */
    public static Sound getShootSound() {
        return shoot;
    }
    
    /**
     * Get the wall hit sound effect
     * @return the wall hit sound
     */
    public static Sound getWallhitSound() {
        return wallhit;
    }
    
    /**
     * Get the ball pop sound effect
     * @return the ball pop sound
     */
    public static Sound getBallpopSound() {
        return ballpop;
    }
    
    /**
     * Start the background music
     */
    public static void startMusic() {
        System.out.println("Starting music");
        audioStateMachine.playMusic();
    }
    
    /**
     * Stop the background music
     */
    public static void stopMusic() {
        audioStateMachine.stopMusic();
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
