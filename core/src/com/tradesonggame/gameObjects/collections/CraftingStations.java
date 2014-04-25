package com.tradesonggame.gameObjects.collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.tradesonggame.gameObjects.craftingStations.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CraftingStations {
    public static final String NODES_JSON = "allCraftingStations.json";
    private final ArrayList<BaseCraftingStation> nodes = new ArrayList<BaseCraftingStation>();
    private CraftingStations() {}

    public static CraftingStations parseFromJson() {

        printStationsToConsole();

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

    public BaseCraftingStation getStation(String stationName) {
        for (BaseCraftingStation station : this.nodes) {
            if (station.getStationName().equalsIgnoreCase(stationName)) {
                return station;
            }
        }
        Gdx.app.error("No station known by name", stationName);
        return null;
    }

    /**
     * If the JSON ever gets out of sync of from the classes, this can be used to match them up (provided everything in it is setup correctly)
     * */
    private static void printStationsToConsole() {
        CraftingStations nodes = new CraftingStations();

        Storage inputChest = new Storage("Input Chest");
        inputChest.iconX = 8;
        inputChest.iconY = 29;
        inputChest.description = "Where the items go in";
        nodes.nodes.add(inputChest);

        Processor smelter = new Processor("Smelter");
        smelter.inputToOutput.put("Ore", "Ingot");
        smelter.inputToOutput.put("Gleaming Ore", "Better Ingot");
        smelter.inputToOutput.put("Encrusted Ore", "Awesome Ingot");
        smelter.inputToOutput.put("Tomato", "Tomato Sauce");
        smelter.inputToOutput.put("Wood", "Charcoal");
        smelter.inputToOutput.put("Better Wood", "Charcoal");
        smelter.inputToOutput.put("Awesome Wood", "Charcoal");
        smelter.description = "Processes with the power of heat!";
        smelter.iconX = 15;
        smelter.iconY = 17;
        nodes.nodes.add(smelter);

        Combiner assembler = new Combiner("Assembler");
        ArrayList<String> inputs = new ArrayList<String>();
        inputs.add("Better Scrap");
        inputs.add("Better Scrap");
        inputs.add("Better Scrap");
        assembler.inputToOutput.put(inputs, "Best Scrap");
//        assembler.inputToOutput.put(new ArrayList<String>(Arrays.asList("Better Scrap, Better Scrap, Better Scrap".split(","))), "Best Scrap");
//        assembler.inputToOutput.put(new ArrayList<String>(Arrays.asList("Scrap, Scrap, Scrap".split(","))), "Scrap");
//        assembler.inputToOutput.put(new ArrayList<String>(Arrays.asList("Ingot, Wood".split(","))), "Dagger");
//        assembler.inputToOutput.put(new ArrayList<String>(Arrays.asList("Better Ingot, Better Ingot, Better Wood".split(","))), "Sword");
        assembler.iconX = 14;
        assembler.iconY = 4;
        assembler.description = "How the coolest of kids make something out of nothing.";
        nodes.nodes.add(assembler);

        Combiner mixer = new Combiner("Mixer");
        mixer.inputToOutput.put(new ArrayList<String>(Arrays.asList("Black Ink, Charcoal, Charcoal")), "True-Black Ink");
        mixer.iconX = 2;
        mixer.iconY = 22;
        mixer.description = "Mixes liquids with the awesome power of stirring.";
        nodes.nodes.add(mixer);

        Processor masher = new Processor("M.A.S.H");
        masher.inputToOutput.put("Blackberry", "Black Ink");
        masher.inputToOutput.put("Tomato", "Crimson Ink");
        masher.inputToOutput.put("Crystallized Goodie", "Glittering Shards");
        masher.description = "Must Apply Solid Hammer";
        masher.iconX = 14;
        masher.iconY = 9;
        nodes.nodes.add(masher);

        Scrapper scrapper = new Scrapper("Scrapper");
        scrapper.description = "Simply load the cannon with anything, receive scraps.";
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
        Gdx.app.debug("NODES", json.prettyPrint(nodes));
    }

    public ArrayList<BaseCraftingStation> getNodesCopy() {
        return new ArrayList<BaseCraftingStation>(nodes);
    }
}
