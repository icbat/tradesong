package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.Tradesong;

public class LevelScreen extends AbstractScreen {
	
	public String mapName = "";
	private TiledMap map = null;
	private TiledMapRenderer renderer = null;
	
	private OrthographicCamera camera = null;
	private InputAdapter cameraController;

	public LevelScreen(String level, Tradesong game) {
		super(game);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, (w / h) * 10, 10);
		camera.zoom = 2;
		camera.update();

		cameraController = new InputAdapter();
		Gdx.input.setInputProcessor(cameraController);
		
		// Map loading Starts
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
		// Map loading ends
		
		
		this.map = game.assets.get(mapName);
		this.renderer = new OrthogonalTiledMapRenderer(this.map, 1f / 64f);
		
		
	}
	
	@Override
	public void render(float delta) {
		camera = new OrthographicCamera();
		
		super.render(delta);
		Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
	}

	@Override
	public void dispose() {
		// TODO dispose ALL the things
		super.dispose(); // Likely needs to be called last
		
	}

}
