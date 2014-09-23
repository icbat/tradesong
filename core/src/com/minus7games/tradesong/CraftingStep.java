package com.minus7games.tradesong;

public class CraftingStep {

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
}
