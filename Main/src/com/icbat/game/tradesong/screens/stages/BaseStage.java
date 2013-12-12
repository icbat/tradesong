package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * Common base for my stages. Most stages don't actually need Layout, but they should probably know about it
 */
public abstract class BaseStage extends Stage {
    List<Timer> timers = new ArrayList<Timer>();

    public void layout() {}
    public void hide() {
        for (Timer timer : timers) {
            timer.stop();
            timer.clear();
        }
    }
}
