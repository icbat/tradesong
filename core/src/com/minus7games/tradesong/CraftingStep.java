package com.minus7games.tradesong;

import com.badlogic.gdx.scenes.scene2d.Actor;

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
}
