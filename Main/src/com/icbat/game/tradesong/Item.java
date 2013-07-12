package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Basic class of all items/objects
 *
 * Originally had entity-component design pattern in mind.
 * decided to go with this instead; consider extracting out later
 * */
public class Item extends Image {

    private String itemName;
    private String description;
    private TextureRegion region;


    public Item(String itemName, String description, TextureRegion region) {
        this.itemName = itemName;
        this.region = region;
        this.description = description;

    }
    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }
}
