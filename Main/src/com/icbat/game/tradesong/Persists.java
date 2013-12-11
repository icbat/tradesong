package com.icbat.game.tradesong;

/**
 * Skeleton for what a persistent object must be able to handle. Gives whatever is going to load these a way to do it easily.
 */
public interface Persists {
    void save();
    void load();
}
