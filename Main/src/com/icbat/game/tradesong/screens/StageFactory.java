package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.observation.NotificationManager;

/***/
public class StageFactory {


    public static Stage makeHUD() {
        BaseStage hud = new BaseStage();
        return hud;
    }

    /**
     * Common base for my stages. Most stages don't actually need Layout, but they should probably know about it
     */
    private static class BaseStage extends Stage {
        NotificationManager notificationCenter = new NotificationManager();

        @Override
        public void dispose() {
            super.dispose();
            notificationCenter.clearWatchers();
        }
    }
}
