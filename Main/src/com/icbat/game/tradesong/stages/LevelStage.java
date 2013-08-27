package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.maps.MapProperties;

public abstract class LevelStage extends AbstractStage {

    MapProperties properties;

    public LevelStage(MapProperties properties) {
        this.properties = properties;
        layout();

    }
}
