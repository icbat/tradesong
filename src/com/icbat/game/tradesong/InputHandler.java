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
	
	Tradesong game;
	
	/** For logging to work, making this impossible */
	@SuppressWarnings("unused")
	private InputHandler() {}
	
	public InputHandler(Tradesong instance) {
		this.game = instance;
	}
	
	
	
	@Override
	/** Adds the key 'keycode' to the set (if it doesn't yet exist) */
	public boolean keyDown( int keycode ) {
		keysDown.add( keycode );
		game.logger.log( keycode + " was pressed" );
		return true;
	}
	
	
	@Override
	/** Removes the key 'keycode' from the set (if it still exists) */
	public boolean keyUp( int keycode ) {
		keysDown.remove(keycode);
		game.logger.log( keycode + " was released" );
		game.logger.log( "Num of Keys down still: " + keysDown.size() );
		return true;
	}
	
	@Override

	public boolean touchDown( int screenX, int screenY, int pointer, int button ) {
		game.logger.log( button + " (touch) was pressed with ptr " + pointer );
		return false;
	}

	
	@Override
	public boolean touchUp( int screenX, int screenY, int pointer, int button ) {
		game.logger.log( button + " (touch) was released with ptr " + pointer );
		return false;
		
	}
	
	@Override
	public boolean touchDragged( int screenX, int screenY, int pointer ) {
		// TODO 
		return false;
		
	}
}
