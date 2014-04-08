package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

/***/
public class ManyToOne implements CraftingStation {
    private String name;

    @Override
    public String getNodeName() {
        return this.name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return false;//TODO
    }
}
