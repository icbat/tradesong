package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.gameObjects.craftingStations.AnythingToOne;
import com.icbat.game.tradesong.gameObjects.craftingStations.OneToOne;

import java.util.LinkedList;

public class Workshop {
    LinkedList<CraftingStation> orderedNodes = new LinkedList<CraftingStation>();

    public Workshop() { // TODO This is for debugging! Fix it!
        OneToOne oneToOne = new OneToOne("Smelter");
        oneToOne.inputToOutput.put("Ore", "Ingot");
        oneToOne.inputToOutput.put("Tomato", "Tomato Sauce");
        orderedNodes.add(oneToOne);

        OneToOne cutter = new OneToOne("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        orderedNodes.add(cutter);

        AnythingToOne scrapper = new AnythingToOne("Scrapper");
        scrapper.output = "Scrap";
        orderedNodes.add(scrapper);
    }

    public LinkedList<CraftingStation> getOrderedNodes() {
        return orderedNodes;
    }
}
