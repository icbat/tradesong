package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/***/
public enum Rarity {
    COMMON("Common", 16, Color.GREEN),
    UNCOMMON("Uncommon", 8, Color.BLUE),
    RARE("Rare", 4, Color.YELLOW),
    ULTRA_RARE("Ultra Rare", 2, Color.PINK);

    String stringRepresentation;
    Integer weight;
    Color color;


    Rarity(String s, Integer weight, Color color) {
        this.stringRepresentation = s;
        this.weight = weight;
        this.color = color;
    }

    public boolean equals(String stringInput) {
        return this.toString().equalsIgnoreCase(stringInput);
    }

    public static Rarity getRarityFromString(String rarityString) {
        for (Rarity value : Rarity.values()) {
            if (value.equals(rarityString))
                return value;
        }
        Gdx.app.error("rarity parsing failed", "defaulting to common");
        return COMMON;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public Integer getTotalWeight() {
        int sum = 0;
        for (Rarity rarity : Rarity.values()) {
            sum += rarity.getWeight();
        }
        return sum;
    }

    public Color getColor() {
        return color;
    }
}
