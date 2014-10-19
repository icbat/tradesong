package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;
import com.minus7games.tradesong.Displayable;
import com.minus7games.tradesong.workshops.scene2d.NodeActor;

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
    private final ArrayList<CraftingStep> currentSteps = new ArrayList<CraftingStep>();
    private final ArrayList<StepLink> links = new ArrayList<StepLink>();
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
        if (possibleCraftSteps.contains(step)) {
            CraftingStep stepBeingAdded = step.getCopy();
            stepBeingAdded.setPosition(x, y);
            currentSteps.add(stepBeingAdded);
            linkAllSteps();
        }
    }

    public void linkAllSteps() {
        this.links.clear();
        Collections.sort(currentSteps);
        for (CraftingStep stepBeingAdded : currentSteps) {
            if (stepBeingAdded.acceptsAnyInput()) {
                linkThisInput(stepBeingAdded);
            }
            linkThisOutput(stepBeingAdded);
        }
    }

    private void linkThisInput(CraftingStep stepBeingAdded) {
        final List<CraftingStep> stepsToTheLeft = currentSteps.subList(0, currentSteps.indexOf(stepBeingAdded));
        Gdx.app.debug("Steps to the left", stepsToTheLeft.toString());
        for (CraftingStep existingStep : stepsToTheLeft) {
            if (stepBeingAdded.acceptsInputs(existingStep.getOutputs())) {
                links.add(new StepLink(existingStep, stepBeingAdded));
                return;
            }
        }
        links.add(new StepLink(inputBuffer, stepBeingAdded));
    }

    private void linkThisOutput(CraftingStep stepBeingAdded) {
        final List<CraftingStep> stepsToTheRight = currentSteps.subList(currentSteps.indexOf(stepBeingAdded) + 1, currentSteps.size());
        Gdx.app.debug("Steps to the right", stepsToTheRight.toString());
        for (CraftingStep existingStep : stepsToTheRight) {
            if (existingStep.acceptsInputs(stepBeingAdded.getOutputs())) {
                links.add(new StepLink(stepBeingAdded, existingStep));
                return;
            }
        }
        links.add(new StepLink(stepBeingAdded, outputBuffer));
    }

    public ArrayList<CraftingStep> getCurrentSteps() {
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

    public void setCurrentSteps(Collection<CraftingStep> currentSteps) {
        this.currentSteps.clear();
        this.currentSteps.addAll(currentSteps);
    }

    public ArrayList<StepLink> getLinks() {
        return links;
    }
}
