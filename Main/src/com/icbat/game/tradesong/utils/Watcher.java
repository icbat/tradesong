package com.icbat.game.tradesong.utils;

/**
 * Observer
 * */
public interface Watcher {
    public void handleNotification(Watcher sender, Object notification);
}
