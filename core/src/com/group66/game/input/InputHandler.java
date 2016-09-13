package com.group66.game.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;



public class InputHandler {
	
	public interface KeyCommand {
		void runCommand();
	}
	
	// Keymap
	Map<String, Collection<Integer>> keyMap = new HashMap<String, Collection<Integer>>();
	public void registerKeyMap(String keyname, int key) {
		// Init if key is not yet registered
		if (keyMap.get(keyname) == null)
			keyMap.put(keyname, new ArrayList<Integer>());

		keyMap.get(keyname).add(key);
	}
	

	// Register a function to be executed on a key press
	Map<String, Collection<KeyCommand>> multiFunctionMapKP = new HashMap<String, Collection<KeyCommand>>();
	public void registerKeyPressedFunc(String key, KeyCommand com) {

		// Init if key is not yet registered
		if (multiFunctionMapKP.get(key) == null)
			multiFunctionMapKP.put(key, new ArrayList<KeyCommand>());

		multiFunctionMapKP.get(key).add(com);
	}
	
	Map<String, Collection<KeyCommand>> multiFunctionMapKJP = new HashMap<String, Collection<KeyCommand>>();
	public void registerKeyJustPressedFunc(String key, KeyCommand com) {
		// Init if key is not yet registered
		if (multiFunctionMapKJP.get(key) == null)
			multiFunctionMapKJP.put(key, new ArrayList<KeyCommand>());

		multiFunctionMapKJP.get(key).add(com);
	}
	
	public void run () {
		// KeyPressed
		for (Map.Entry<String, Collection<KeyCommand>> entry :multiFunctionMapKP.entrySet()) {
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
		for (Map.Entry<String, Collection<KeyCommand>> entry :multiFunctionMapKJP.entrySet()) {
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
