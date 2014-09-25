package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** All the 'buildings' */
public class Node implements Displayable, Comparable<Node> {
    private final String internalName;
    private final String displayName;
    private final LinkedList<CraftingStep> possibleCrafts = new LinkedList<CraftingStep>();
    private final LinkedList<Item> inputs = new LinkedList<Item>();
    private final LinkedList<Item> outputs = new LinkedList<Item>();

    public Node(String internalName, String displayName, CraftingStep... craftingSteps) {
        this.displayName = displayName;
        this.internalName = internalName;
        Collections.addAll(possibleCrafts, craftingSteps);
    }

    public Node copy() {
        return new Node(this.internalName, this.displayName, this.possibleCrafts.toArray(new CraftingStep[possibleCrafts.size()]));
    }

    public static Node parseNode(JsonValue nodeNode) {
        String internalName = nodeNode.getString("internalName", "default internal name");
        Gdx.app.debug("parsing node: internal name", internalName);
        String displayName = nodeNode.getString("displayName", "default display name");
        Gdx.app.debug("parsing node: display name ", displayName);

        JsonValue craftingStepsNode = nodeNode.getChild("possibleCrafts");
        CraftingStep[] steps = CraftingStep.parseSteps(craftingStepsNode);

        return new Node(internalName, displayName, steps);
    }

    public String getInternalName() {
        return internalName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Item getNextOutput() {
        return outputs.remove(0);
    }

    public void sendInput(Item input) {
        inputs.add(input);
    }

    public List<Item> getInputCopy() {
        List<Item> copy = new LinkedList<Item>();
        copy.addAll(inputs);
        return copy;
    }


    public void act() {
        if (inputs.isEmpty()) {
            return;
        }

        Item item = inputs.remove(0);

        for (CraftingStep step : possibleCrafts) {
            item = step.craft(item);
        }

        outputs.add(item);
    }

    @Override
    public Actor getActor() {
        return null;
    }



    @Override
    public int compareTo(Node o) {
        return this.getInternalName().compareTo(o.getInternalName());
    }
}
