package com.icbat.game.tradesong.gameObjects;

import java.util.ArrayList;
import java.util.List;

/***/
public class Recipe {
    private Item output;
    private List<Item> ingredients;
    private Integer craftTime;
    private final boolean isCatchAll;

    public Recipe(Item output, List<Item> ingredients, Integer craftTime, boolean isCatchAll) {
        this.output = output;
        this.ingredients = ingredients;
        this.craftTime = craftTime;
        this.isCatchAll = isCatchAll;
    }

    public boolean inputCanCraftThis(List<Item> itemsInput) {
        if (itemsInput.size() == ingredients.size()) {
            List<Item> inputCopy = new ArrayList<Item>(itemsInput);

            for (Item ingredient : ingredients) {
                int firstIndex = inputCopy.indexOf(ingredient);
                if (firstIndex != -1) {
                    inputCopy.remove(firstIndex);
                }
            }
            if (inputCopy.isEmpty()) {
                return true;
            }
        }
        return (isCatchAll && itemsInput.size() == 1);
    }

    public Integer getCraftTime() {
        return craftTime;
    }

    public Item getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "craftTime=" + craftTime +
                ", ingredients=" + ingredients +
                ", output=" + output +
                '}';
    }
}
