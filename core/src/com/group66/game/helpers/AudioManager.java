package com.group66.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.group66.game.BustaMove;
import com.group66.game.logging.MessageType;

public class AudioManager {
    private AudioStateMachine audioStateMachine;
    public Sound shoot, wallhit, ballpop;
    public Music gameMusic;
    
    /**
     * Load all the audioclips from files
     */
    public void load() {
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
    public void mute() {
        audioStateMachine.setState(new AudioStateMachine.Muted());
    }
    
    /**
     * Unmute the music and sounds
     */
    public void unmute() {
        audioStateMachine.setState(new AudioStateMachine.Active());
    }
    
    /**
     * Toggle the mute for music and sounds
     */
    public void toggleMute() {
        audioStateMachine.toggleMute();
    }
    
    /**
     * See if the audiomanager is muted
     * @return the mute status
     */
    public Boolean isMuted() {
        return audioStateMachine.muted();
    }
    
    /**
     * Start the background music
     */
    public void startMusic() {
        BustaMove.getGameInstance().log(MessageType.Info, "Starting music");
        audioStateMachine.playMusic();
    }
    
    /**
     * Stop the background music
     */
    public void stopMusic() {
        audioStateMachine.playMusic();
    }
    
    /**
     * Play the sound for shooting
     */
    public void shoot() {
        audioStateMachine.playShoot();
    }
    
    /**
     * Play the sound for when a ball hits the wall
     */
    public void wallhit() {
        audioStateMachine.playWall();
    }
    
    /**
     * Play the sound for when a ball pops
     */
    public void ballpop() {
        try {
            audioStateMachine.playPop();
        } catch (NullPointerException e) {
            return;
        }
    }
    
    /**
     * Dispose of all audioclips
     */
    public void dispose() {
        shoot.dispose();
        wallhit.dispose();
        ballpop.dispose();
        gameMusic.dispose();
    }
}
