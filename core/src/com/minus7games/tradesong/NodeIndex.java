package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.Set;
import java.util.TreeSet;

/***/
public class NodeIndex {
    private final Set<Node> allTheItems = new TreeSet<Node>();
    public NodeIndex(FileHandle nodesDotJson) {
        Gdx.app.debug("Node Index Init", "reading from file " + nodesDotJson.path());
        JsonReader reader = new JsonReader();
        JsonValue wholeThing = reader.parse(nodesDotJson);
        Gdx.app.debug("Nodes nodes found", String.valueOf(wholeThing.size));
        JsonValue nodeNode = wholeThing.child();
        Gdx.app.debug("Node Index Init", "reading in nodes");

        while (nodeNode != null) {
            allTheItems.add(Node.parseNode(nodeNode));
            nodeNode = nodeNode.next();
        }
        Gdx.app.debug("Node Index Init", "loaded "+ allTheItems.size()+" nodes (unique)");
    }

    public Node get(String internalName) {
        for (Node node : allTheItems) {
            if (node.getInternalName().equalsIgnoreCase(internalName)) {
                return node.copy();
            }
        }
        Gdx.app.error("Node not found with name", internalName);
        throw new IllegalArgumentException("Node not found with name:  " + internalName);
    }
}
