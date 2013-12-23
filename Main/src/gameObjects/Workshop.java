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

    @Override
    public String toString() {
        return "Workshop{" +
                "recipesAssociated=" + recipesAssociated.size() +
                ", name='" + name + '\'' +
                '}';
    }
}
