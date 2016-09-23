package com.group66.game.logging;

public enum MessageType {
    Default(5), Error(4), Warning(3), Info(2), Debug(1);
    
    int level = 0;

    MessageType(int lvl) {
        this.level = lvl;
    }
}
