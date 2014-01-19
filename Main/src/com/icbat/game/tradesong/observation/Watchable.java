package com.icbat.game.tradesong.observation;

import java.util.LinkedHashSet;

/**
 * Java's Watchable impl is an abstract class, and I can't put it where I want. I could probably rig up a way to use it, but I'd rather just make simple one.
 *
 * The downside to this impl is that standard usage is not guaranteed, so you need to be safe when implementing each of these. Implementation basics are in the javadocs for each function.
 */
public interface Watchable {
    LinkedHashSet<Watcher> watchers = new LinkedHashSet<Watcher>();

    /**
     * Add a watcher to the set of Watchers/observers
     * */
    public void addWatcher(Watcher o);

    /**
     * Remove this specified watcher from the set.
     * */
    public void removeWatcher(Watcher o);

    /**
     * Empty the set.
     * */
    public void clearWatchers();

    /**
     * For each watcher, notify it.
     * */
    public void notifyWatchers(Notification payload);

    /**
     * @return the number of observers.
     */
    public int countWatchers();
}
