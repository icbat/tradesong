package com.icbat.game.tradesong.gameObjects.craftingStations;

public class Scrapper extends BaseCraftingStation {
    public String output;

    public Scrapper() {}
    public Scrapper(String name) {
        this.stationName = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }
    @Override
    public String process(String processedItem) {
        return "Scrap";
    }
}
