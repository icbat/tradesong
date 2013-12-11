package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Item representation with Prototype functionality via copy constructor.
 * */
public class Item extends Actor {
    private final String name;
    private final String description;
    private final TextureRegion icon;
    private final Integer basePrice;

    public Item(String name, String description, TextureRegion icon, Integer basePrice) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.basePrice = basePrice;
    }

    public Item(Item original) {
        this(original.getName(), original.getDescription(), original.getIcon(), original.getBasePrice());
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public String getDescription() {
        return description;
    }

    public TextureRegion getIcon() {
        return icon;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                ", basePrice=" + basePrice +
                '}';
    }
}
