package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Inventory with persistence
 */
public class Inventory {
    private int maxSize = 18;
    private List<Item> items = new ArrayList<Item>(maxSize);
    private Integer money = 0;

    public Inventory() {}

    public boolean addItem(Item newItem) {
        if (canAdd()) {
            items.add(new Item(newItem));
            return true;
        } else {
            Gdx.app.debug("couldn't add", newItem.toString());
            return false;
        }
    }

    /**
     * Attempts to remove the item. Will return payload regardless of removal's success.
     *
     * @return the same payload you gave it.
     * */
    public Item takeOutItem(Item payload) {
        items.remove(payload);
        return payload;
    }

    public List<Item> getCopyOfInventory() {
        return new ArrayList<Item>(items);
    }

    public boolean canAdd() {
        return items.size() < maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Sorts inventory based on item name
     * */
    public void sort() {
        Collections.sort(items);
    }

    public Integer getMoney() {
        return money;
    }

    /**
     * I'm lazy, use this for add and subtract.
     * */
    public void addMoney(Integer newInput) {
        money += newInput;
    }

    public int getSlotsFree() {
        return maxSize - items.size();
    }
}
