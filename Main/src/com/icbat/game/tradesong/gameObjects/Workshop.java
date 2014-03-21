package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Items;

import java.util.ArrayList;
import java.util.List;

/***/
public class Workshop extends Image{
    List<Recipe> recipesAssociated = new ArrayList<Recipe>();
    private final String name;
    private final TextureRegion sprite;

    public Workshop(String workshopName, List<Recipe> recipes, TextureRegion sprite) {
        super(sprite);
        recipesAssociated = recipes;
        name = workshopName;
        this.sprite = sprite;
    }


    public String getName() {
        return name;
    }

    public Items.Item getOutput(List<Items.Item> itemsInput) {
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

    public TextureRegion getSprite() {
        return sprite;
    }
}
