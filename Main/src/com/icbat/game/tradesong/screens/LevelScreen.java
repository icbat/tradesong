package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;

import java.util.LinkedList;
import java.util.Random;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName = "";
	private TiledMap map = null;
	private TiledMapRenderer renderer = null;

	private OrthographicCamera camera = null;
    private Actor backgroundActor = null;

    // Used with items
    private Texture itemsTexture;
    private LinkedList<Item> itemsOnMap = new LinkedList<Item>();
    /** The string label in the map. If it changes there, change it here! */
    private String item_key = "spawnable_items";
    private String[] spawnableItems;
    private Timer timer;

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

		this.map = game.assets.get(mapName);
		this.renderer = new OrthogonalTiledMapRenderer(this.map, 1f / 64f);

        startTime = System.currentTimeMillis();
        game.assets.load("sprites/items.png", Texture.class);
        game.assets.finishLoading();
        endTime = System.currentTimeMillis();
        log("Loaded sprites in " + (endTime - startTime) + " milliseconds");

//        itemsTexture = new Texture();

        // Set up list of items to spawn
        String items = (String)this.map.getProperties().get(item_key);
        this.spawnableItems = items.split(",");

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            public void run() {
                Item toSpawn = spawnItem();
                Random r = new Random();

                log(toSpawn.getItemName());
            }
        }
                ,5 , 6);
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
        timer.clear(); // Cancels all tasks
		super.dispose(); // Likely needs to be called last
		this.map.dispose();
	}

    /** Spawns a random item that is possible on this map */
    private Item spawnItem() {
        Random r = new Random();
        int i = r.nextInt(spawnableItems.length);
        String name, descr;
        TextureRegion region = null;

        name = spawnableItems[i];
        // ATTN: hard coding this for now. Extract out later. This is good enough for alpha
        switch(i) {
            case 1:
                descr = "Some rock with little glinting bits.";

                break;
            case 2:
                descr = "Some rock with little glinting bits.";

                break;
            case 3:
                descr = "Some rock with little glinting bits.";

                break;
            default:
                descr = "What a strange object!";
//                region = new TextureRegion().setRegion();
        }

        return new Item(name, descr, region);
    }

    @Override
    public void pause() {
        super.pause();
        timer.stop();
    }

    @Override
    public void resume() {
        super.resume();
        timer.start();
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

            camera.unproject(curr.set(x, y, 0));

            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                camera.unproject(delta.set(last.x, last.y, 0));
                delta.sub(curr);
                camera.position.add(delta.x, delta.y * -1, 0);
            }

            last.set(x, y, 0);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            last.set(-1, -1, -1);
        }
    }

}