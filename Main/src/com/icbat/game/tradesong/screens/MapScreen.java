package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.stages.HUD;
import com.icbat.game.tradesong.screens.stages.MapStage;

/**
 * Screen to handle map display and moving between maps.
 */
public class MapScreen extends AbstractScreen {
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();

    public MapScreen(String mapName) {
        Gdx.app.log("Setting Map to", mapName);
        setMap(mapName);
    }

    public void setMap(String mapName) {
        String mapFile = "maps/" + mapName + ".tmx";
        Tradesong.assetManager.load(mapFile, TiledMap.class);
        Tradesong.assetManager.finishLoading();
        Gdx.app.log("Map loaded", mapFile);

        TiledMap map = Tradesong.assetManager.get(mapFile);
        this.mapRenderer = new OrthogonalTiledMapRenderer(map, 1);

        MapStage mapStage = new MapStage(map.getProperties());
        mapStage.setDragListener(camera);
        mapStage.setCamera(camera);
        stages.add(mapStage);

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.camera.setToOrtho(false, width, height);
        this.camera.update();
    }

    @Override
    public String getScreenName() {
        return "mapScreen";
    }

    @Override
    public void render(float delta) {
        drawBackground(0.7f, 0.99f, 1, delta);
        camera.update();
        this.mapRenderer.setView(camera);
        this.mapRenderer.render();
        renderStages(delta);
    }
}
