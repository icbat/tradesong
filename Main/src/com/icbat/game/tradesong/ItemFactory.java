package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 *
 * What needs to happen:
 *
 * Given a spritesheet, find a random item (these are known by name)
 *
 * ***/

public class ItemFactory {
    public static final int ITEM_DIMENSIONS = 34;
    public static final int SPRITE_SIZE = 34;

    private ItemFactory() {}

    public static Item makeItem(Texture spritesheet, String itemName) {
        int[] coords = itemCoordLookup(itemName);
        TextureRegion region = new TextureRegion(spritesheet, coords[0], coords[1] , ITEM_DIMENSIONS, ITEM_DIMENSIONS);
        return new Item(itemName, itemDescriptionLookup(itemName), region);

    }

    public static Item makeRandomItem(Texture spritesheet, String[] possibleItems) {
        int i = new Random().nextInt(possibleItems.length);
        return makeItem(spritesheet, possibleItems[i]);
    }

    /**
     * //TODO Hardcoding. Replace ASAP
     *
     * @return array of Integers with x in the 0 pos and y in the pos
     * */
    public static int[] itemCoordLookup(String itemName) {
        int[] output = new int[2];

        output[0] = 0;
        output[1] = 0;

        if (itemName.equals("blackberry")) {
            output[0] = 0;
            output[1] = 0;
        }
        if (itemName.equals("ore")) {
            output[0] = 0;
            output[1] = 17;
        }
        if (itemName.equals("wood")) {
            output[0] = 6;
            output[1] = 18;
        }

        output[0] *= SPRITE_SIZE;
        output[1] *= SPRITE_SIZE;

        return output;
    }

    /**
     * //TODO Hardcoding. Replace ASAP
     *
     * @return item description from name
     * */

    public static String itemDescriptionLookup(String itemName) {
        String description = "";

        if (itemName.equals("blackberry"))
            description = "A clump of Blackberries. But where's the bush?";
        if (itemName.equals("ore"))
            description = "Some rock with little glinting bits.";
        if (itemName.equals("wood"))
            description = "It's a log!";

        return description;
    }


}
