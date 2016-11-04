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
        AudioManager audiomanager = new AudioManager();
        audiomanager.load();
    }
    
    @Test
    public void muteTest() {
        AudioManager audiomanager = new AudioManager();
        audiomanager.load();
        assertTrue(audiomanager.isMuted());
        
        audiomanager.unmute();
        assertFalse(audiomanager.isMuted());
        audiomanager.mute();
        assertTrue(audiomanager.isMuted());
        audiomanager.unmute();
        assertFalse(audiomanager.isMuted());
        audiomanager.toggleMute();
        assertTrue(audiomanager.isMuted());
        audiomanager.toggleMute();
        assertFalse(audiomanager.isMuted());
        audiomanager.toggleMute();
        assertTrue(audiomanager.isMuted());
        
        audiomanager.dispose();
    }
    
    @Test
    public void playTest() {
        AudioManager audiomanager = new AudioManager();
        audiomanager.load();
        
        audiomanager.shoot();
        audiomanager.wallhit();
        audiomanager.startMusic();
        audiomanager.ballpop();
        
        audiomanager.toggleMute();
        
        audiomanager.shoot();
        audiomanager.wallhit();
        audiomanager.startMusic();
        audiomanager.ballpop();
    }
}
