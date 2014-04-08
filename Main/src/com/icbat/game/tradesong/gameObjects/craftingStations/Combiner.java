package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

import java.util.ArrayList;
import java.util.HashMap;

public class Combiner implements CraftingStation {
    private String name;
    private HashMap<ArrayList<String>, String> inputToOutput = new HashMap<ArrayList<String>, String>();

    public Combiner() { name = ""; }

    public Combiner(String name) {
        this.name = name;
    }

    @Override
    public String getNodeName() {
        return this.name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return false;//TODO
    }
}
