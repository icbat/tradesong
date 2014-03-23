package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameSkin extends Skin {
    private GameSkin() {}

    public static GameSkin makeDefaultUIStyles() {
        GameSkin skin = new GameSkin();

        skin.add("default", new BitmapFont());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        TextButton.TextButtonStyle disabledButton = new TextButton.TextButtonStyle();
        disabledButton.font = skin.getFont("default");
        disabledButton.fontColor = Color.GRAY;
        skin.add("disabled", disabledButton);

        TextButton.TextButtonStyle redButton = new TextButton.TextButtonStyle();
        redButton.font = skin.getFont("default");
        redButton.fontColor = Color.RED;
        skin.add("redText", redButton);

        return skin;
    }
}
