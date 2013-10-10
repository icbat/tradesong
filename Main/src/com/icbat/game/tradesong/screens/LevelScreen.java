package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.TownStage;
import com.icbat.game.tradesong.stages.WyldStage;
import com.icbat.game.tradesong.utils.OrthoCamera;
import com.icbat.game.tradesong.utils.TextureAssets;

/**
 * Generic level screen. The way maps are shown.
 * */
public class LevelScreen extends AbstractScreen {

	public String mapName;

    Timer itemSpawnTimer = new Timer();

    private TiledMap map;
	private TiledMapRenderer renderer;

	private OrthoCamera rendererCamera;
    private final OrthoCamera gameWorldCamera;

    private SpriteBatch batch = new SpriteBatch();
    private Texture background = Tradesong.getTexture(TextureAssets.SKY);

    public LevelScreen(String level, Tradesong gameInstance) {
        stages.clear(); // Needs to have HUD added last.

        // Load the map
        Tradesong.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        this.mapName = "maps/" + level + ".tmx";  // "Internal" relative address. What the asset loader wants. Is there a better way to do this?

        Tradesong.assetManager.load(mapName, TiledMap.class);
        Tradesong.assetManager.finishLoading();

        this.map = Tradesong.assetManager.get(mapName);
        this.renderer = new OrthogonalTiledMapRenderer(this.map, 1);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        // Load the stages
        if (map.getProperties().get("type").equals("town")) {
            stages.add(new TownStage(gameInstance, map.getProperties()));
        }
        else {
            stages.add(new WyldStage(gameInstance, map.getProperties()));

            int spawnInitialDelay = 5;
            int spawnIntervalSeconds = 6;
            itemSpawnTimer.scheduleTask(
                    new Timer.Task() {
                        public void run() {
                            ((WyldStage)stages.get(0)).spawnItem();
                        }

                    }, spawnInitialDelay, spawnIntervalSeconds
            );
        }

        stages.add(Tradesong.getHud());

        // Set up cameras
        rendererCamera = new OrthoCamera(width, height);
        gameWorldCamera = new OrthoCamera(width, height);



        stages.get(0).setCamera(gameWorldCamera);

    }

    @Override
	public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        rendererCamera.update();
        renderer.setView(rendererCamera);
		renderer.render();

        // This happens in Super, but must happen here as well or it will be behind the map
        for (AbstractStage stage : stages) {
            stage.act(delta);
            stage.draw();
        }
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        rendererCamera.setToOrtho(false, width, height);
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
        ((HUDStage)stages.get(1)).getDragCatcher().clearListeners();
    }

    @Override
    public void show() {
        super.show();
        itemSpawnTimer.start();
        // DualCamController

        ((HUDStage)stages.get(1)).getDragCatcher().addListener(new DualCamController(rendererCamera, gameWorldCamera));
    }

    @Override
    public void resume() {
        super.resume();
        itemSpawnTimer.start();
    }

    /**
     * Input handling for moving rendererCamera on maps. Handles moving both cameras simultaneously regardless of how it's needed.
     * */
    class DualCamController extends ClickListener {
        final OrthographicCamera camera1;
        final OrthographicCamera camera2;
        final Vector3 curr = new Vector3();
        final Vector3 last = new Vector3(-1, -1, -1);
        final Vector3 delta = new Vector3();
        private static final float DRAG_SPEED = 0.7f;

        public DualCamController(OrthographicCamera camera1, OrthographicCamera camera2) {
            this.camera1 = camera1;
            this.camera2 = camera2;

        }


        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);

            this.moveCameraBy(x, y);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            last.set(-1, -1, -1);
        }

        public void moveCameraBy(float x, float y) {
            // Use Camera1 as the last point
            curr.set(x, y, 0);

            // If this isn't the first drag called
            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                // Still use camera 1 as the latest; this time as change
                delta.set(last.x, last.y, 0);
                delta.sub(curr);
                camera1.translate(delta.x * DRAG_SPEED, delta.y * DRAG_SPEED);
                camera2.position.set(camera1.position);
            }

            last.set(x, y, 0);
        }

    }

    @Override
    public String toString() {
        return super.toString() + "." + this.mapName;
    }
}