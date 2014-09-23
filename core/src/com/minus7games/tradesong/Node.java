package com.minus7games.tradesong;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** All the 'buildings' */
public class Node {
    private final List<CraftingStep> possibleCrafts = new LinkedList<CraftingStep>();
    private final List<Item> inputs = new LinkedList<Item>();
    private final List<Item> outputs = new LinkedList<Item>();

    public Node(CraftingStep... mock) {
        Collections.addAll(possibleCrafts, mock);
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


}
