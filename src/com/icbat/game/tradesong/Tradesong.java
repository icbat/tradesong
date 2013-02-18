package com.icbat.game.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.icbat.game.Lumberjack;

public class Tradesong extends Game {
	public Lumberjack logger = new Lumberjack(this.getClass().getSimpleName(), true, true);
	public int trd = 1;
	
	@Override
	public void create() {
		logger.setSensitivity(Application.LOG_DEBUG);
		logger.log( "Creating game", Lumberjack.DEBUG );
		setScreen( ScreenFactory.getLevelScreen( "test", this ) );
		
		Gdx.input.setInputProcessor(new InputHandler());
	}

	@Override
	public void dispose() {
		logger.log( "Disposing game", Lumberjack.DEBUG );
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize( int width, int height ) {
		logger.log( "Resizing game to " + width + "w by " + height + "h", Lumberjack.DEBUG );
		super.resize(width, height);
	}

	@Override
	public void pause() {
		logger.log( "Pausing game", Lumberjack.DEBUG );
		super.pause();
	}

	@Override
	public void resume() {
		logger.log( "Resuming game", Lumberjack.DEBUG );
		super.resume();
	}
	
	@Override
	public void setScreen( Screen screen ) {
		// Deliberately no debug here. Doesn't 'toString' well, and context is still clear.
		super.setScreen( screen );
	}
}
