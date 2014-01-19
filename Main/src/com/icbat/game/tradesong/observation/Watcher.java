package com.icbat.game.tradesong.observation;

/**
 * Observer
 * */
public interface Watcher {
    public void handleNotification(Object sender, Notification notification);

    public boolean verifyICare(Object sender, Notification notification);
}
