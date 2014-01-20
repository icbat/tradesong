package com.icbat.game.tradesong.observation;

import java.util.LinkedHashSet;

/**
 * Convenience implementation of what a Watcher should do.
 */
public class NotificationManager {
    private LinkedHashSet<Watcher> watchers = new LinkedHashSet<Watcher>();

    public void addWatcher(Watcher o) {
        watchers.add(o);
    }

    public void removeWatcher(Watcher o) {
        watchers.remove(o);
    }

    public void clearWatchers() {
        watchers.clear();
    }

    public void notifyWatchers(Notification payload) {
        for (Watcher w : watchers) {
            w.handleNotification(payload);
        }
    }

    public int countWatchers() {
        return watchers.size();
    }
}
