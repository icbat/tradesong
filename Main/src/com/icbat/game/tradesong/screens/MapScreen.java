package com.icbat.game.tradesong.screens;

/**
 * @author icbat
 */
public class MapScreen extends AbstractScreen {
    public MapScreen(String mapName) {
        setMap(mapName);
    }

    public void setMap(String mapName) {

    }

    @Override
    public void render(float delta) {
        render(0.3f, 0.8f, .8f, 1f, delta);
    }
}
