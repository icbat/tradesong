package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.icbat.game.tradesong.Tradesong;

/**
 * @author icbat
 */
public class MapScreen extends AbstractScreen {
    public MapScreen(String mapName) {
        // TODO consider moving up to tradesong init
        Tradesong.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        setMap(mapName);
    }

    public void setMap(String mapName) {

    }

    @Override
    public void render(float delta) {
        render(0.4f, 0.7f, 0.99f, 1, delta);
    }
}
