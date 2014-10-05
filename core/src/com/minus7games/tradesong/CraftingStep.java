package com.minus7games.tradesong;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** There's one stipulation, no duplicate inputs! */
public class CraftingStep implements Displayable {

    /** Should not be modified. A list of the inputs this Step accepts */
    private final ArrayList<Item> validInput = new ArrayList<Item>();
    /** Should not be modified. A list of the outputs to release once this step is completed */
    private final ArrayList<Item> outputs = new ArrayList<Item>();
    private final LinkedList<Item> inputBuffer = new LinkedList<Item>();
    private final LinkedList<Item> outputBuffer = new LinkedList<Item>();

    public CraftingStep(List<Item> inputs, List<Item> outputs) {
        this.outputs.addAll(outputs);
        this.validInput.addAll(inputs);
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
        return null;
    }

    public static CraftingStep[] parseSteps(JsonValue craftingStepsNode) {
//        Gdx.app.debug("parsing crafting steps", "");
//        LinkedList<CraftingStep> stepsFound = new LinkedList<CraftingStep>();
//        JsonValue currentStep = craftingStepsNode;
//
//        while (currentStep != null) {
//            String inputInternalName = currentStep.getChild("validInput").asString();
//            String outputInternalName = currentStep.getChild("output").asString();
//            Gdx.app.debug("step found that turns", inputInternalName + " => " + outputInternalName);
//            currentStep = currentStep.next();
//            stepsFound.add(new CraftingStep(ItemIndex.get(outputInternalName), ItemIndex.get(inputInternalName)));
//        }
//
//        Gdx.app.debug("Crafting step parsing", "found " + stepsFound.size() + " steps");
//        return stepsFound.toArray(new CraftingStep[stepsFound.size()]);
        return null;
    }
}
