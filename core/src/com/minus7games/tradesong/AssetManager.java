package com.minus7games.tradesong;

import com.badlogic.gdx.graphics.Texture;

/** Wraps the GDX AssetManager with some extra sugar for this use case */
public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

    public void loadIcon(String fileName) {
        this.load(iconPath(fileName), Texture.class);
    }

    public Texture getIcon(String fileName) {
        return this.get(iconPath(fileName), Texture.class);
    }

    private String iconPath(String fileName) {
        return "icons/" + fileName + ".png";
    }
}
