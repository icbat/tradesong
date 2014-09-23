package com.minus7games.tradesong;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** All the 'buildings' */
public class Node {
    private List<CraftingStep> possibleCrafts = new LinkedList<CraftingStep>();

    public Node(CraftingStep... mock) {
        Collections.addAll(possibleCrafts, mock);
    }

    /** @return the matching output of a crafting step, or the input if no work was done */
    public Item processesItem(Item input) {
        for (CraftingStep possibleCraft : possibleCrafts) {
            if (possibleCraft.takesAsInput(input)){
                return possibleCraft.craft(input);
            }
        }
        return input;
    }
}
