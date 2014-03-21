package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameSkin extends Skin {
    private GameSkin() {}

    public static GameSkin makeDefaultUIStyles() {
        GameSkin defaultStyle = new GameSkin();

        defaultStyle.add("default", new BitmapFont());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = defaultStyle.getFont("default");
        defaultStyle.add("default", labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = defaultStyle.getFont("default");
        defaultStyle.add("default", textButtonStyle);

        return defaultStyle;
    }
}
