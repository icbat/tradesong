package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;
import com.minus7games.tradesong.Displayable;
import com.minus7games.tradesong.workshops.actors.NodeActor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** All the 'buildings' */
public class Node implements Displayable, Comparable<Node> {
    private final String internalName;
    private final String displayName;
    /** All the possible crafting steps this node can use */
    private final ArrayList<CraftingStep> possibleCraftSteps = new ArrayList<CraftingStep>();
    private final ArrayList<CraftingStepInUse> currentSteps = new ArrayList<CraftingStepInUse>();
    private final CraftingStep inputBuffer = new CraftingStep("Incoming Items");
    private final CraftingStep outputBuffer = new CraftingStep("Outgoing Items");

    public Node(String internalName, String displayName, CraftingStep... craftingSteps) {
        this.displayName = displayName;
        this.internalName = internalName;
        Collections.addAll(possibleCraftSteps, craftingSteps);
    }

    public Node(String internalName, String displayName, List<CraftingStep> craftingSteps) {
        this.displayName = displayName;
        this.internalName = internalName;
        this.possibleCraftSteps.addAll(craftingSteps);
    }

    public Node copy() {
        return new Node(this.internalName, this.displayName, this.possibleCraftSteps);
    }

    public static Node parseNode(JsonValue nodeNode) {
        String internalName = nodeNode.getString("internalName", "default internal name");
        Gdx.app.debug("parsing node: internal name", internalName);
        String displayName = nodeNode.getString("displayName", "default display name");
        Gdx.app.debug("parsing node: display name ", displayName);

        JsonValue craftingStepsNode = nodeNode.getChild("possibleCraftSteps");
        List<CraftingStep> steps = CraftingStep.parseSteps(craftingStepsNode);

        return new Node(internalName, displayName, steps);
    }

    public String getInternalName() {
        return internalName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Actor getActor() {
        return new NodeActor(this);
    }

    @Override
    public int compareTo(Node o) {
        return this.getInternalName().compareTo(o.getInternalName());
    }

    /** Adds the step to shop if and only if it is a possible step for this. */
    public void addToWorkflow(CraftingStep step, float x, float y) {
        Gdx.app.log(displayName + " adding to workflow", step.getDisplayName());
        if (possibleCraftSteps.contains(step)) {
            currentSteps.add(new CraftingStepInUse(step, x, y));
        }
    }

    public List<CraftingStep> getCurrentStepsUnwrapped() {
        List<CraftingStep> steps = new ArrayList<CraftingStep>();
        for (CraftingStepInUse inUse : currentSteps) {
            steps.add(inUse.get());
        }
        return steps;
    }

    public ArrayList<CraftingStepInUse> getCurrentSteps() {
        return currentSteps;
    }

    public ArrayList<CraftingStep> getPossibleCraftSteps() {
        return possibleCraftSteps;
    }

    public CraftingStep getInputBuffer() {
        return inputBuffer;
    }

    public CraftingStep getOutputBuffer() {
        return outputBuffer;
    }

    public void setCurrentSteps(Collection<CraftingStepInUse> currentSteps) {
        this.currentSteps.clear();
        this.currentSteps.addAll(currentSteps);
    }
}
