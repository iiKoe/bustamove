package com.group66.game.helpers;

import com.group66.game.BustaMove;

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
     * Play shoot.
     */
    public void playShoot() {
        this.getState().playShoot();
    }

    /**
     * Play wall.
     */
    public void playWall() {
        this.getState().playWall();
    }

    /**
     * Play pop.
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

        /* (non-Javadoc)
         */
        public void toggleMute(AudioStateMachine state) {
            if (state != null) {
                state.setState(new Active());
            }
        }

        /* (non-Javadoc)
         */
        public Boolean muted() {
            return true;
        }

        /* (non-Javadoc)
         */
        public void playMusic() {
            try {
                BustaMove.getGameInstance().getAudioManager().gameMusic.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playShoot() {
            try {
                BustaMove.getGameInstance().getAudioManager().shoot.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playWall() {
            try {
                BustaMove.getGameInstance().getAudioManager().wallhit.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playPop() {
            try {
                BustaMove.getGameInstance().getAudioManager().ballpop.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

        /* (non-Javadoc)
         */
        public void toggleMute(AudioStateMachine state) {
            if (state != null) {
                state.setState(new Muted());
                try {
                    BustaMove.getGameInstance().getAudioManager().stopMusic();
                    BustaMove.getGameInstance().getAudioManager().gameMusic.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* (non-Javadoc)
         */
        public Boolean muted() {
            return false;
        }

        /* (non-Javadoc)
         */
        public void playMusic() {
            try {
                BustaMove.getGameInstance().getAudioManager().gameMusic.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playShoot() {
            try {
                BustaMove.getGameInstance().getAudioManager().shoot.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playWall() {
            try {
                BustaMove.getGameInstance().getAudioManager().wallhit.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* (non-Javadoc)
         */
        public void playPop() {
            try {
                BustaMove.getGameInstance().getAudioManager().ballpop.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}