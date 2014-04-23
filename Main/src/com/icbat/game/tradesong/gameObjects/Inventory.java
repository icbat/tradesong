package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int maxSize = 18;
    private ArrayList<String> items = new ArrayList<String>(maxSize);
    private Integer money = 0;

    public Inventory() {}

    public boolean addItem(String newItem) {
        if (canAdd()) {
            items.add(newItem);
            Gdx.app.log("inventory added", newItem);
            return true;
        } else {
            Gdx.app.debug("couldn't add", newItem);
            return false;
        }
    }

    public boolean canAdd() {
        return items.size() < maxSize;
    }

    public Integer getMoney() {
        return money;
    }

    public void addMoney(Integer newInput) {
        money += newInput;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "money=" + money +
                ", items=" + items +
                '}';
    }

    public List<String> getEditableInventory() {
        return items;
    }

    public List<String> getMatchList(List<String> itemsToMatch) {
        List<String> matchesFound = new ArrayList<String>();
        List<String> copyOfInput = new ArrayList<String>(itemsToMatch);
        List<String> copyOfInventory = new ArrayList<String>(items);

        for (String name : copyOfInventory) {
            if (copyOfInput.contains(name)) {
                matchesFound.add(name);
                copyOfInput.remove(name);
            }
        }

        return matchesFound;

    }
}
