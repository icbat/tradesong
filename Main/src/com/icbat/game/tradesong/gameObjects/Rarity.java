package com.icbat.game.tradesong.gameObjects;

/***/
public enum Rarity {
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    ULTRA_RARE("Ultra Rare");

    String stringRepresentation;
    Rarity(String s) {
        stringRepresentation = s;
    }

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

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
