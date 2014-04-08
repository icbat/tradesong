package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

import java.util.HashMap;

public class Processor implements CraftingStation {
    public String craftingNodeName;
    public final HashMap<String, String> inputToOutput;       // TODO tighter scope

    public Processor() {
        inputToOutput = new HashMap<String, String>();
        craftingNodeName = "";
    }

    public Processor(String craftingNodeName) {
        this();
        this.craftingNodeName = craftingNodeName;
    }

    @Override
    public String getNodeName() {
        return craftingNodeName;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return inputToOutput.containsKey(inputItemName);
    }
}
