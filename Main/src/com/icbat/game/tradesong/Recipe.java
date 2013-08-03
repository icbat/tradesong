package com.icbat.game.tradesong;

import java.util.ArrayList;

// TODO timer for crafting
public class Recipe {
    Item output;
    String workshop;
    ArrayList<Item> recipeBySlot = new ArrayList<Item>();

    public Recipe(Item outputItem, String workshop, ArrayList<Item> recipeBySlot) {
        this.output = outputItem;
        this.workshop = workshop;
        this.recipeBySlot = recipeBySlot;
    }

    public boolean check(ArrayList<Item> checkAgainst) {
        if (checkAgainst.size() == recipeBySlot.size()) {
            for (int i = 0; i < checkAgainst.size(); ++i) {
                // If one slot is wrong
                if (!checkAgainst.get(i).equals(recipeBySlot.get(i)))
                    return false;

            }
            return false;
        }
        return false;
    }
    public Item getOutput() {
        return output;
    }
}
