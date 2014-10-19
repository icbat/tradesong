package com.minus7games.tradesong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/***/
public class GameSkin extends Skin {
    private static final Skin skin = new GameSkin();

    public GameSkin() {
        BitmapFont defaultFont = new BitmapFont();

        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();
        defaultLabelStyle.font = defaultFont;
        defaultLabelStyle.fontColor = Color.WHITE;

        TextButton.TextButtonStyle defaultTextButtonStyle = new TextButton.TextButtonStyle();
        defaultTextButtonStyle.font = defaultFont;
        defaultTextButtonStyle.fontColor = Color.WHITE;
        defaultTextButtonStyle.disabledFontColor = Color.DARK_GRAY;

        this.add("default", defaultFont);
        this.add("default", defaultLabelStyle);
        this.add("default", defaultTextButtonStyle);
    }

    public static Skin get() {
        return skin;
    }
}
