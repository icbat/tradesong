package com.icbat.game.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class Tradesong extends Game {
	
	@Override
	public void create() {
		// Comment out -> No debug messages show
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		debug( "Creating game" );
		setScreen( ScreenFactory.getLevelScreen( "test", this ) );
	}

	@Override
	public void dispose() {
		debug( "Disposing game" );
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize( int width, int height ) {
		debug( "Resizing game" );
		super.resize(width, height);
	}

	@Override
	public void pause() {
		debug( "Pausing game" );
		super.pause();
	}

	@Override
	public void resume() {
		debug( "Resuming game" );
		super.resume();
	}
	
	@Override
	public void setScreen( Screen screen ) {
		// Deliberately no debug here. Doesn't 'toString' well, and context is still clear.
		super.setScreen( screen );
	}
	
	public static final void debug( String message ) {
		Gdx.app.debug( "", message );
	}
	
	public static final void log( String message ) {
		Gdx.app.log( "#", message );
	}
	
	public static final void error( String message ) {
		Gdx.app.error( "!!", message )
	}
}
