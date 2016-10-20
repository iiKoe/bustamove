package com.group66.game.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class AudioManagerTest {
    @Test
    public void loadTest() {
        //Gdx.gl = mock(GL20.class);
        AudioManager.load();
    }
    
    @Test
    public void muteTest() {
        //Gdx.gl = mock(GL20.class);
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
