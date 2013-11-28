package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Handy little default implementation of camera to cut down on duplication
 */
public class OrthoCamera extends OrthographicCamera {
    public OrthoCamera(int width, int height) {
        super(width, height);
//        this.setToOrtho(false, (width / height) * 10, 10);
        this.zoom = 1;
        this.update();
    }
}
