package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.gameObjects.craftingStations.Processor;
import com.icbat.game.tradesong.gameObjects.craftingStations.Scrapper;

import java.util.LinkedList;

public class Workshop {
    LinkedList<CraftingStation> orderedNodes = new LinkedList<CraftingStation>();

    public Workshop() { // TODO This is for debugging! Fix it!
        Processor processor = new Processor("Smelter");
        processor.inputToOutput.put("Ore", "Ingot");
        processor.inputToOutput.put("Tomato", "Tomato Sauce");
        orderedNodes.add(processor);

        Processor cutter = new Processor("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        orderedNodes.add(cutter);

        Scrapper scrapper = new Scrapper("Scrapper");
        scrapper.output = "Scrap";
        orderedNodes.add(scrapper);
    }

    public LinkedList<CraftingStation> getOrderedNodes() {
        return orderedNodes;
    }
}
