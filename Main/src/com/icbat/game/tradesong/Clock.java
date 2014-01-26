package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.observation.NotificationManager;
import com.icbat.game.tradesong.observation.notifications.DayEndedNotification;

/**
 * @author icbat
 */
public class Clock extends NotificationManager {
    /** Length of a day in seconds. Currently set to 12 minutes.
     * Expecting to want to refactor this out at some point */
    private static final int dayLengthInMinutes = 12;
    public static final int SECONDS_TO_MINUTE = 60;
    /** Current time in minutes. Initialized to 0 every time you startClock */
    private int currentTime = 0;
    private final Timer dayTimer = new Timer();

    public void startClock() {
        currentTime = 0;
        dayTimer.scheduleTask(new dayEndTask(), dayLengthInMinutes * SECONDS_TO_MINUTE);
        dayTimer.scheduleTask(new countUpTask(), 0, SECONDS_TO_MINUTE, dayLengthInMinutes * SECONDS_TO_MINUTE);
    }

    public int getTime() {
        return currentTime;
    }

    private class dayEndTask extends Timer.Task {

        @Override
        public void run() {
            Gdx.app.debug("clock says", "day ended!");
            notifyWatchers(new DayEndedNotification());
            startClock();
        }
    }

    private class countUpTask extends Timer.Task {

        @Override
        public void run() {
            currentTime += 1;

        }
    }
}
