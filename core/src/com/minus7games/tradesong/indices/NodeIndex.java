package com.minus7games.tradesong.indices;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.minus7games.tradesong.workshops.Node;

import java.util.Set;
import java.util.TreeSet;

/***/
public class NodeIndex {
    private static final Set<Node> allTheNodes = new TreeSet<Node>();
    public NodeIndex(FileHandle nodesDotJson) {
        Gdx.app.debug("Node Index Init", "reading from file " + nodesDotJson.path());
        JsonReader reader = new JsonReader();
        JsonValue wholeThing = reader.parse(nodesDotJson);
        Gdx.app.debug("Nodes nodes found", String.valueOf(wholeThing.size));
        JsonValue nodeNode = wholeThing.child();
        Gdx.app.debug("Node Index Init", "reading in nodes");

        while (nodeNode != null) {
            allTheNodes.add(Node.parseNode(nodeNode));
            nodeNode = nodeNode.next();
        }
        Gdx.app.log("Node Index Init", "loaded "+ allTheNodes.size()+" nodes (unique)");
    }

    public static Node get(String internalName) {
        if (allTheNodes.isEmpty()) {
            throw new IllegalStateException("No nodes have been read!");
        }
        for (Node node : allTheNodes) {
            if (node.getInternalName().equalsIgnoreCase(internalName)) {
                return node.copy();
            }
        }
        Gdx.app.error("Node not found with name", internalName);
        throw new IllegalArgumentException("Node not found with name:  " + internalName);
    }

    public static Set<Node> getAllNodes() {
        return allTheNodes;
    }
}
