package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.utility.Point;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.observation.NotificationManager;
import com.icbat.game.tradesong.observation.notifications.GatherNotification;
import com.icbat.game.tradesong.observation.watchers.GatheringWatcher;
import com.icbat.game.tradesong.utility.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MapRandomSpawner implements Spawner {

    private static final String SPAWNABLE_ITEMS_KEY = "spawnableItems";
    private static final String INITIAL_SPAWN_COUNT_KEY = "initialSpawnCount";
    private static final String MAX_SPAWN_CAPACITY_KEY = "maxSpawnCapacity";
    private static final int DELAY_SECONDS = 0;
    private static final int INTERVAL_SECONDS = 1;

    private final Random seededRandom = Tradesong.state.getSeededRNG();
    private final Timer spawnTimer = new Timer();
    private List<ValidSpawnArea> validAreas;
    private int initialSpawnCount = 0;
    private Group ownersItems;

    private int maxCapacity = 0;

    private NotificationManager notificationCenter = new NotificationManager();

    private MapRandomSpawner() {}

    public static MapRandomSpawner make(TiledMap map, Group itemsGroupReference) {
        MapRandomSpawner spawner = new MapRandomSpawner();
        spawner.setOwnersItems(itemsGroupReference);
        spawner.notificationCenter.addWatcher(new GatheringWatcher());
        spawner.setupValidAreas(map);

        MapProperties properties = map.getProperties();
        spawner.setInitialSpawnCount(properties);
        spawner.setMaxCapacity(properties);

        spawner.spawnInitialItems();
        return spawner;
    }

    private void setupValidAreas(TiledMap map) {
        this.validAreas = parseAreas(map);
    }

    private int parseInteger(MapProperties properties, String key) {
        return Integer.parseInt((String) properties.get(key));
    }

    public void spawnInitialItems() {
        for (int i=0; i < initialSpawnCount; ++i) {
            spawnOneItem();
        }
    }

    @Override
    public Items.Item spawnOneItem() {
        Items.Item item = null;
        if (!validAreas.isEmpty() && !isFull(ownersItems, maxCapacity)) {
            int areaIndex = seededRandom.nextInt(validAreas.size() - 1);
            ValidSpawnArea area = validAreas.get(areaIndex);
            item = area.spawnItem();
            item.addListener(new GatherClickListener(item));
            ownersItems.addActor(item);
        }
        return item;
    }

    private boolean isFull(Group ownersItems, int maxCapacity) {
        return ownersItems.getChildren().size >= maxCapacity;
    }

    @Override
    public void start() {
        spawnTimer.start();
        spawnTimer.clear();
        spawnTimer.scheduleTask(new SpawnTask(), DELAY_SECONDS, INTERVAL_SECONDS);
    }

    @Override
    public void stop() {
        spawnTimer.stop();
        spawnTimer.clear();
    }

    private void setOwnersItems(Group ownersItems) {
        this.ownersItems = ownersItems;
    }

    private void setInitialSpawnCount(MapProperties properties) {
        this.initialSpawnCount = parseInteger(properties, INITIAL_SPAWN_COUNT_KEY);
    }

    private void setMaxCapacity(MapProperties properties) {
        this.maxCapacity = parseInteger(properties, MAX_SPAWN_CAPACITY_KEY);
    }

    private class SpawnTask extends Timer.Task {

        @Override
        public void run() {
            int spawnChance = seededRandom.nextInt(5);
            if (spawnChance == 0) {
                spawnOneItem();
            }
        }
    }

    private List<ValidSpawnArea> parseAreas(TiledMap map) {
        List<ValidSpawnArea> areasFound = new ArrayList<ValidSpawnArea>();
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().startsWith("#SPAWN")) {
                areasFound.add(parseSpawnArea((TiledMapTileLayer) layer));
            }
        }
        return areasFound;
    }

    private ValidSpawnArea parseSpawnArea(TiledMapTileLayer layer) {
        List<Point> spawnPoints = findSpawnableTiled(layer);
        List<Items.Item> spawnableItems = findSpawnableItems(layer, SPAWNABLE_ITEMS_KEY);
        return new ValidSpawnArea(spawnPoints, spawnableItems);
    }

    private List<Point> findSpawnableTiled(TiledMapTileLayer layer) {
        List<Point> tileCoordinates = new LinkedList<Point>();
        for (int x=0; x <= layer.getWidth(); ++x) {
            for (int y=0; y <= layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) {
                    tileCoordinates.add(new Point(x, y));
                }
            }
        }
        return tileCoordinates;
    }

    private List<Items.Item> findSpawnableItems(TiledMapTileLayer layer, String spawnableItemsKey) {
        List<Items.Item> spawnableItems = new ArrayList<Items.Item>();

        String spawnableBlob = (String) layer.getProperties().get(spawnableItemsKey);

        for (String itemName : spawnableBlob.split(",")) {
            Items.Item item = Tradesong.items.getItem(itemName.trim());
            if (item != null) {
                spawnableItems.add(item);
            } else {
                Gdx.app.error("Check properties in layer " + layer.getName() + "! Failed to find item by name", itemName);
                Gdx.app.debug("Possible items", Tradesong.items.getAll().toString());
            }
        }

        return spawnableItems;
    }

    /**
     * Non-rectangular area defined by all the tiles on a map layer whose name starts with #SPAWN
     */
    private class ValidSpawnArea {

        private final int ICON_SIZE = Constants.SPRITE_DIMENSION.value();
        private final List<Point> spawnableTiles;
        private final List<Items.Item> weightedSpawnableItemsList;

        public ValidSpawnArea(List<Point> spawnableTiles, List<Items.Item> spawnableItems) {
            this.spawnableTiles = spawnableTiles;
            this.weightedSpawnableItemsList = weightSpawnableItems(spawnableItems);
        }

        private List<Items.Item> weightSpawnableItems(List<Items.Item> spawnableItems) {
            List<Items.Item> weightedSpawns = new ArrayList<Items.Item>();

            for (Items.Item item : spawnableItems) {
                for (int i = 0; i < item.getRarity().getWeight(); ++i) {
                    weightedSpawns.add(new Items.Item(item));
                }
            }

            return weightedSpawns;
        }

        /**
         * @return the Item to spawn regardless of max capacity
         * */
        public Items.Item spawnItem() {
            if (!weightedSpawnableItemsList.isEmpty() && !spawnableTiles.isEmpty()) {
                Items.Item itemToSpawn = getRandomItem(this.weightedSpawnableItemsList);
                Point spawnPoint = getRandomSpawnPoint(this.spawnableTiles);
                itemToSpawn.setPosition(spawnPoint.getX() * ICON_SIZE, spawnPoint.getY() * ICON_SIZE);
                return itemToSpawn;
            }

            return null;
        }

        private Items.Item getRandomItem(List<Items.Item> spawnableItems) {
            int i = seededRandom.nextInt(spawnableItems.size());
            Items.Item item = new Items.Item(Tradesong.items.getItem("Sword"));
            try {

                item = new Items.Item(spawnableItems.get(i));
            } catch (NullPointerException npe) {
                Gdx.app.error("Current list trying to spawn from", spawnableItems.toString());
                Gdx.app.error("Exception trying to spawn item from list", item.getName(), npe);

            }
            return item;
        }

        private Point getRandomSpawnPoint(List<Point> spawnableTiles) {
            int i = seededRandom.nextInt(spawnableTiles.size());
            return spawnableTiles.get(i);
        }
    }

    /**
     * Class to handle touching/clicking of items on levels.
     */
    private class GatherClickListener extends ClickListener {
        Items.Item owner;

        GatherClickListener(Items.Item owner) {
            this.owner = owner;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            notificationCenter.notifyWatchers(new GatherNotification(owner));
        }

    }


}
