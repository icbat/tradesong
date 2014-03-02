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
import com.icbat.game.tradesong.utils.Constants;
import com.icbat.game.tradesong.utils.Point;

import java.util.*;

public class MapStage extends BaseStage {
    public static final String SPAWNABLE_ITEMS_KEY = "spawnableItems";
    private HashSet<ValidSpawnArea> spawnAreas = new HashSet<ValidSpawnArea>();
    private Timer spawnTimer = new Timer();
    private int maxItemsOnMap = 0;

    public MapStage(TiledMap map) {
        MapProperties mapProperties = map.getProperties();
        setupAreas(map);
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
//        this.spawnTimer.scheduleTask(new Timer.Task() {
//            @Override
//            public void run() {
//                if (getActors().size < maxItemsOnMap) {
//                    spawnRandomItem(new ArrayList<String>(spawnableItemNames), new ArrayList<ValidSpawnArea>(spawnAreas));
//                }
//
//            }
//        }, 3, 3);
//        this.spawnTimer.start();
    }

    private void getMaxItems(MapProperties mapProperties) {
        String maxSpawnsBlob = (String) mapProperties.get("maxSpawnCapacity");
        if (maxSpawnsBlob != null) {
            maxItemsOnMap = Integer.parseInt(maxSpawnsBlob);
        }
    }

    private void setupAreas(TiledMap map) {
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().startsWith("#SPAWN")) {
                TiledMapTileLayer spawnLayer = (TiledMapTileLayer) layer;
                List<Point> spawnableTiles = parseValidSpawnAreaZone(spawnLayer);
                Set<Item> spawnableItems = new HashSet<Item>();
                try {
                    spawnableItems = parseSpawnableItems(spawnLayer);
                } catch (NullPointerException npe) {
                    Gdx.app.error("Layer " + layer.getName(), "has no spawnableItems attribute or found no items", npe);
                }


                Gdx.app.debug(layer.getName(), "found " + spawnableTiles.size() + " spawnable tiles ");
                spawnAreas.add( new ValidSpawnArea(spawnableTiles, spawnableItems) );
            }
        }
    }

    private List<Point> parseValidSpawnAreaZone(TiledMapTileLayer spawnLayer) {
        // This feels bad. Maybe there's a better way to do this by extending GDX?
        List<Point> spawnableTiles = new LinkedList<Point>();
        for (int x=0; x <= spawnLayer.getWidth(); ++x) {
            for (int y=0; y <= spawnLayer.getHeight(); ++y) {
                if (spawnLayer.getCell(x, y) != null) {
                    spawnableTiles.add(new Point(x, y));
                }
            }
        }
        return spawnableTiles;
    }

    private Set<Item> parseSpawnableItems(TiledMapTileLayer spawnLayer) {
        String spawnableItemsBlob = (String) spawnLayer.getProperties().get(SPAWNABLE_ITEMS_KEY);
        String[] splitItems = spawnableItemsBlob.split(",");
        Set<Item> spawnableItems = new HashSet<Item>();
        for (String itemName : splitItems) {
            spawnableItems.add(Tradesong.itemPrototypes.get(itemName));
        }
        return spawnableItems;
    }

    private void spawnInitialItems(MapProperties mapProperties) {
//        String initialNodesString = (String) mapProperties.get("initialSpawnCount");
//        Integer initialNodes = (initialNodesString == null) ? 0 : Integer.parseInt(initialNodesString);
//        if ((!spawnableItemNames.isEmpty() && !spawnAreas.isEmpty())) {
//            List<String> spawnableItems = new ArrayList<String>(spawnableItemNames);
//            List<ValidSpawnArea> areas = new ArrayList<ValidSpawnArea>(spawnAreas);        // consider using the stage or a group's random actor fn
//
//            for (int i = 0; i < initialNodes; ++i) {
//                if (spawnRandomItem(spawnableItems, areas)) return;
//            }
//        }
    }

    // TODO move into VSA
//    private boolean spawnRandomItem(List<String> spawnableItems, List<ValidSpawnArea> areas) {
//        Random random = new Random();
//        String nameToSpawn = "NULL";
//        try {
//            nameToSpawn = spawnableItems.get(random.nextInt(spawnableItemNames.size()));
//        } catch (IllegalArgumentException e) {
//            Gdx.app.error("List of valid spawnable items", spawnableItems.toString());
//            Gdx.app.error("List of valid spawnable areas", areas.toString());
//            Gdx.app.error("Spawning random item " + nameToSpawn + " caused error", "Suspect one of the preceeding lists to be empty. Check map properties and layer properties", e);
//        }
//
//        Item spawn = Tradesong.itemPrototypes.get(nameToSpawn);
//
//        return spawnItem(areas, spawn);
//    }

    // TODO move in to VSA
//    private boolean spawnItem(List<ValidSpawnArea> areas, Item spawn) {
//        if (spawn == null) {
//            Gdx.app.error("spawning item on map", "null");
//            return true;
//        }
//
//        areas.get(0).spawnItemInsideArea(spawn);
//        spawn.addListener(new GatherClickListener(spawn));
//        this.addActor(spawn); // Looks weird, but the spawn area just sets position on the actor.
//        Gdx.app.debug("spawned " + spawn.getName() + " at", spawn.getGameX() + ", " + spawn.getGameY());
//        return false;
//    }


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

        private final int ICON_SIZE = Constants.SPRITE_DIMENSION.value();
        private final List<Point> spawnableTiles;
        private final Set<Item> spawnableItems;

        public ValidSpawnArea(List<Point> spawnableTiles, Set<Item> spawnableItems) {
            this.spawnableTiles = spawnableTiles;
            this.spawnableItems = spawnableItems;
        }

//        public void spawnItemInsideArea(Item item) {
//            Random random = new Random();
//            int x = random.nextInt(endX - startX + 1) + startX;
//            int y = random.nextInt(endY - startY + 1) + startY + 3; // Why does +3 work?
//            x *= ICON_SIZE;
//            y *= ICON_SIZE;
//
//            item.setPosition(x, y);
//        }
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
