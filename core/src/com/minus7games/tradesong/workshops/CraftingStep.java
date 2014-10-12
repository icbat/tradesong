package com.minus7games.tradesong.workshops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.JsonValue;
import com.minus7games.tradesong.Displayable;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.Item;
import com.minus7games.tradesong.indices.ItemIndex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** There's one stipulation, no duplicate inputs! */
public class CraftingStep implements Displayable, Comparable<CraftingStep> {

    /** Should not be modified. A list of the inputs this Step accepts */
    private final ArrayList<Item> validInput = new ArrayList<Item>();
    /** Should not be modified. A list of the outputs to release once this step is completed */
    private final ArrayList<Item> outputs = new ArrayList<Item>();
    private final LinkedList<Item> inputBuffer = new LinkedList<Item>();
    private final LinkedList<Item> outputBuffer = new LinkedList<Item>();
    private final String displayName;
    private float x = 0;
    private float y = 0;

    public CraftingStep(String displayName, List<Item> inputs, List<Item> outputs) {
        this.displayName = displayName;
        this.outputs.addAll(outputs);
        this.validInput.addAll(inputs);
    }

    /** Creates a step with no inputs or outputs, useful as a buffer that acts like any other step. */
    public CraftingStep(String displayName) {
        this.displayName = displayName;
    }

    /** @return a new Crafting step with the same ins and outs and names. Will not set X and Y or the buffers */
    public CraftingStep getCopy() {
        return new CraftingStep(displayName, validInput, outputs);
    }

    /** Gives an item to this step. If the step can use it, it is stored in the step for processing; otherwise it's queued in to the output buffer */
    public void send(Item input) {
        if (validInput.contains(input)) {
            if (!inputBuffer.contains(input)) {
                inputBuffer.add(input);
                return;
            }
        }
        outputBuffer.addLast(input);
    }

    /** Tries to process inputs in the input buffer to create outputs. */
    public void act() {
        if (canCraft()) {
            inputBuffer.clear();
            for (Item out : outputs) {
                outputBuffer.addLast(out);
            }
        }
    }

    /** @return whatever's first in the output buffer, or null if it's empty */
    public Item getNextOutput() {
        if (outputBuffer.isEmpty()) {
            return null;
        }
        return outputBuffer.removeFirst();
    }

    /** How can such a simple check be so convoluted? */
    private boolean canCraft() {
        if (inputBuffer.size() == validInput.size()) {
            for (Item input : inputBuffer) {
                if (!validInput.contains(input)) {
                     return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Actor getActor() {
        final Label label = new Label(this.displayName, GameSkin.get());
        label.setName(String.valueOf(Math.random()));
        return label;
    }

    public static List<CraftingStep> parseSteps(JsonValue craftingStepsNode) {
        Gdx.app.debug("parsing crafting steps", "");
        List<CraftingStep> stepsFound = new ArrayList<CraftingStep>();
        JsonValue currentStep = craftingStepsNode;

        while (currentStep != null) {
            List<Item> inputs = readItemChildren(currentStep.getChild("validInput"));
            List<Item> outputs = readItemChildren(currentStep.getChild("outputs"));
            String displayName = currentStep.getString("displayName", "SOME DEFAULT VALUE");

            currentStep = currentStep.next();
            stepsFound.add(new CraftingStep(displayName, inputs, outputs));
        }

        Gdx.app.debug("Crafting step parsing", "found " + stepsFound.size() + " steps");
        return stepsFound;
    }

    private static List<Item> readItemChildren(JsonValue node) {
        if (node == null) {
            return new ArrayList<Item>();
        }
        List<Item> items = new ArrayList<Item>();
        JsonValue itemJson = node.child();
        while (itemJson != null) {
            String itemName = itemJson.asString();
            items.add(ItemIndex.get(itemName));
            itemJson = itemJson.next();
        }
        return items;
    }

    public ArrayList<Item> getValidInput() {
        return validInput;
    }

    public ArrayList<Item> getOutputs() {
        return outputs;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean acceptsInput() {
        return !validInput.isEmpty();
    }

    public boolean acceptsInput(ArrayList<Item> outputs) {
        // TODO
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(CraftingStep o) {
        final float otherX = o.getX();
        if (x < otherX) {
            return -1;
        }
        if (x > otherX) {
            return 1;
        }
        return 0;

    }

    @Override
    public String toString() {
        return "CraftingStep{" +
                "displayName='" + displayName + '\'' +
                ", x=" + x +
                '}';
    }
}
