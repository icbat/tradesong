package com.icbat.game.tradesong.utils;

public enum MusicAsset {
    TITLE_THEME("Thatched Villagers");

    private String path;

    private MusicAsset(String pathVal) {
        this.path = "music/" + pathVal + ".mp3";
    }

    public String getPath() {
        return path;
    }
}

