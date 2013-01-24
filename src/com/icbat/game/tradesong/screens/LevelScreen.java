package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.files.FileHandle;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {

	public LevelScreen(FileHandle level, Tradesong instance) {
		super(instance);
		log( "Opening level:  " + level );
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	@Override
	public void show() {
		super.show();
		
	}

	@Override
	public void hide() {
		super.hide();
		
	}

	@Override
	public void pause() {
		super.pause();
		
	}

	@Override
	public void resume() {
		super.resume();
		
	}

	@Override
	public void dispose() {
		// TODO dispose of level-stuff
		super.dispose(); // Likely needs to be called last
		
	}

}
