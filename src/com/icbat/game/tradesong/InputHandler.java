package com.icbat.game.tradesong;


import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.InputAdapter;

/**
 * Input adapter specialized for this game.
 * 
 * For Keys: keeps a log (a set) of what's currently down so processing can occur.
 * 
 * TODO needs to be expanded to handle touch screen (when that day comes)
 * */
public class InputHandler extends InputAdapter {
	public Set<Integer> keysDown = new TreeSet<Integer>(); // TreeSet guarantees log(n) add/remove/contains
	
	@Override
	/** Adds the key 'keycode' to the set (if it doesn't yet exist) */
	public boolean keyDown(int keycode) {
		keysDown.add(keycode);
		Tradesong.log( new Integer(keycode).toString() );
		return true;
	}
	
	
	@Override
	/** Removes the key 'keycode' from the set (if it still exists) */
	public boolean keyUp(int keycode) {
		keysDown.remove(keycode);
		Tradesong.log( new Integer(keycode).toString());
		Tradesong.log( "Num of Keys down still: " + keysDown.size());
		return true;
	}
}
