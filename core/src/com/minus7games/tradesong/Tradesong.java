package com.minus7games.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.minus7games.tradesong.indices.ItemIndex;
import com.minus7games.tradesong.indices.NodeIndex;
import com.minus7games.tradesong.screens.HomeScreen;

public class Tradesong extends Game {
    public static AssetManager assets;
    public static Tradesong instance;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        instance = this;
        setupGame();
        ScreenManager.get().moveToScreen(new HomeScreen());
    }

    private void setupGame() {
        ItemIndex items = new ItemIndex(Gdx.files.internal("items.json"));
        NodeIndex nodes = new NodeIndex(Gdx.files.internal("nodes.json"));
        loadAssets();
        ScreenManager screenManager = new ScreenManager(this);
    }

    private void loadAssets() {
        assets = new AssetManager();

        assets.load("1p.png", Texture.class);
        for (Item item : ItemIndex.getAllItems()) {
            assets.loadIcon(item.getInternalName());
        }
        assets.finishLoading();
    }
}
