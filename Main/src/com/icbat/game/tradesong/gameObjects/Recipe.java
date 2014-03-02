package com.icbat.game.tradesong.gameObjects;

import java.util.ArrayList;
import java.util.List;

/***/
public class Recipe {
    private Item output;
    private List<Item> ingredients;
    private Integer craftTime;

    public Recipe(Item output, List<Item> ingredients, Integer craftTime) {
        this.output = output;
        this.ingredients = ingredients;
        this.craftTime = craftTime;
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

        return false;
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
