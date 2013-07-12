package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;

import java.util.LinkedList;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName = "";
	private TiledMap map = null;
	private TiledMapRenderer renderer = null;
	private OrthographicCamera camera = null;

    private Actor backgroundActor = null;

//    private LinkedList<Item> itemsOnMap = new LinkedList<Item>();

	public LevelScreen(String level, Tradesong game) {
		super(game);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

        // Setup a camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, (w / h) * 10, 10);
		camera.zoom = 1;
		camera.update();


        backgroundActor = new Actor();
        backgroundActor.setTouchable(Touchable.enabled);
        backgroundActor.setVisible(true);
        backgroundActor.addListener(new OrthoCamController(camera));


        stage.addActor(backgroundActor);


		
		// Map loading Starts
		game.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		
		// "Internal" relative address. What the asset loader wants.
		this.mapName = "maps/" + level + ".tmx";
		
		log("Loading level: \"" + level + "\"");
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		game.assets.load(mapName, TiledMap.class);
		game.assets.finishLoading();
		endTime = System.currentTimeMillis();
		log("Loaded map in " + (endTime - startTime) + " milliseconds");
		// Map loading ends
		
		
		this.map = game.assets.get(mapName);
		this.renderer = new OrthogonalTiledMapRenderer(this.map, 1f / 64f);
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backgroundActor.setBounds(0,0, width, height);
    }


	@Override
	public void dispose() {
		// TODO dispose ALL the things
		super.dispose(); // Likely needs to be called last
		this.map.dispose();
	}

    /**
     * Input handling for moving camera on maps. Handles:
     *  - touch-dragging
     * */
    class OrthoCamController extends ClickListener {
        final OrthographicCamera camera;
        final Vector3 curr = new Vector3();
        final Vector3 last = new Vector3(-1, -1, -1);
        final Vector3 delta = new Vector3();

        public OrthoCamController (OrthographicCamera camera) {
            this.camera = camera;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);
            log("Dragging");

            camera.unproject(curr.set(x, y, 0));

            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                camera.unproject(delta.set(last.x, last.y, 0));
                delta.sub(curr);
                camera.position.add(delta.x, delta.y, 0);
            }

            last.set(x, y, 0);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            log("Touch up!");
            last.set(-1, -1, -1);
        }
    }

}



