package com.group66.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group66.game.BustaMove;
import com.group66.game.settings.Config;

/**
 * The Class DesktopLauncher.
 */
public class DesktopLauncher {
    
    /**
     * The main method.
     *
     * @param arg the arguments
     */
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = 60; // FPS on foreground
        config.backgroundFPS = 10; // FPS on background
        config.width = Config.WIDTH;
        config.height = Config.HEIGHT;
        //config.resizable = false;
        new LwjglApplication(new BustaMove(), config);
    }
}
