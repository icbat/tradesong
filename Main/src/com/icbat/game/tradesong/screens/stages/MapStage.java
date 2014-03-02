package com.icbat.game.tradesong.screens.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.observation.notifications.GatherNotification;
import com.icbat.game.tradesong.observation.notifications.StopNotification;
import com.icbat.game.tradesong.observation.watchers.GatheringWatcher;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;
import com.icbat.game.tradesong.utils.Point;

import java.util.*;

public class MapStage extends BaseStage {
    private HashSet<String> spawnableItemNames = new HashSet<String>();
    private HashSet<ValidSpawnArea> spawnAreas = new HashSet<ValidSpawnArea>();
    private Timer spawnTimer = new Timer();
    private int maxItemsOnMap = 0;

    public MapStage(TiledMap map) {
        MapProperties mapProperties = map.getProperties();
        setupAreas(map);
        getPrototypeNames(mapProperties);
        getMaxItems(mapProperties);
        spawnInitialItems(mapProperties);
    }

    @Override
    public void layout() {
        super.layout();
        notificationCenter.addWatcher(new GatheringWatcher());
        startSpawnTimer();

    }

    private void startSpawnTimer() {
        this.spawnTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (getActors().size < maxItemsOnMap) {
                    spawnRandomItem(new ArrayList<String>(spawnableItemNames), new ArrayList<ValidSpawnArea>(spawnAreas));
                }

            }
        }, 3, 3);
        this.spawnTimer.start();
    }

    private void getMaxItems(MapProperties mapProperties) {
        String maxSpawnsBlob = (String) mapProperties.get("maxSpawnCapacity");
        if (maxSpawnsBlob != null) {
            maxItemsOnMap = Integer.parseInt(maxSpawnsBlob);
        }
    }

    private void setupAreas(TiledMap map) {

        MapLayers layers = map.getLayers();

        TiledMapTileLayer spawnLayer = (TiledMapTileLayer) layers.get("#SPAWN wholeMap");

        // TODO expand to more than 1 area
        // This feels bad. Maybe there's a better way to do this by extending GDX?
        List<Point> spawnableTiles = new LinkedList<Point>();
        for (int x=0; x <= spawnLayer.getWidth(); ++x) {
            for (int y=0; y <= spawnLayer.getHeight(); ++y) {
                if (spawnLayer.getCell(x, y) != null) {
                    spawnableTiles.add(new Point(x, y));
                }
            }
        }

        Gdx.app.debug("found spawnable tiles", spawnableTiles.size() +"");
        spawnAreas.add( new ValidSpawnArea(spawnableTiles) );

    }

    private void getPrototypeNames(MapProperties mapProperties) {
        String spawnableItemsBlob = (String) mapProperties.get("spawnableItems");
        if (spawnableItemsBlob != null) {
            String[] spawnableItems = spawnableItemsBlob.split(",");
            Collections.addAll(this.spawnableItemNames, spawnableItems);
            for (String name : this.spawnableItemNames) {
                Gdx.app.debug("spawnableItem", name);
            }
        }

    }

    private void spawnInitialItems(MapProperties mapProperties) {
        String initialNodesString = (String) mapProperties.get("initialSpawnCount");
        Integer initialNodes = (initialNodesString == null) ? 0 : Integer.parseInt(initialNodesString);
        if ((!spawnableItemNames.isEmpty() && !spawnAreas.isEmpty())) {
            List<String> spawnableItems = new ArrayList<String>(spawnableItemNames);
            List<ValidSpawnArea> areas = new ArrayList<ValidSpawnArea>(spawnAreas);        // consider using the stage or a group's random actor fn

            for (int i = 0; i < initialNodes; ++i) {
                if (spawnRandomItem(spawnableItems, areas)) return;
            }
        }
    }

    // TODO move into VSA
    private boolean spawnRandomItem(List<String> spawnableItems, List<ValidSpawnArea> areas) {
        Random random = new Random();
        String nameToSpawn = "NULL";
        try {
            nameToSpawn = spawnableItems.get(random.nextInt(spawnableItemNames.size()));
        } catch (IllegalArgumentException e) {
            Gdx.app.error("List of valid spawnable items", spawnableItems.toString());
            Gdx.app.error("List of valid spawnable areas", areas.toString());
            Gdx.app.error("Spawning random item " + nameToSpawn + " caused error", "Suspect one of the preceeding lists to be empty. Check map properties and layer properties", e);
        }

        Item spawn = Tradesong.itemPrototypes.get(nameToSpawn);

        return spawnItem(areas, spawn);
    }

    // TODO move in to VSA
    private boolean spawnItem(List<ValidSpawnArea> areas, Item spawn) {
        if (spawn == null) {
            Gdx.app.error("spawning item on map", "null");
            return true;
        }

        areas.get(0).spawnItemInsideArea(spawn);
        spawn.addListener(new GatherClickListener(spawn));
        this.addActor(spawn); // Looks weird, but the spawn area just sets position on the actor.
        Gdx.app.debug("spawned " + spawn.getName() + " at", spawn.getGameX() + ", " + spawn.getGameY());
        return false;
    }


    public void setDragListener(Camera camera) {
        Gdx.app.debug("", "Setting a drag listener");
        this.addListener(new DragMoveListener(camera));
    }

    @Override
    public void hide() {
        spawnTimer.stop();
        spawnTimer.clear();
        notificationCenter.notifyWatchers(new StopNotification());
    }

    /**
     * Non-rectangular area defined by all the tiles on a map layer whose name starts with #SPAWN
     */
    class ValidSpawnArea {
        Integer startX;
        Integer startY;
        Integer endX;
        Integer endY;
        private static final int ICON_SIZE = 32;

        /**
         * Expects exactly 4 elements. Will fail with errors if passed less than 4.
         */
        public ValidSpawnArea(String[] coords) {
            this.startX = Integer.parseInt(coords[0]);
            this.startY = Integer.parseInt(coords[1]);
            this.endX = Integer.parseInt(coords[2]);
            this.endY = Integer.parseInt(coords[3]);
        }

        public ValidSpawnArea(List<Point> spawnableTiles) {

        }

        public void spawnItemInsideArea(Item item) {
            Random random = new Random();
            int x = random.nextInt(endX - startX + 1) + startX;
            int y = random.nextInt(endY - startY + 1) + startY + 3; // Why does +3 work?
            x *= ICON_SIZE;
            y *= ICON_SIZE;

            item.setPosition(x, y);
        }
    }

    /**
     * Class to handle touching/clicking of items on levels.
     */
    class GatherClickListener extends ClickListener {
        Item owner;

        GatherClickListener(Item owner) {
            this.owner = owner;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            notificationCenter.notifyWatchers(new GatherNotification(owner));
        }

    }
}
