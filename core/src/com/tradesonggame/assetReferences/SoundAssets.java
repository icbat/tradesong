package com.tradesonggame.assetReferences;

public enum SoundAssets {
    GATHER_CLINK("gathering2"),
    SUCCESS("success"),
    CHURCHBELL("churchbell"),
    PORTAL("portal"),
    SCREEN_SWAP("swapping"),
    FAIL("fail"),
    ;

    private String path;

    private SoundAssets(String pathVal) {
        this.path = "sounds/" + pathVal + ".ogg";
    }

    public String getPath() {
        return path;
    }
}

