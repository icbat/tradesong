package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.Gdx;

/**
 * Things that can be passed to goToOverlay
 */
public enum ScreenTypes {
    MAIN_MENU,
    SETTINGS,
    WORKSHOP,
    INVENTORY,
    STORE,
    GUILDHALL,
    LEVEL;

    public static ScreenTypes getTypeFromString(String typeString) {
        typeString = typeString.toLowerCase();

        Gdx.app.log("screenTypesEnum", typeString);

        ScreenTypes out = LEVEL;

        if (typeString.equals("main_menu"))
            out = MAIN_MENU;
        if (typeString.equals("settings"))
            out = SETTINGS;
        if (typeString.equals("workshop"))
            out = WORKSHOP;
        if (typeString.equals("inventory"))
            out = INVENTORY;
        if (typeString.equals("store"))
            out = STORE;
        if (typeString.equals("guildhall"))
            out = GUILDHALL;

        return out;
    }
}

