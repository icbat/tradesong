package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Screen;
import com.icbat.game.tradesong.Tradesong;

public abstract class AbstractScreen implements Screen {

	Tradesong myGameInstance;
	public AbstractScreen( Tradesong instance ) {
		this.myGameInstance = instance;
	}
	
	@Override
	public void render( float delta ) {

	}

	@Override
	public void resize(int width, int height) {
		log( "resizing to " + width + "w by " + height + "h" );

	}

	@Override
	public void show() {
		log( "showing" );

	}

	@Override
	public void hide() {
		log( "hiding" );

	}

	@Override
	public void pause() {
		log( "pausing" );

	}

	@Override
	public void resume() {
		log( "resuming" );

	}

	@Override
	public void dispose() {
		log( "disposing" );

	}
	
	private void log( String message ) {
		Tradesong.log( getClass().getSimpleName() + ":  Message" );
	}

}
