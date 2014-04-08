package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingNode;

/***/
public class StorageNode implements CraftingNode {
    private String name;

    public StorageNode() {
        name = "";
    }

    public StorageNode(String name) {
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
