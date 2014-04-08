package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingNode;

/***/
public class ManyToOneNode implements CraftingNode {
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
