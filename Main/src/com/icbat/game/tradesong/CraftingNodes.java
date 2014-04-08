package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;

import java.util.ArrayList;

public class CraftingNodes {
    public static final String NODES_JSON = "nodes.json";
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private CraftingNodes() {}

    public static CraftingNodes parseFromJson() {
        CraftingNodes craftingNodes;
        try {
            Json json = new Json();
            craftingNodes = json.fromJson(CraftingNodes.class, Gdx.files.internal(NODES_JSON));
            return craftingNodes;
        } catch (SerializationException se) {
            Gdx.app.error("Missing or malformed file: "+NODES_JSON, "Cannot read crafting nodes", se);
            return null;
        }
    }

    private class Node {

    }
}
