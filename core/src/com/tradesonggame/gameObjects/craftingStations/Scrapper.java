package com.tradesonggame.gameObjects.craftingStations;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tradesonggame.Tradesong;

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

    @Override
    public Table getProcessDisplayTable() {
        Table table = new Table(Tradesong.uiStyles);
        table.add("Anything");
        table.add(">").space(5);
        table.add(Tradesong.items.getItem("Scrap"));
        return table;

    }
}
