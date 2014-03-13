package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Item representation with Prototype functionality via copy constructor.
 * */
public class Item extends Image implements Comparable {
    private final String name;
    private final String description;
    private final TextureRegion icon;
    private final Integer basePrice;
    private final Rarity rarity;

    public Item(String name, String description, Rarity rarity, TextureRegion icon, Integer basePrice) {
        super(icon);
        this.name = name;
        this.rarity = rarity;
        this.icon = icon;
        this.description = description;
        this.basePrice = basePrice;
    }

    public Item(Item original) {
        this(original.getName(), original.getDescription(), original.getRarity(), original.getIcon(), original.getBasePrice());
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
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!basePrice.equals(item.basePrice)) return false;
        if (!description.equals(item.description)) return false;
        if (!icon.equals(item.icon)) return false;
        if (!name.equals(item.name)) return false;
        if (rarity != item.rarity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + basePrice.hashCode();
        result = 31 * result + rarity.hashCode();
        return result;
    }

    public Rarity getRarity() {
        return this.rarity;
    }
}
