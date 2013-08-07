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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (!output.equals(recipe.output)) return false;
        if (!recipeBySlot.equals(recipe.recipeBySlot)) return false;
        if (!workshop.equals(recipe.workshop)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = output.hashCode();
        result = 31 * result + workshop.hashCode();
        result = 31 * result + recipeBySlot.hashCode();
        return result;
    }
}
