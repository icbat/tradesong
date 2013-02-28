package com.icbat.game.tradesong;


import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.InputAdapter;
import com.icbat.game.LJ;

/**
 * Input adapter specialized for this game.
 * 
 * For Keys: keeps a log (a set) of what's currently down so processing can occur.
 * 
 * TODO needs to be expanded to handle touch screen (when that day comes)
 * */
public class InputHandler extends InputAdapter {
	public Set<Integer> keysDown = new TreeSet<Integer>(); // TreeSet guarantees log(n) add/remove/contains
	
	/** For logging to work, making this impossible */
	public InputHandler() {}

	
	
	
	@Override
	/** Adds the key 'keycode' to the set (if it doesn't yet exist) */
	public boolean keyDown( int keycode ) {
		keysDown.add( keycode );
		LJ.log( keycode + " was pressed" );
		return true;
	}
	
	
	@Override
	/** Removes the key 'keycode' from the set (if it still exists) */
	public boolean keyUp( int keycode ) {
		keysDown.remove(keycode);
		LJ.log( keycode + " was released" );
		LJ.log( "Num of Keys down still: " + keysDown.size() );
		return true;
	}
	
	@Override

	public boolean touchDown( int screenX, int screenY, int pointer, int button ) {
		LJ.log( button + " (touch) was pressed with ptr " + pointer );
		return false;
	}

	
	@Override
	public boolean touchUp( int screenX, int screenY, int pointer, int button ) {
		LJ.log( button + " (touch) was released with ptr " + pointer );
		return false;
		
	}
	
	@Override
	public boolean touchDragged( int screenX, int screenY, int pointer ) {
		// TODO 
		return false;
		
	}
}