package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingNode;

public class AnythingToOneNode implements CraftingNode {
    // TODO SCOPE
    public String craftingStationName;
    public String output;

    public AnythingToOneNode(String craftingStationName) {
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
