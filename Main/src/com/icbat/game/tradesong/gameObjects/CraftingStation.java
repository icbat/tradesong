package com.icbat.game.tradesong.gameObjects;

public interface CraftingStation {
    public String getNodeName();
    public boolean isValidInput(String inputItemName);
}
