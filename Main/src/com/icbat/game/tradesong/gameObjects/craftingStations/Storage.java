package com.icbat.game.tradesong.gameObjects.craftingStations;

public class Storage extends BaseCraftingStation {

    public Storage() {}
    public Storage(String name) {
        this();
        this.name = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }

    @Override
    public void process() {
        this.readyForOutput.addAll(this.inputs);
        this.inputs.clear();
    }

    public void add(String itemName) {
        this.readyForOutput.add(itemName);
    }

}
