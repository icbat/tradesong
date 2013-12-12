package com.icbat.game.tradesong.assetReferences;

public enum TextureAssets {
    ITEMS("items"),
    HUD_BG("hud-bg"),
    FRAME("frame"),
    WORKSHOP_ARROW("workshop-arrow"),
    ICON_HAMMER("hammer-drop"),
    ICON_WRENCH("auto-repair"),
    ICON_BOOK("burning-book"),
    CHAR("character"),
    COIN("goldCoin5"),
    SLIDER_HEAD("slider-icon"),
    SLIDER_BG("slider-bg"),
    SKY("sky"),;

    private String path;

    private TextureAssets(String pathVal) {
        this.path = "sprites/" + pathVal + ".png";
    }

    public String getPath() {
        return path;
    }
}

