package com.group66.game.helpers;

/**
 * The Class AudioStateMachine.
 */
public class AudioStateMachine {
    
    /**
     * The Interface AudioState.
     */
    interface AudioState {
        
        /**
         * Toggle mute.
         *
         * @param state the state
         */
        public void toggleMute(AudioStateMachine state);
        
        /**
         * Muted.
         *
         * @return the boolean
         */
        public Boolean muted();
        
        /**
         * Play music.
         */
        public void playMusic();
        
        //Stop music
        public void stopMusic();
        
        /**
         * Play shoot.
         */
        public void playShoot();
        
        /**
         * Play wall.
         */
        public void playWall();
        
        /**
         * Play pop.
         */
        public void playPop();
    }
    
    /** The state. */
    private AudioState state;
    
    /**
     * Instantiates a new audio state machine.
     */
    public AudioStateMachine() {
        this.setState(new Muted());
    }
    
    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(AudioState state) {
        if (state != null) {
            this.state = state;
        }
    }
    
    /**
     * Gets the state.
     *
     * @return the state
     */
    public AudioState getState() {
        return this.state;
    }
    
    /**
     * Toggle mute.
     */
    public void toggleMute() {
        this.getState().toggleMute(this);
    }
    
    /**
     * Muted.
     *
     * @return the boolean
     */
    public Boolean muted() {
        return this.getState().muted();
    }
    
    /**
     * Play music.
     */
    public void playMusic() {
        this.getState().playMusic();
    }
    
    /**
     * Stop music.
     */
    public void stopMusic() {
        this.getState().stopMusic();
    }
    
    /**
     * Play shoot sound effect.
     */
    public void playShoot() {
        this.getState().playShoot();
    }
    
    /**
     * Play wall sound effect.
     */
    public void playWall() {
        this.getState().playWall();
    }
    
    /**
     * Play pop sound effect.
     */
    public void playPop() {
        this.getState().playPop();
    }
    
    /**
     * The Class Muted.
     */
    static class Muted implements AudioState {
        
        /**
         * Instantiates a new muted.
         */
        public Muted() {
            this.playMusic();
        }
        
        public void toggleMute(AudioStateMachine state) {
            if (state != null) {
                state.setState(new Active());
            }
        }

        public Boolean muted() {
            return true;
        }

        public void playMusic() { }
        
        public void stopMusic() {
            AudioManager.getGameMusic().stop();
        }

        public void playShoot() { }

        public void playWall() { }

        public void playPop() { }
    }
    
    /**
     * The Class Active.
     */
    static class Active implements AudioState {
        
        /**
         * Instantiates a new active.
         */
        public Active() {
            this.playMusic();
        }
        
        public void toggleMute(AudioStateMachine state) {
            if (state != null) {
                state.setState(new Muted());
                AudioManager.getGameMusic().stop();
            }
        }

        public Boolean muted() {
            return false;
        }

        public void playMusic() {
            AudioManager.getGameMusic().play();
        }

        public void stopMusic() {
            AudioManager.getGameMusic().stop();
        }

        public void playShoot() {
            AudioManager.getShootSound().play();
        }

        public void playWall() {
            AudioManager.getWallhitSound().play();
        }

        public void playPop() {
            AudioManager.getBallpopSound().play();
        }
    }
}