package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;

import java.util.HashMap;
import java.util.Map;

public class Processor extends BaseCraftingStation {
    public final HashMap<String, String> inputToOutput = new HashMap<String, String>();

    public Processor() {}
    public Processor(String craftingNodeName) {
        this();
        this.stationName = craftingNodeName;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return inputToOutput.containsKey(inputItemName);
    }

    @Override
    public String process(String processedItem) {
        return inputToOutput.get(processedItem);
    }

    @Override
    public Table getProcessDisplayTable() {
        Table table = new Table(Tradesong.uiStyles);
        for (Map.Entry<String, String> entry : inputToOutput.entrySet()) {
            table.add(Tradesong.items.getItem(entry.getKey()));
            table.add(">").space(5);
            table.add(Tradesong.items.getItem(entry.getValue())).row();
        }
        return table;
    }
}
