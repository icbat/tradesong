package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.tiled.SimpleTileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.icbat.game.Lumberjack;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {
	
	BitmapFont font;
	TiledMap map;
	TileAtlas atlas;
	TileMapRenderer renderer;
	
	// TODO abstract somehow to not be code-static
	FileHandle tilesetsDir = Gdx.files.internal("tilesets/");
	
	

	public LevelScreen(FileHandle mapFile, Tradesong instance) {
		super(instance);
		log( "Opening level:  " + mapFile, Lumberjack.DEBUG );
		
		// Load up resources
		this.font = new BitmapFont();
		font.setColor(Color.RED);
		
		// Load map and log the time
		// TODO refactor this timing to be more readable
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		this.map = TiledLoader.createMap(mapFile);		
		endTime = System.currentTimeMillis();
		log( "Loaded map in " + (endTime - startTime) + " milliseconds", Lumberjack.DEBUG );
		
		// Atlas map
		startTime = System.currentTimeMillis();
		this.atlas = new SimpleTileAtlas(this.map, tilesetsDir);
		endTime = System.currentTimeMillis();
		log( "Atlas'd map in " + (endTime - startTime) + " milliseconds", Lumberjack.DEBUG );
		
		
		// Set up renderer
		int blockHeight = 10;
		int blockWidth = 10;
		
		startTime = System.currentTimeMillis();
		this.renderer = new TileMapRenderer(map, atlas, blockWidth, blockHeight, 32, 32); 
		endTime = System.currentTimeMillis();
		log( "Renderer loaded in " + (endTime - startTime) + " milliseconds", Lumberjack.DEBUG );
		
		//TODO Continue loading/making this show
		
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
