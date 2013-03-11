package com.icbat.game.tradesong;

import java.util.Date;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.screens.InventoryScreen;
import com.icbat.game.tradesong.screens.LevelScreen;

public class Tradesong extends Game {
	
	public Object inventory = null;
	public LJ log = new LJ("", this.getClass().getSimpleName());
	
	@Override
	public void create() {
		log.setLevel( Application.LOG_DEBUG );
		log.info( "Creating game" );
		// Some initial logging (type and version)
		log.info( new Date().toString() );
		log.info( "App Type" + Gdx.app.getType().toString() );
		log.info( "Device Version" + Gdx.app.getVersion() );
		// setScreen( new LevelScreen("test", this) );
		setScreen( new InventoryScreen(this) );
	}

	@Override
	public void dispose() {
		log.debug( "Disposing game" );
		super.dispose();
	}

	@Override
	public void resize( int width, int height ) {
		log.debug( "Resizing game to " + width + "w by " + height + "h" );
		super.resize(width, height);
	}

	@Override
	public void pause() {
		log.debug( "Pausing game" );
		super.pause();
	}

	@Override
	public void resume() {
		log.debug( "Resuming game" );
		super.resume();
	}
	
	@Override
	public void setScreen( Screen screen ) {
		// Deliberately no debug here. Doesn't 'toString' well, and context is still clear.
		super.setScreen( screen );
	}
}
