package com.icbat.game.tradesong;

import com.badlogic.gdx.assets.AssetManager;
import com.icbat.game.LJ;

public class LoggedAssetManager extends AssetManager {
    LJ logger;
    public LoggedAssetManager(Tradesong gameInstance) {
        super();
        logger = gameInstance.log;
    }

    public <T> void timedLoad(String fileName, Class<T> type) {
        float start, finish;

        logger.debug("Loading asset: " + fileName);
        start = System.currentTimeMillis();
        this.load(fileName, type);
        this.finishLoading();
        finish = System.currentTimeMillis();
        logger.debug("Loading finished in: " + (finish - start) + " ms");
    }
}
