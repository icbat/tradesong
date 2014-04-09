package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.gameObjects.craftingStations.Processor;
import com.icbat.game.tradesong.gameObjects.craftingStations.Scrapper;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;

import java.util.LinkedList;

public class Workshop {
    LinkedList<BaseCraftingStation> orderedNodes = new LinkedList<BaseCraftingStation>();

    public Workshop() { // TODO This is for debugging! Remove it

        Storage inputChest = new Storage("Input Chest");
        inputChest.iconX = 8;
        inputChest.iconY = 29;
        orderedNodes.add(inputChest);

        Processor processor = new Processor("Smelter");
        processor.inputToOutput.put("Ore", "Ingot");
        processor.inputToOutput.put("Tomato", "Tomato Sauce");
        processor.iconX = 10;
        processor.iconY = 10;
        orderedNodes.add(processor);

        Processor cutter = new Processor("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.iconX = 15;
        cutter.iconY = 17;
        orderedNodes.add(cutter);

        Scrapper scrapper = new Scrapper("Scrapper");
        scrapper.output = "Scrap";
        scrapper.iconX = 9;
        scrapper.iconY = 29;
        orderedNodes.add(scrapper);

        Storage outputChest = new Storage("Output Chest");
        outputChest.iconX = 7;
        outputChest.iconY = 29;
        orderedNodes.add(outputChest);
    }

    public LinkedList<BaseCraftingStation> getOrderedNodes() {
        return orderedNodes;
    }
}
