package com.group66.game.helpers;

interface AudioState {
    public void toggleMute(AudioStateMachine state);
    public Boolean muted();
    public void playMusic();
    public void playShoot();
    public void playWall();
    public void playPop();
}

public class AudioStateMachine {
    private AudioState state;
    
    public AudioStateMachine() {
        this.setState(new Active());
    }
    
    public void setState(AudioState state) {
        this.state = state;
    }
    
    public AudioState getState() {
        return this.state;
    }
    
    public void toggleMute() {
        this.getState().toggleMute(this);
    }
    
    public Boolean muted() {
        return this.getState().muted();
    }
    
    public void playMusic() {
        this.getState().playMusic();
    }
    
    public void playShoot() {
        this.getState().playShoot();
    }
    
    public void playWall() {
        this.getState().playWall();
    }
    
    public void playPop() {
        this.getState().playPop();
    }
    
    static class Muted implements AudioState {
        public Muted() {
            this.playMusic();
        }
        
        public void toggleMute(AudioStateMachine state) {
            state.setState(new Active());
        }

        public Boolean muted() {
            return true;
        }

        public void playMusic() {
            AudioManager.gameMusic.stop();
        }

        public void playShoot() {
            AudioManager.shoot.stop();
        }

        public void playWall() {
            AudioManager.wallhit.stop();
        }

        public void playPop() {
            AudioManager.ballpop.stop();
        }
    }
    
    static class Active implements AudioState {
        public Active() {
            this.playMusic();
        }
        
        public void toggleMute(AudioStateMachine state) {
            state.setState(new Muted());
            AudioManager.stopMusic();
            AudioManager.gameMusic.stop();
        }

        public Boolean muted() {
            return false;
        }

        public void playMusic() {
            AudioManager.gameMusic.play();
        }

        public void playShoot() {
            AudioManager.shoot.play();
        }

        public void playWall() {
            AudioManager.wallhit.play();
        }

        public void playPop() {
            AudioManager.ballpop.play();
        }
    }
}