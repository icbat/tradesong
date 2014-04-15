package com.icbat.game.tradesong.gameObjects.craftingStations;

public class Scrapper extends BaseCraftingStation {
    public String output;

    public Scrapper() {}
    public Scrapper(String name) {
        this.name = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }
    @Override
    public void process() {
        this.itemsBeingProcessed.removeFirst();
        this.readyForOutput.add("Scrap");
    }
}
