package com.icbat.game.tradesong.assetReferences;

public enum MusicAssets {
    TITLE_THEME("Thatched Villagers");

    private String path;

    private MusicAssets(String pathVal) {
        this.path = "music/" + pathVal + ".ogg";
    }

    public String getPath() {
        return path;
    }
}

