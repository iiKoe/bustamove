package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;

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
     * Start the background music
     */
    public static void startMusic() {
        BustaMove.getGameInstance().log(MessageType.Info, "Starting music");
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
    
    /**
     * Returns shooting sound
     */
    public static Sound getShootSound() {
        return shoot;
    }
    
    /**
     * Returns wall hit sound
     */
    public static Sound getWallhitSound() {
        return wallhit;
    }
    
    /**
     * Returns ball popping sound
     */
    public static Sound getBallPopSound() {
        return ballpop;
    }
    
    /**
     * Returns the game music
     */
    public static Music getGameMusic() {
        return gameMusic;
    }
}
