package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;

import java.util.ArrayList;
import java.util.Random;

/** This stage governs:
 *  - items
 *  - the draggable, clear background
 *  - it's also a nice encapsulation of adding/removing items
 *  */
public class GameWorldStage extends Stage {

    public static final String PROPERTY_INITIAL_SPAWN_COUNT = "initialSpawnCount";
    public static final String PROPERTY_SPAWN_CAPACITY = "maxSpawnCapacity";
    public static final String PROPERTY_SPAWNABLE_ITEMS = "spawnableItems";

    private Tradesong gameInstance;
    private final ArrayList<Item> possibleItemSpawns = new ArrayList<Item>();
    private int mapX = 0;
    private int mapY = 0;


    private Actor backgroundActor = new Actor();

    int initialItemCount;
    int itemCount;
    int maxSpawnedPerMap;

    public GameWorldStage(Tradesong gameInstance, MapProperties properties) {
        this.gameInstance = gameInstance;

        // Get coords for setting bounds
        mapX = (Integer)properties.get("width");
        mapY = (Integer)properties.get("height");

        //    // Actor for dragging map around. Covers all the ground but doesn't have an image
        backgroundActor.setTouchable(Touchable.enabled);
        backgroundActor.setVisible(true);
        this.addActor(backgroundActor);

        // Use the Map properties to get some good stuff
        initialItemCount = Integer.parseInt((String)properties.get(PROPERTY_INITIAL_SPAWN_COUNT));
        maxSpawnedPerMap = Integer.parseInt((String)properties.get(PROPERTY_SPAWN_CAPACITY));
        String[] itemsArray = ((String)properties.get(PROPERTY_SPAWNABLE_ITEMS)).split(",");

        // Figure out what spawns here and what the total rarity is
        for (String itemName : itemsArray) {
            possibleItemSpawns.add( gameInstance.gameState.getItemByName(itemName) );
        }


        for (int i = 0; i < initialItemCount; ++i) {
            spawnItem();
        }
    }

    public boolean spawnItem() {
        if (itemCount < maxSpawnedPerMap + 1) {
            finalizeItemForView(chooseItemByRarity());
            ++itemCount;
            return true;
        } else {
            return false;
        }
    }

    /***/
    private Item chooseItemByRarity() {
        int totalRarity = 0;
        for (Item item : possibleItemSpawns) {
            totalRarity += 2 << item.getRarity() - 1;
        }

        // Rarity algorithm
        Random random = new Random();
        int n = random.nextInt(totalRarity); // 0 - (totalRarity - 1)

        int highestSeen = 0;
        for (Item item : possibleItemSpawns) {
            highestSeen += item.getRarity();
            if (n < highestSeen) {
                return item;
            }
        }

        return null; // TODO if this ever gets hit, make a note and FIX IT
    }

    /** Performs common steps for Items being added to the stage randomly */
    private void finalizeItemForView(Item item) {

        item.addListener(new ItemClickListener(item));
        int[] coords = getRandomCoords();
        item.setBounds(coords[0], coords[1], 34, 34);   // TODO constants
        item.setTouchable(Touchable.enabled);
        item.setVisible(true);
        this.addActor(item);
    }

    private int[] getRandomCoords() {
        int[] output = new int[2];

        Random random = new Random();

        output[0] = random.nextInt(mapX) * 32;
        output[1] = random.nextInt(mapY) * 32;


        return output;
    }

    public void removeItemCount() {
        removeItemCount(1);
    }

    public void removeItemCount(int i) {
        itemCount -= i;
    }

    public Actor getBackgroundActor() {
        return backgroundActor;
    }


    /** Class to handle touching/clicking of items on levels.  */
    class ItemClickListener extends ClickListener {
        Item owner;

        ItemClickListener(Item owner) {
            this.owner = owner;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            boolean outcome = gameInstance.gameState.getInventory().add(owner);
            if (outcome) {
                removeItemCount();
                owner.remove();
            }
            return true;
        }
    }

}
