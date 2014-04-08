package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.icbat.game.tradesong.gameObjects.CraftingNode;
import com.icbat.game.tradesong.gameObjects.craftingStations.AnythingToOneNode;
import com.icbat.game.tradesong.gameObjects.craftingStations.OneToOneNode;

import java.util.ArrayList;

public class CraftingNodes {
    public static final String NODES_JSON = "nodes.json";
    private final ArrayList<CraftingNode> nodes = new ArrayList<CraftingNode>();
    private CraftingNodes() {}

    public static CraftingNodes parseFromJson() {
        // TODO REMOVE
        setupFirst();

        CraftingNodes craftingNodes;
        try {
            Json json = new Json();
            craftingNodes = json.fromJson(CraftingNodes.class, Gdx.files.internal(NODES_JSON));
            return craftingNodes;
        } catch (SerializationException se) {
            Gdx.app.error("Missing or malformed file: "+NODES_JSON, "Cannot read crafting nodes");
            Gdx.app.debug("", "", se);
            return null;
        }
    }

    private static void setupFirst() {
        CraftingNodes nodes = new CraftingNodes();

        OneToOneNode oneToOne = new OneToOneNode("Smelter");
        oneToOne.inputToOutput.put("Ore", "Ingot");
        oneToOne.inputToOutput.put("Tomato", "Tomato Sauce");
        nodes.nodes.add(oneToOne);

        OneToOneNode cutter = new OneToOneNode("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        nodes.nodes.add(cutter);

        AnythingToOneNode scrapper = new AnythingToOneNode("Scrapper");
        scrapper.output = "Scrap";
        nodes.nodes.add(scrapper);

        Json json = new Json();
        Gdx.app.log("NODES", json.prettyPrint(nodes));
    }

    public ArrayList<CraftingNode> getNodesCopy() {
        return new ArrayList<CraftingNode>(nodes);
    }
}
