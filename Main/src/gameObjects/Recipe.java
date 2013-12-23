package gameObjects;

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
}
