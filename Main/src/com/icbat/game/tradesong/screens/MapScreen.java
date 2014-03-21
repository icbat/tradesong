package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.listeners.ZoomOnScrollListener;
import com.icbat.game.tradesong.Constants;

public class MapScreen extends AbstractScreen {
    private TiledMapRenderer mapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();

    public MapScreen(String mapName) {
        super();
        moveToMap(mapName);
    }

    public void moveToMap(String mapName) {
        String mapFile = "maps/" + mapName + ".tmx";
        Tradesong.assetManager.load(mapFile, TiledMap.class);
        Tradesong.assetManager.finishLoading();
        Gdx.app.log("Map loaded", mapFile);

        TiledMap map = Tradesong.assetManager.get(mapFile);
        this.mapRenderer = new OrthogonalTiledMapRenderer(map, 1);
        MapStage mapStage = setupMapStage(map);
        this.setupStages();
        this.stages.add(mapStage);
        this.setupInputMultiplexer();
        centerCamera(map.getProperties());
    }

    private void centerCamera(MapProperties properties) {
        MapStage.Point center = findCenter(properties);
        zeroCamera(camera);
        camera.translate(center.getX(), center.getY());
        camera.update();
    }

    private MapStage.Point findCenter(MapProperties properties) {
        Integer height = (Integer) properties.get("height");
        height = height * Constants.SPRITE_DIMENSION.value();
        height = height / 2;
        Integer width = (Integer) properties.get("width");
        width = width * Constants.SPRITE_DIMENSION.value();
        width = width / 2;
        return new MapStage.Point(width, height);
    }

    private MapStage setupMapStage(TiledMap map) {
        MapStage mapStage = new MapStage(map);
        mapStage.setDragListener(camera);
        mapStage.setCamera(camera);
        mapStage.addListener(new ZoomOnScrollListener(camera));
        return mapStage;
    }

    @Override
    public void resize(int width, int height) {
        Vector3 beforePosition = new Vector3(camera.position);
        super.resize(width, height);
        this.camera.setToOrtho(false, width, height);
        zeroCamera(this.camera);
        this.camera.translate(beforePosition);
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

    private void zeroCamera(OrthographicCamera camera) {
        Vector3 oldPosition = new Vector3(camera.position);
        oldPosition.scl(-1);
        camera.translate(oldPosition);
    }

}
