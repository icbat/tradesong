package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** All the 'buildings' */
public class Node implements Displayable, Comparable<Node> {
    private final String internalName;
    private final String displayName;
    /** All the possible crafting steps this node can use */
    private final ArrayList<CraftingStep> possibleCraftSteps = new ArrayList<CraftingStep>();
    private final ArrayList<CraftingStep> currentSteps = new ArrayList<CraftingStep>();
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
        Group mainTable = new Group();
        mainTable.setPosition(0,0);
        mainTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainTable.addActor(setupTopTable());
        mainTable.addActor(setupPossibleStepsTable());
        return mainTable;
    }

    private Table setupTopTable() {
        Table topTable = new Table(GameSkin.get());
        topTable.setFillParent(true);
        topTable.align(Align.top);
        topTable.add(getDisplayName());
        return topTable;
    }

    private Table setupPossibleStepsTable() {
        Table possibleNodes = new Table(GameSkin.get());
        possibleNodes.setFillParent(true);
        possibleNodes.align(Align.bottom);
        int maxColumns = 10;
        possibleNodes.add("Possible Crafting Steps").colspan(maxColumns).row();
        int i=1;
        for (CraftingStep step : this.possibleCraftSteps) {
            Gdx.app.debug("Showing crafting step", step.getDisplayName());
            possibleNodes.add(step.getActor()).pad(5);
            if (i++ >= maxColumns) {
                possibleNodes.row();
            }
        }
        return possibleNodes;
    }

    @Override
    public int compareTo(Node o) {
        return this.getInternalName().compareTo(o.getInternalName());
    }

    /** Adds the step to shop if and only if it is a possible step for this. */
    public void addToWorkflow(CraftingStep validCraftingStep) {
        if (possibleCraftSteps.contains(validCraftingStep)) {
            currentSteps.add(validCraftingStep);
        }
    }

    public ArrayList<CraftingStep> getCurrentSteps() {
        return currentSteps;
    }
}
