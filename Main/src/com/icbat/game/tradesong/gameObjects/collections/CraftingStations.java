package com.icbat.game.tradesong.gameObjects.collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.gameObjects.craftingStations.Processor;
import com.icbat.game.tradesong.gameObjects.craftingStations.Scrapper;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;

import java.util.ArrayList;

public class CraftingStations {
    public static final String NODES_JSON = "nodes.json";
    private final ArrayList<BaseCraftingStation> nodes = new ArrayList<BaseCraftingStation>();
    private CraftingStations() {}

    public static CraftingStations parseFromJson() {

        setupFirst();// TODO REMOVE

        CraftingStations craftingStations;
        try {
            Json json = new Json();
            craftingStations = json.fromJson(CraftingStations.class, Gdx.files.internal(NODES_JSON));
            return craftingStations;
        } catch (SerializationException se) {
            Gdx.app.error("Missing or malformed file: "+NODES_JSON, "Cannot read crafting nodes");
            Gdx.app.debug("", "", se);
            return null;
        }
    }

    private static void setupFirst() {
        CraftingStations nodes = new CraftingStations();

        Storage inputChest = new Storage("Input Chest");
        inputChest.iconX = 8;
        inputChest.iconY = 29;
        inputChest.description = "Where the items go in";
        nodes.nodes.add(inputChest);

        Processor processor = new Processor("Smelter");
        processor.inputToOutput.put("Ore", "Ingot");
        processor.inputToOutput.put("Tomato", "Tomato Sauce");
        processor.description = "Processes with the power of heat!";
        processor.iconX = 15;
        processor.iconY = 17;

        nodes.nodes.add(processor);

        Processor cutter = new Processor("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.description = "Chops, slices, etc.";
        cutter.iconX = 1;
        cutter.iconY = 10;
        nodes.nodes.add(cutter);

        Scrapper scrapper = new Scrapper("Scrapper");
        scrapper.description = "Turns anything in to junk. Because it's a cannon.";
        scrapper.output = "Scrap";
        scrapper.iconX = 9;
        scrapper.iconY = 29;
        nodes.nodes.add(scrapper);

        Storage outputChest = new Storage("Output Chest");
        outputChest.description = "Where the output is stored";
        outputChest.iconX = 7;
        outputChest.iconY = 29;
        nodes.nodes.add(outputChest);

        Json json = new Json();
        Gdx.app.log("NODES", json.prettyPrint(nodes));
    }

    public ArrayList<BaseCraftingStation> getNodesCopy() {
        return new ArrayList<BaseCraftingStation>(nodes);
    }
}
