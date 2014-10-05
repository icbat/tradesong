package com.minus7games.tradesong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/***/
public class GameSkin extends Skin {
    private static final Skin skin = new GameSkin();

    public GameSkin() {
        BitmapFont defaultFont = new BitmapFont();
        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();
        defaultLabelStyle.font = defaultFont;
        defaultLabelStyle.fontColor = Color.WHITE;

        this.add("default", defaultFont);
        this.add("default", defaultLabelStyle);
    }

    public static Skin get() {
        return skin;
    }
}
