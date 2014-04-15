package com.icbat.game.tradesong.gameObjects.craftingStations;

import java.util.ArrayList;
import java.util.HashMap;

public class Combiner extends BaseCraftingStation {
    private HashMap<ArrayList<String>, String> inputToOutput = new HashMap<ArrayList<String>, String>();

    public Combiner() {}
    public Combiner(String name) {
        this();
        this.name = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        for (ArrayList<String> keyList : inputToOutput.keySet()) {
            for (String key : keyList) {
                if (key.equalsIgnoreCase(inputItemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void process() {

    }
}
