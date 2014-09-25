package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;

import java.util.LinkedList;

public class CraftingStep implements Displayable {

    private final Item validInput;
    private final Item output;

    public CraftingStep(Item validInput, Item output) {
        this.validInput = validInput;
        this.output = output;
    }

    /** @return the finished product if the input is valid; otherwise spits out the input */
    public Item craft(Item input) {
        if (input.equals(validInput)) {
            return output;
        } else {
            return input;
        }
    }

    @Override
    public Actor getActor() {
        return null;
    }

    public static CraftingStep[] parseSteps(JsonValue craftingStepsNode) {
        Gdx.app.debug("parsing crafting steps", "");
        LinkedList<CraftingStep> stepsFound = new LinkedList<CraftingStep>();
        JsonValue currentStep = craftingStepsNode;

        while (currentStep != null) {
            String inputInternalName = currentStep.getChild("validInput").asString();
            String outputInternalName = currentStep.getChild("output").asString();
            Gdx.app.debug("step found that turns", inputInternalName + " => " + outputInternalName);
            currentStep = currentStep.next();
            stepsFound.add(new CraftingStep(ItemIndex.get(inputInternalName), ItemIndex.get(outputInternalName)));
        }

        Gdx.app.debug("Crafting step parsing", "found " + stepsFound.size() + " steps");
        return stepsFound.toArray(new CraftingStep[stepsFound.size()]);
    }
}
