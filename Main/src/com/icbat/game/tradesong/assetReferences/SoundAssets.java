package com.icbat.game.tradesong.assetReferences;

public enum SoundAssets {
    GATHER_CLINK("hammering");

    private String path;

    private SoundAssets(String pathVal) {
        this.path = "sounds/" + pathVal + ".ogg";
    }

    public String getPath() {
        return path;
    }
}

