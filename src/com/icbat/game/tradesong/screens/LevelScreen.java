package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {
	
	BitmapFont font;

	public LevelScreen(FileHandle level, Tradesong instance) {
		super(instance);
		log( "Opening level:  " + level );
		
		// TODO actually load
		// Load up resources
		this.font = new BitmapFont();
		font.setColor(Color.RED);
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
		// TODO dispose ALL the things
		font.dispose();
		super.dispose(); // Likely needs to be called last
		
	}

}
