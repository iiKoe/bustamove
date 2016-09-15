package com.group66.game.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

/**
 * A class to manage and handle the user input via the keyboard.
 */
public class InputHandler {
	
	/** The key map. */
	private Map<String, Collection<Integer>> keyMap = 
			new HashMap<String, Collection<Integer>>();
	
	/** The multi function map for KeyPressed. */
	private Map<String, Collection<KeyCommand>> multiFunctionMapKP = 
			new HashMap<String, Collection<KeyCommand>>();
	
	/** The multi function map KeyJustPressed. */
	private Map<String, Collection<KeyCommand>> multiFunctionMapKJP = 
			new HashMap<String, Collection<KeyCommand>>();
	
	/**
	 * The Interface KeyCommand to store the function to be called when a Key is pressed.
	 */
	public interface KeyCommand {
		
		/**
		 * The function in which the commands to be run when a key is press are placed.
		 */
		void runCommand();
	}

	/**
	 * Register key map.
	 *
	 * @param keyname the String key name
	 * @param key the keybord key to be mapped to the key name
	 */
	public void registerKeyMap(String keyname, int key) {
		// Init if key is not yet registered
		if (keyMap.get(keyname) == null) {
			keyMap.put(keyname, new ArrayList<Integer>());
		}

		keyMap.get(keyname).add(key);
	}
	
	/**
	 * Register a function for a key pressed given the key name.
	 *
	 * @param keyname the key name String registered with registerKeyMap()
	 * @param com the command to be run when the Key is Pressed
	 */
	public void registerKeyPressedFunc(String keyname, KeyCommand com) {

		// Init if key is not yet registered
		if (multiFunctionMapKP.get(keyname) == null) {
			multiFunctionMapKP.put(keyname, new ArrayList<KeyCommand>());
		}

		multiFunctionMapKP.get(keyname).add(com);
	}
	
	/**
	 * Register a function for a key JUST pressed given the key name.
	 *
	 * @param key the key name String registered with registerKeyMap()
	 * @param com the command to be run when the Key is JUST Pressed
	 */
	public void registerKeyJustPressedFunc(String key, KeyCommand com) {
		// Init if key is not yet registered
		if (multiFunctionMapKJP.get(key) == null) {
			multiFunctionMapKJP.put(key, new ArrayList<KeyCommand>());
		}

		multiFunctionMapKJP.get(key).add(com);
	}
	
	/**
	 * Check for the key presses and execute the commands registered to a key if it is pressed.
	 */
	public void run() {
		// KeyPressed
		for (Map.Entry<String, Collection<KeyCommand>> entry 
				:multiFunctionMapKP.entrySet()) {
		    String key = entry.getKey();
		    
			Collection<Integer> keyInts = keyMap.get(key);
		    for (int keyInt : keyInts) {
			    
			    if (Gdx.input.isKeyPressed(keyInt)) {
			    	Collection<KeyCommand> functions = entry.getValue();
			    	for (KeyCommand command : functions) {
			    		command.runCommand();
			    	}
			    }		
		    }
		}
		
		// KeyJustPressed
		for (Map.Entry<String, Collection<KeyCommand>> entry
				:multiFunctionMapKJP.entrySet()) {
		    String key = entry.getKey();
		    
			Collection<Integer> keyInts = keyMap.get(key);
		    for (int keyInt : keyInts) {
			    
			    if (Gdx.input.isKeyJustPressed(keyInt)) {
			    	Collection<KeyCommand> functions = entry.getValue();
			    	for (KeyCommand command : functions) {
			    		command.runCommand();
			    	}
			    }		
		    }
		}
	}
}
