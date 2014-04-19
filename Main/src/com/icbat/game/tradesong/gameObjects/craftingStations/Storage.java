package com.icbat.game.tradesong.gameObjects.craftingStations;

public class Storage extends BaseCraftingStation {

    public Storage() {}
    public Storage(String name) {
        this();
        this.stationName = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }

    @Override
    public String process(String processedItem) {
        this.readyForOutput.addAll(this.inputs);
        this.inputs.clear();
        return processedItem;
    }

    public void add(String itemName) {
        this.readyForOutput.add(itemName);
    }

}
