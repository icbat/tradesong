package gameObjects;

import java.util.ArrayList;
import java.util.List;

/***/
public class Workshop {
    List<Recipe> recipesAssociated = new ArrayList<Recipe>();
    private String name;

    public Workshop(String workshopName, List<Recipe> recipes) {
        recipesAssociated = recipes;
        name = workshopName;
    }


    public String getName() {
        return name;
    }

    public Item getOutput(List<Item> itemsInput) {
        for (Recipe recipe : recipesAssociated) {
            if (recipe.inputCanCraftThis(itemsInput)) {
                return recipe.getOutput();
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "recipesAssociated=" + recipesAssociated.size() +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Recipe> getRecipes() {
        return recipesAssociated;
    }
}
