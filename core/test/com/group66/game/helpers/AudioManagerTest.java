package com.group66.game.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AudioManagerTest {
    @Test
    public void loadTest() {
        AudioManager.load();
    }
    
    @Test
    public void muteTest() {
        AudioManager.load();
        assertTrue(AudioManager.isMuted());
        
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
    
    @Test
    public void playTest() {
        AudioManager.load();
        
        AudioManager.shoot();
        AudioManager.wallhit();
        AudioManager.startMusic();
        AudioManager.ballpop();
        
        AudioManager.toggleMute();
        
        AudioManager.shoot();
        AudioManager.wallhit();
        AudioManager.startMusic();
        AudioManager.ballpop();
    }
}
