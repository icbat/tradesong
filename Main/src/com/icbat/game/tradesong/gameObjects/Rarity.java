package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;

/***/
public enum Rarity {
    COMMON("Common", 2),
    UNCOMMON("Uncommon", 4),
    RARE("Rare", 6),
    ULTRA_RARE("Ultra Rare", 8);

    String stringRepresentation;
    Integer weight;

    Rarity(String s, Integer weight) {
        stringRepresentation = s;
        this.weight = weight;
    }

    public boolean equals(String stringInput) {
        return this.toString().equalsIgnoreCase(stringInput);
    }

    public static Rarity getRarityFromString(String rarityString) {
        for (Rarity value : Rarity.values()) {
            if (value.equals(rarityString))
                return value;
        }
        Gdx.app.log("rarity parsing failed", "defaulting to common");
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
}
