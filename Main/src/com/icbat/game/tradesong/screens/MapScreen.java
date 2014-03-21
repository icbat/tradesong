package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Items;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Portal;
import com.icbat.game.tradesong.observation.notifications.GatherNotification;
import com.icbat.game.tradesong.observation.notifications.StopNotification;
import com.icbat.game.tradesong.observation.watchers.GatheringWatcher;
import com.icbat.game.tradesong.screens.listeners.DragMoveListener;
import com.icbat.game.tradesong.screens.listeners.ZoomOnScrollListener;
import com.icbat.game.tradesong.utils.Constants;
import com.icbat.game.tradesong.utils.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        this.setupInputMultiplexer();
        centerCamera(map.getProperties());
    }

    private void centerCamera(MapProperties properties) {
        Point center = findCenter(properties);
        zeroCamera(camera);
        camera.translate(center.getX(), center.getY());
        camera.update();
    }

    private Point findCenter(MapProperties properties) {
        Integer height = (Integer) properties.get("height");
        height = height * Constants.SPRITE_DIMENSION.value();
        height = height / 2;
        Integer width = (Integer) properties.get("width");
        width = width * Constants.SPRITE_DIMENSION.value();
        width = width / 2;
        return new Point(width, height);
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

    public static class MapStage extends BaseStage {
        public static final String SPAWNABLE_ITEMS_KEY = "spawnableItems";
        public static final String INITIAL_SPAWN_COUNT_KEY = "initialSpawnCount";
        public static final String MAX_SPAWN_CAPACITY_KEY = "maxSpawnCapacity";
        private List<ValidSpawnArea> spawnAreas = new LinkedList<ValidSpawnArea>();
        private Timer spawnTimer = new Timer();
        private int maxItemsOnMap = 0;
        protected final Random seededRandom = Tradesong.state.getSeededRNG();

        public MapStage(TiledMap map) {
            MapProperties mapProperties = map.getProperties();
            setupPortals(map);
            setupAreas(map);
            maxItemsOnMap = getMaxItems(mapProperties);
            try {
                Integer initialSpawnedItems = Integer.parseInt((String) mapProperties.get(INITIAL_SPAWN_COUNT_KEY));
                spawnInitialItems(initialSpawnedItems);
            } catch (NumberFormatException nfe) {
                Gdx.app.log("Could not determine how many items to spawn initially", "map property " + INITIAL_SPAWN_COUNT_KEY + "likely missing.", nfe);
            }

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

        @Override
        public void dispose() {
            super.dispose();
            spawnTimer.clear();
            spawnTimer.stop();
            Gdx.app.debug("map stage", "disposed");
        }

        @Override
        public void layout() {
            super.layout();
            notificationCenter.addWatcher(new GatheringWatcher());
            startSpawnTimer();
        }

        private void startSpawnTimer() {
            this.spawnTimer.stop();
            this.spawnTimer.clear();
            this.spawnTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    addRandomItem();
                }
            }, 3, 3);
            this.spawnTimer.start();
        }

        private Integer getMaxItems(MapProperties mapProperties) {
            String maxSpawnsBlob = (String) mapProperties.get(MAX_SPAWN_CAPACITY_KEY);
            if (maxSpawnsBlob != null) {
                return Integer.parseInt(maxSpawnsBlob);
            }
            Gdx.app.log("Could not find map attribute maxSpawnCapacity", "Defaulting to 0");
            return 0;
        }

        private void setupAreas(TiledMap map) {
            for (MapLayer layer : map.getLayers()) {
                if (layer.getName().startsWith("#SPAWN")) {
                    TiledMapTileLayer spawnLayer = (TiledMapTileLayer) layer;
                    List<Point> spawnableTiles = parseValidSpawnAreaZone(spawnLayer);
                    List<Items.Item> spawnableItems = new LinkedList<Items.Item>();
                    try {
                        spawnableItems = parseSpawnableItems(spawnLayer);

                    } catch (NullPointerException npe) {
                        Gdx.app.error("Layer " + layer.getName(), "has no spawnableItems attribute or found no items", npe);
                    }
                    spawnAreas.add(new ValidSpawnArea(spawnableTiles, spawnableItems));
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

        private List<Items.Item> parseSpawnableItems(TiledMapTileLayer spawnLayer) {
            String spawnableItemsBlob = (String) spawnLayer.getProperties().get(SPAWNABLE_ITEMS_KEY);
            String[] splitItems = spawnableItemsBlob.split(",");
            List<Items.Item> spawnableItems = new LinkedList<Items.Item>();
            for (String itemName : splitItems) {
                Items.Item itemToAdd = Tradesong.items.getItem(itemName.trim());
                if (itemToAdd != null) {
                    spawnableItems.add(itemToAdd);
                } else {
                    Gdx.app.error("Failed to find item by name", itemName);
                }

            }
            return spawnableItems;
        }

        private void spawnInitialItems(int itemsToSpawn) {
            for (int i=0; i < itemsToSpawn; ++i) {
                addRandomItem();
            }
        }

        private boolean addRandomItem() {
            int index;
            try {
                index = seededRandom.nextInt(spawnAreas.size());
                Items.Item item = spawnAreas.get(index).spawnItem();
                return addItemToMap(item, maxItemsOnMap);
            } catch (IllegalArgumentException iea){
                Gdx.app.log("Error encountered spawning random item", "Suspect no areas or items found.", iea);
                return false;
            }
        }

        /**
         * Checks for capacity, and adds item to map if possible
         *
         * @return true if the item was successfully added.
         * */
        private boolean addItemToMap(Items.Item item, int maxItemsOnMap) {
            if (this.getActors().size < maxItemsOnMap) {
                Gdx.app.debug("spawning item", item.getName());
                this.addActor(item);
                return true;
            }
            return false;
        }

        public void setDragListener(Camera camera) {
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
                    itemToSpawn.addListener(new GatherClickListener(itemToSpawn));
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
        class GatherClickListener extends ClickListener {
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
}
