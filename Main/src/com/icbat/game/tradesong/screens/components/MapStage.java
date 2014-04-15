package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.gameObjects.MapRandomSpawner;
import com.icbat.game.tradesong.gameObjects.Spawner;
import com.icbat.game.tradesong.gameObjects.Portal;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;

public class MapStage extends Stage {

    Spawner spawner;
    Group items = new Group();

    public MapStage(TiledMap map) {
        setupPortals(map);
        spawner = MapRandomSpawner.make(map, items);
        spawner.start();
        this.addActor(items);
    }

    private void setupPortals(TiledMap map) {
        for ( MapLayer layer : map.getLayers() ) {
            if (layer.getName().contains("#PORTALS")) {
                for (MapObject obj : layer.getObjects()) {
                    Gdx.app.debug("found map object", obj.getName());

                    this.addActor(Portal.makePortal(obj));
                }
            }
        }
    }

    public void setDragListener(Camera camera) {
        this.addListener(new DragMoveListener(camera));
    }

    public void onHide() {
        spawner.stop();
    }

    public void onShow() {
        spawner.start();
    }

    @Override
    public void dispose() {
        super.dispose();
        spawner.stop();
    }
}
