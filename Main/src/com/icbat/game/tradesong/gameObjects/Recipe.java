package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.Items;

import java.util.ArrayList;
import java.util.List;

/***/
public class Recipe {
    private Items.Item output;
    private List<Items.Item> ingredients;
    private Integer craftTime;
    private final boolean isCatchAll;

    public Recipe(Items.Item output, List<Items.Item> ingredients, Integer craftTime, boolean isCatchAll) {
        this.output = output;
        this.ingredients = ingredients;
        this.craftTime = craftTime;
        this.isCatchAll = isCatchAll;
    }

    public boolean inputCanCraftThis(List<Items.Item> itemsInput) {
        if (itemsInput.size() == ingredients.size()) {
            List<Items.Item> inputCopy = new ArrayList<Items.Item>(itemsInput);

            for (Items.Item ingredient : ingredients) {
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

    public Items.Item getOutput() {
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
