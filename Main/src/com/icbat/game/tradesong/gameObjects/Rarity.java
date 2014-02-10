package com.icbat.game.tradesong.gameObjects;

/***/
public enum Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    ULTRA_RARE;

    public boolean equals(String stringInput) {
        return this.toString().equalsIgnoreCase(stringInput);
    }

    public static Rarity getRarityFromString(String rarityString) {
        for (Rarity value : Rarity.values()) {
            if (value.equals(rarityString))
                return value;
        }
        return COMMON;
    }
}
