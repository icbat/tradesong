package com.minus7games.tradesong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.minus7games.tradesong.indices.ItemIndex;
import com.minus7games.tradesong.indices.NodeIndex;
import com.minus7games.tradesong.screens.NodeScreen;

public class Tradesong extends Game {
    public static AssetManager assets;

    @Override
    public void create() {
//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setupGame();
        setScreen(new NodeScreen(NodeIndex.get("orchard")));
    }

    private void setupGame() {
        ItemIndex items = new ItemIndex(Gdx.files.internal("items.json"));
        NodeIndex nodes = new NodeIndex(Gdx.files.internal("nodes.json"));
        assets = new AssetManager();
        assets.load("1p.png", Texture.class);
        assets.finishLoading();
    }
}
