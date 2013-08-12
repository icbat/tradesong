package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.icbat.game.tradesong.OrthoCamera;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;
import com.icbat.game.tradesong.stages.GameWorldStage;
import com.icbat.game.tradesong.stages.HUDStage;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName;

    Timer itemSpawnTimer = new Timer();

    private TiledMap map;
	private TiledMapRenderer renderer;

	private OrthoCamera rendererCamera;
    private final int mapWidth;
    private final int mapHeight;

    public LevelScreen(String level, HUDStage hud) {
        super();

        // Load the map
        Tradesong.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        this.mapName = "maps/" + level + ".tmx";  // "Internal" relative address. What the asset loader wants. Is there a better way to do this?

        Tradesong.assets.load(mapName, TiledMap.class);
        Tradesong.assets.finishLoading();

        this.map = Tradesong.assets.get(mapName);
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 1); // TODO read about this class

        mapWidth = (Integer)map.getProperties().get("width");
        mapHeight = (Integer)map.getProperties().get("height");



        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        // Load the stages
        stages.add(new GameWorldStage(map.getProperties()));
        stages.add(hud);

        // Set up cameras
        rendererCamera = new OrthoCamera(width, height);
        OrthoCamera gameWorldCamera = new OrthoCamera(width, height);


        stages.get(0).setCamera(gameWorldCamera);


        // DualCamController
        ((GameWorldStage)stages.get(0)).getBackgroundActor().addListener(new DualCamController(rendererCamera, gameWorldCamera));

        // Setup an input Multiplexer
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(stages.get(0));

        int spawnInitialDelay = 5;
        int spawnIntervalSeconds = 6;
        itemSpawnTimer.scheduleTask(
            new Timer.Task() {
                public void run() {
                    ((GameWorldStage)stages.get(0)).spawnItem();
                }

            }, spawnInitialDelay, spawnIntervalSeconds
        );
    }

    @Override
	public void render(float delta) {
        super.render(delta);

        rendererCamera.update();

        renderer.setView(rendererCamera);
		renderer.render();
        for (AbstractStage stage : stages) {
            stage.act(delta);
            stage.draw();
        }
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ((GameWorldStage)stages.get(0)).getBackgroundActor().setBounds(0, 0, width, height);
        rendererCamera.setToOrtho(false, width, height);
//        rendererCamera.position.set(mapHeight/2, mapWidth/2, 0);
        rendererCamera.update();
    }

	@Override
	public void dispose() {
        itemSpawnTimer.clear(); // Cancels all tasks

		this.map.dispose();

        super.dispose(); // Likely needs to be called last

	}

    @Override
    public void pause() {
        super.pause();
        itemSpawnTimer.stop();
    }

    @Override
    public void hide() {
        super.hide();
        itemSpawnTimer.stop();
    }

    @Override
    public void show() {
        super.show();
        itemSpawnTimer.start();
    }

    @Override
    public void resume() {
        super.resume();
        itemSpawnTimer.start();
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
                camera1.position.add(delta.x / 32, delta.y * -32, 0);
                camera2.position.add(delta.x * 32, delta.y * -32, 0);
            }

//            camera1.update();

            last.set(x, y, 0);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            last.set(-1, -1, -1);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "." + this.mapName;    //To change body of overridden methods use File | Settings | File Templates.
    }
}