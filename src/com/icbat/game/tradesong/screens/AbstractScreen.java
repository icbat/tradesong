package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Screen;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.Tradesong;

public abstract class AbstractScreen implements Screen {

	protected final Tradesong gameInstance;
	
	public AbstractScreen( Tradesong instance ) {
		this.gameInstance = instance;
		log( "Creating Screen", LJ.LOG );
	}
	
	@Override
	public void render( float delta ) {

	}

	@Override
	public void resize(int width, int height) {
		log( "Resizing screen to " + width + "w by " + height + "h", LJ.DEBUG );
//		super.resize(width, height);

	}

	@Override
	public void show() {
		log( "Showing Screen: ", LJ.DEBUG );

	}

	@Override
	public void hide() {
		log( "Hiding Screen: ", LJ.DEBUG );

	}

	@Override
	public void pause() {
		log( "Pausing Screen: ", LJ.DEBUG );

	}

	@Override
	public void resume() {
		log( "Resuming Screen: ", LJ.DEBUG );

	}

	@Override
	public void dispose() {
		log( "Disposing of Screen: ", LJ.DEBUG );

	}
	
	protected void log( String message, int level ) {
		LJ.log( message, getClass().getSimpleName(), level );
	}
}
