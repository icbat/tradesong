package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {
	
	public String mapName = "";
	private TiledMap map = null;
	private TiledMapRenderer renderer = null;

	public LevelScreen(String level, Tradesong game) {
		super(game);
		
		game.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		
		// "Internal" relative address. What the asset loader wants.
		this.mapName = "maps/" + level + ".tmx";
		
		LJ.log("Loading level", level, LJ.LOG);
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		game.assets.load(mapName, TiledMap.class);
		game.assets.finishLoading();
		endTime = System.currentTimeMillis();
		LJ.log("Loaded map in " + (endTime - startTime) + " milliseconds", LJ.DEBUG);
		
		this.map = game.assets.get(mapName);
		
		
		
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
