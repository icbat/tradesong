package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

/***/
public class Storage implements CraftingStation {
    private String name;

    public Storage() {
        name = "";
    }

    public Storage(String name) {
        this();
        this.name = name;
    }

    @Override
    public String getNodeName() {
        return this.name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }
}
