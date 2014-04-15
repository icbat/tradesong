package com.icbat.game.tradesong.gameObjects.craftingStations;

import java.util.HashMap;

public class Processor extends BaseCraftingStation {
    public final HashMap<String, String> inputToOutput = new HashMap<String, String>();

    public Processor() {}
    public Processor(String craftingNodeName) {
        this();
        this.name = craftingNodeName;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return inputToOutput.containsKey(inputItemName);
    }

    @Override
    public void process() {

    }
}
