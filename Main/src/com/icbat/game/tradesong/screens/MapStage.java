package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.MapRandomSpawner;
import com.icbat.game.tradesong.Spawner;
import com.icbat.game.tradesong.gameObjects.Portal;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;

public class MapStage extends Stage {

    Spawner spawner;

    public MapStage(TiledMap map) {
        setupPortals(map);
        spawner = MapRandomSpawner.make(map, this);
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

    // TODO keep track of current items separately
    // TODO start and stop timers.

    public void setDragListener(Camera camera) {
        this.addListener(new DragMoveListener(camera));
    }

}
