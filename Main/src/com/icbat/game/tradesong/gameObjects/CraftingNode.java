package com.icbat.game.tradesong.gameObjects;

public interface CraftingNode {
    public String getNodeName();
    public boolean isValidInput(String inputItemName);
}
