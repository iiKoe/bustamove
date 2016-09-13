package com.group66.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group66.game.BustaMove;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60; // FPS on foreground
		config.backgroundFPS = 10; // FPS on background
		config.width = BustaMove.WIDTH;
		config.height = BustaMove.HEIGHT;
		//config.resizable = false;
		new LwjglApplication(new BustaMove(), config);
	}
}
