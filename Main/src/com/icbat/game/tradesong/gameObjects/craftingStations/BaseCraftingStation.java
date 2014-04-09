package com.icbat.game.tradesong.gameObjects.craftingStations;

import java.util.LinkedList;

public abstract class BaseCraftingStation {

    String name = "";
    LinkedList<String> itemsBeingProcessed = new LinkedList<String>();
    LinkedList<String> readyForOutput = new LinkedList<String>();

    public final String getStationName() {
        return name;
    }

    public final void ingest(String inputItemName) {
        if (isValidInput(inputItemName)) {
            itemsBeingProcessed.addLast(inputItemName);
        }
    }

    public final String extractOutput() {
        return readyForOutput.removeFirst();
    }

    public abstract boolean isValidInput(String inputItemName);
    public abstract void process();
}
