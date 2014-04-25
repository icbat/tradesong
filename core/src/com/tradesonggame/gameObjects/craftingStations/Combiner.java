package com.tradesonggame.gameObjects.craftingStations;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tradesonggame.Tradesong;

import java.util.ArrayList;
import java.util.HashMap;

public class Combiner extends BaseCraftingStation {
    public HashMap<ArrayList<String>, String> inputToOutput = new HashMap<ArrayList<String>, String>();

    public Combiner() {}
    public Combiner(String name) {
        this();
        this.stationName = name;
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
    protected String process(String processedItem) {
        return null; //TODO impl
    }

    @Override
    public Table getProcessDisplayTable() {
        Table table = new Table(Tradesong.uiStyles);
//        for (Map.Entry<ArrayList<String>, String> entry : inputToOutput.entrySet()) {
//            ArrayList<String> inputs = entry.getKey();
//            for (String inputName : inputs) {
//                table.add(Tradesong.items.getItem(inputName));
//            }
//            table.add(">").spaceLeft(5).spaceRight(5);
//            table.add(Tradesong.items.getItem(entry.getValue())).row();
//        }
        return table;
    }
}
