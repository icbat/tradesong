package com.icbat.game.tradesong.screens.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;

import java.util.Collections;
import java.util.HashSet;

public class MapStage extends Stage {
    private Actor dragCatcher = new Actor();
    private HashSet<String> spawnableItemNames = new HashSet<String>();
    public MapStage(MapProperties mapProperties) {
        this.addActor(dragCatcher);
        getPrototypeNames(mapProperties);
        spawnInitialItems(mapProperties);
    }

    private void getPrototypeNames(MapProperties mapProperties) {
        String spawnableItemsBlob = (String) mapProperties.get("spawnableItems");
        if (spawnableItemsBlob == null) {
            Gdx.app.log("getPrototypeNamesOfSpawnables", "No items found for map.");
            return;
        }
        String[] spawnableItems = spawnableItemsBlob.split(",");
        Collections.addAll(this.spawnableItemNames, spawnableItems);
        for (String name : this.spawnableItemNames) {
            Gdx.app.debug("spawnableItem", name);
        }
    }

    private void spawnInitialItems (MapProperties mapProperties) {
        Integer initialNodes = Integer.parseInt((String) mapProperties.get("initialSpawnCount"));


    }

    public void setDragListener (Camera camera) {
        Gdx.app.debug("", "Setting a drag listener");
        dragCatcher.clearListeners();
        dragCatcher.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        dragCatcher.setTouchable(Touchable.enabled);
        Gdx.app.debug("Drag Listeners", dragCatcher.getListeners().size + "");
        dragCatcher.addListener(new DragMoveListener(camera));
        Gdx.app.debug("Drag Listeners", dragCatcher.getListeners().size + "");

    }
}
