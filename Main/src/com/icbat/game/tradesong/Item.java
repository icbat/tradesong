package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Basic class of all items/objects
 *
 * Abstract representation of the item. The actual "tangible" class is StackedItem.
 * @see StackedItem
 * */
public class Item extends Image {

    private String itemName;
    private String description;
    private int maxStack = 9;
    private int rarity = -1;

    public static final int ICON_SIZE = 34;

    public Item(String itemName, String description, Texture texture,  int spriteX, int spriteY, int rarity, int maxStack) {
        super( new TextureRegion(texture, spriteX, spriteY, ICON_SIZE, ICON_SIZE) );
        this.itemName = itemName;
        this.description = description;
        this.maxStack = maxStack;
        this.rarity = rarity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getRarity() {
        return rarity;
    }
}
