package com.tradesonggame.utility;

/**
 * A handful of game-wide, numerical constants
 * */
public enum Constants {
    SPRITE_DIMENSION(32),
    GATHER_TIME_BASE(2),
    NUMBER_OF_SAVE_SLOTS(4),
    RENT_AMOUNT(-300),
    ;

    private int value;

    private Constants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
