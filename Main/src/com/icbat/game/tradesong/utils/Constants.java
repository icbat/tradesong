package com.icbat.game.tradesong.utils;

/**
 * A handful of game-wide, numerical constants
 * */
public enum Constants {
    SPRITE_DIMENSION(32);

    private int value;

    private Constants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}