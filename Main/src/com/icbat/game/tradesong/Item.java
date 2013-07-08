package com.icbat.game.tradesong;

import java.util.ArrayList;

/**
 * Basic class of all items/objects
 *
 * Originally had entity-component design pattern in mind.
 * decided to go with this instead; consider extracting out later
 * */
public class Item {

    private String itemName;


    public Item(String itemName) {
        this.itemName = itemName;

    }
    public String getItemName() {
        return itemName;
    }
}
