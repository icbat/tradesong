package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UIStyles {

    private final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    private final TextButton.TextButtonStyle disabledButtonStyle = new TextButton.TextButtonStyle();
    private final Label.LabelStyle labelStyle = new Label.LabelStyle();
    private final TextButton.TextButtonStyle blankButtonStyle = new TextButton.TextButtonStyle();

    public UIStyles() {
        BitmapFont font = new BitmapFont();
        this.textButtonStyle.font = font;
        this.textButtonStyle.fontColor = Color.WHITE;
        this.textButtonStyle.overFontColor = Color.LIGHT_GRAY;

        this.disabledButtonStyle.font = font;
        this.disabledButtonStyle.fontColor = Color.DARK_GRAY;

        this.blankButtonStyle.font = font;
        this.labelStyle.font = font;
        this.labelStyle.fontColor = Color.GREEN;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() {
        return textButtonStyle;
    }

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public TextButton.TextButtonStyle getDisabledButtonStyle() {
        return disabledButtonStyle;
    }

    public TextButton.TextButtonStyle getBlankButtonStyle() {
        return blankButtonStyle;
    }
}
