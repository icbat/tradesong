package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watchable;
import com.icbat.game.tradesong.observation.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Common base for my stages. Most stages don't actually need Layout, but they should probably know about it
 */
public abstract class BaseStage extends Stage implements Watchable {
    List<Timer> timers = new ArrayList<Timer>();


    public void layout() {
        clearWatchers();
    }
    public void onRender() {}
    public void hide() {
        for (Timer timer : timers) {
            timer.stop();
            timer.clear();
        }
    }

    @Override
    public void addWatcher(Watcher o) {
        watchers.add(o);
    }

    @Override
    public void removeWatcher(Watcher o) {
        watchers.remove(o);
    }

    @Override
    public void clearWatchers() {
        watchers.clear();
    }

    @Override
    public void notifyWatchers(Notification payload) {
        for (Watcher o : watchers) {
            o.handleNotification(this, payload);
        }
    }

    @Override
    public int countWatchers() {
        return watchers.size();
    }

    @Override
    public void dispose() {
        super.dispose();
        clearWatchers();
        timers.clear();
    }
}
