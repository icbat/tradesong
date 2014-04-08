package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingStation;

import java.util.ArrayList;
import java.util.HashMap;

/***/
public class ManyToOne implements CraftingStation {
    private String name;
    private HashMap<ArrayList<String>, String> inputToOutput = new HashMap<ArrayList<String>, String>();

    public ManyToOne(String name) {
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
