package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {
	
//	BitmapFont font;
//	TiledMap map;
//	TileAtlas atlas;
//	TileMapRenderer renderer;
	
	// TODO abstract somehow to not be code-static
	FileHandle tilesetsDir = Gdx.files.internal("tilesets/");
	
	

	public LevelScreen(String level, Tradesong game) {
		super(game);
		
		game.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		
		LJ.log("Loading level", level, LJ.LOG);
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		game.assets.load("maps/" + level + ".tmx", TiledMap.class);
		endTime = System.currentTimeMillis();
		LJ.log("Loaded map in " + (endTime - startTime) + " milliseconds", LJ.DEBUG);
		
		
//		LJ.log("Trying to load level", level, LJ.LOG);
//		FileHandle mapFile = Gdx.files.internal("maps/" + level + ".tmx");
//		if (!mapFile.exists())
//			LJ.log("Level not found!", level, LJ.ERROR);
//		
//		log( "Opening level:  " + mapFile, LJ.DEBUG );
//		
//		// Load up resources
//		this.font = new BitmapFont();
//		font.setColor(Color.RED);
//		
//		// Load map and log the time
//		// TODO refactor this timing to be more readable
//		long startTime, endTime;
//		startTime = System.currentTimeMillis();
//		this.map = TiledLoader.createMap(mapFile);		
//		endTime = System.currentTimeMillis();
//		log( "Loaded map in " + (endTime - startTime) + " milliseconds", LJ.DEBUG );
//		
//		// Atlas map
//		startTime = System.currentTimeMillis();
//		this.atlas = new SimpleTileAtlas(this.map, tilesetsDir);
//		endTime = System.currentTimeMillis();
//		log( "Atlas'd map in " + (endTime - startTime) + " milliseconds", LJ.DEBUG );
//		
//		
//		// Set up renderer
//		int blockHeight = 10;
//		int blockWidth = 10;
//		
//		startTime = System.currentTimeMillis();
//		this.renderer = new TileMapRenderer(map, atlas, blockWidth, blockHeight, 32, 32); 
//		endTime = System.currentTimeMillis();
//		log( "Renderer loaded in " + (endTime - startTime) + " milliseconds", LJ.DEBUG );
//		
//		//TODO Continue loading/making this show
		
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
		super.dispose(); // Likely needs to be called last
		
	}

}
