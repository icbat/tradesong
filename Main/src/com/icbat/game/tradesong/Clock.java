package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;

/**
 * @author icbat
 */
public class Clock {
    /** Length of a day in seconds. Currently set to 12 minutes.
     * Expecting to want to refactor this out at some point */
//    private static final int dayLengthInSeconds = 720;
    private static final int dayLengthInSeconds = 10;
    private int currentTime = 0;
    /**
     * Does some work. Starts the timer going.
     * */
    public Clock() {
        Timer dayTimer = new Timer();
        dayTimer.scheduleTask(new dayEndTask(), dayLengthInSeconds);
        dayTimer.scheduleTask(new countUpTask(), 0, 1, dayLengthInSeconds);
    }

    public int getCurrentTime() {
        return currentTime;
    }

    private class dayEndTask extends Timer.Task {

        @Override
        public void run() {
            Gdx.app.debug("clock says", "day ended!");
        }
    }

    private class countUpTask extends Timer.Task {

        @Override
        public void run() {
            currentTime += 1;
            Gdx.app.debug("currentTimeIs", currentTime + "");
        }
    }
}
