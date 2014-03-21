package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Inventory with persistence
 */
public class Inventory {
    private int maxSize = 18;
    private ArrayList<String> items = new ArrayList<String>(maxSize);
    private Integer money = 0;

    public Inventory() {}

    public boolean addItem(String newItem) {
        if (canAdd()) {
            items.add(newItem);
            return true;
        } else {
            Gdx.app.debug("couldn't add", newItem);
            return false;
        }
    }

    public ArrayList<String> getCopyOfInventory() {
        return new ArrayList<String>(items);
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

    @Override
    public String toString() {
        return "Inventory{" +
                "money=" + money +
                ", items=" + items +
                '}';
    }
}
