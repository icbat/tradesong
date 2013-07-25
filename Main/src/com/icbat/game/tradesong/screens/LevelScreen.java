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
import com.icbat.game.tradesong.OrthoCamera;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.GameWorldStage;
import com.icbat.game.tradesong.stages.InterfaceOverlay;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName = "";
    protected Stage worldStage;


    int initialItemCount = 4;
    int itemCount;
    int maxSpawnedPerMap = 10; // TODO pull this out of map properties

    private TiledMap map = null;
	private TiledMapRenderer renderer = null;

	private OrthographicCamera rendererCamera = new OrthographicCamera();
    private OrthographicCamera stageCamera = new OrthographicCamera();
    private Actor backgroundActor = new Actor();

    private Timer timer = new Timer();
    private LevelItemFactory itemFactory;


	public LevelScreen(String level, Tradesong game) {
        super(game);
        // Load the map
        game.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        this.mapName = "maps/" + level + ".tmx";  // "Internal" relative address. What the asset loader wants. Is there a better way to do this?

        game.assets.load(mapName, TiledMap.class);
        game.assets.finishLoading();

        this.map = game.assets.get(mapName);
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 1f / 64f);

        // Setup an input Multiplexer



        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        // Load the World Stage
        worldStage = new GameWorldStage(map.getProperties());
        worldStage.setCamera(new OrthoCamera(width, height));

        // Load the UI's Stage
//        UIStage = new InterfaceOverlay();
//        UIStage.setCamera(new OrthoCamera(width, height));

    }
	
	@Override
	public void render(float delta) {
		super.render(delta);
		rendererCamera.update();
		renderer.setView(rendererCamera);
		renderer.render();
        worldStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		worldStage.draw();
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldStage.setViewport(width, height, false);
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
     * Input handling for moving rendererCamera on maps. Handles:
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