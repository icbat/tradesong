package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

public class Scrapper implements CraftingStation {
    // TODO SCOPE
    public String craftingStationName;
    public String output;

    public Scrapper() {craftingStationName = ""; }
    public Scrapper(String craftingStationName) {
        this.craftingStationName = craftingStationName;
    }

    @Override
    public String getNodeName() {
        return this.craftingStationName;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }
}
