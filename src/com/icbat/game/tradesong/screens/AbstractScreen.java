package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Screen;
import com.icbat.game.tradesong.Tradesong;

public abstract class AbstractScreen implements Screen {

	protected final Tradesong myGameInstance;
	
	public AbstractScreen( Tradesong instance ) {
		debug( "creating");
		this.myGameInstance = instance;
	}
	
	@Override
	public void render( float delta ) {

	}

	@Override
	public void resize(int width, int height) {
		debug( "resizing to " + width + "w by " + height + "h" );
//		super.resize(width, height);

	}

	@Override
	public void show() {
		debug( "showing" );

	}

	@Override
	public void hide() {
		debug( "hiding" );

	}

	@Override
	public void pause() {
		debug( "pausing" );

	}

	@Override
	public void resume() {
		debug( "resuming" );

	}

	@Override
	public void dispose() {
		debug( "disposing" );

	}
	
	private void debug( String message ) {
		Tradesong.debug( getClass().getSimpleName() + ":  " + message);
	}
	
	public void log(String message) {
		Tradesong.debug( getClass().getSimpleName() + ":  " + message );
	}

}
