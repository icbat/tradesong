package com.icbat.game.tradesong;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Tradesong implements ApplicationListener {
	
	public static final String LOG = Tradesong.class.getSimpleName();
	
	@Override
	public void create() {		
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public static final void log(String message) {
		Gdx.app.log( Tradesong.LOG, message );
	}
}
