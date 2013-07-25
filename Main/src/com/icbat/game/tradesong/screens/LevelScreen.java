package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.LevelItemFactory;
import com.icbat.game.tradesong.Tradesong;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName = "";


    int initialItemCount = 4;
    int itemCount;
    int maxSpawnedPerMap = 10; // TODO pull this out of map properties

    private TiledMap map = null;
	private TiledMapRenderer renderer = null;

	private OrthographicCamera bgCamera = new OrthographicCamera();
    private OrthographicCamera stageCamera = new OrthographicCamera();
    private Actor backgroundActor = new Actor();

    private Timer timer = new Timer();
    private LevelItemFactory itemFactory;


	public LevelScreen(String level, Tradesong game) {
		super(game);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

        // Setup a bgCamera
		bgCamera.setToOrtho(false, (w / h) * 10, 10);
		bgCamera.zoom = 1;
		bgCamera.update();

        // Set the stage camera1 to match
        stageCamera.setToOrtho(false, (w/h)*10, 10);
        stageCamera.zoom = 1;
        stageCamera.update();

        // TODO take this and make it work... problems: lag when spawning, makes map TINY. Learn about cameras
        stage.setCamera(stageCamera);
        // Actor for dragging map around. Covers all the ground but doesn't have an image
        backgroundActor.setTouchable(Touchable.enabled);
        backgroundActor.setVisible(true);
        backgroundActor.addListener(new DualCamController(bgCamera, stageCamera));
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


        this.itemFactory =  new LevelItemFactory(this);
        // Initial item spawns
        for (int i = 0; i < initialItemCount; ++i) {
            stage.addActor(itemFactory.makeItem());
            ++itemCount;
        }

        // Set up timer to spawn more items
        timer.scheduleTask(new Timer.Task() {
            public void run() {
                if(itemCount < maxSpawnedPerMap) {
                    stage.addActor(itemFactory.makeItem());
                    ++itemCount;

                    // FOR DEBUGGING PLEASE REMOVE TODO
                    int size = gameInstance.gameState.getInventory().size();
                    log(""+(Integer)size);
                    if (size > 0)
                        log (gameInstance.gameState.getInventory().itemAt(0).getItemName());

                }

            }
        }
                ,5 , 6);


    }
	
	@Override
	public void render(float delta) {
		super.render(delta);
		bgCamera.update();
		renderer.setView(bgCamera);
		renderer.render();
        // Stage.act(d) is handled in super. So is draw, but Stage's needs to happen last, after the bgCamera
		stage.draw();
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

    public TiledMap getMap() {
        return map;
    }

    public void removeItemCount() {
        removeItemCount(1);
    }

    public void removeItemCount(int i) {
        itemCount -= i;
    }


    /**
     * Input handling for moving bgCamera on maps. Handles:
     *  - touch-dragging
     * */
    class DualCamController extends ClickListener {
        final OrthographicCamera camera1;
        final OrthographicCamera camera2;
        final Vector3 curr = new Vector3();
        final Vector3 last = new Vector3(-1, -1, -1);
        final Vector3 delta = new Vector3();

        public DualCamController(OrthographicCamera camera1, OrthographicCamera camera2) {
            this.camera1 = camera1;
            this.camera2 = camera2;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);

            // Use Camera1 as the last point
            camera1.unproject(curr.set(x, y, 0));

            // If this isn't the first drag called
            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                // Still use camera 1 as the latest; this time as change
                camera1.unproject(delta.set(last.x, last.y, 0));
                delta.sub(curr);
                camera1.position.add(delta.x, delta.y * -1, 0);
                camera2.position.add(delta.x * 32, delta.y * -32, 0);
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