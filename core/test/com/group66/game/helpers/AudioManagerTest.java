package com.group66.game.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AudioManagerTest {
    @Test
    public void loadTest() {
        AudioManager.load();
    }
    
    @Test
    public void muteTest() {
        AudioManager.load();
        assertFalse(AudioManager.isMuted());
        
        AudioManager.unmute();
        assertFalse(AudioManager.isMuted());
        AudioManager.mute();
        assertTrue(AudioManager.isMuted());
        AudioManager.unmute();
        assertFalse(AudioManager.isMuted());
        AudioManager.toggleMute();
        assertTrue(AudioManager.isMuted());
        AudioManager.toggleMute();
        assertFalse(AudioManager.isMuted());
        AudioManager.toggleMute();
        assertTrue(AudioManager.isMuted());
        
        AudioManager.dispose();
    }
}
