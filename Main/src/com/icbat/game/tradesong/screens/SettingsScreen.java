package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;

public class SettingsScreen extends AbstractScreen {
    public SettingsScreen(Tradesong gameInstance) {
        stages.add(new SettingsStage(gameInstance));
    }


    class SettingsStage extends AbstractStage {
        Tradesong gameInstance;
        Preferences preferences = Gdx.app.getPreferences("General_Preferences");

        Table table = new Table();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();


        SettingsStage(Tradesong gameInstance) {
            this.gameInstance = gameInstance;

            this.table.setFillParent(true);

            this.buttonStyle.font = new BitmapFont();
            this.buttonStyle.fontColor = Color.WHITE;

            this.labelStyle.font = new BitmapFont();
            this.labelStyle.fontColor = Color.WHITE;

            this.sliderStyle.knob = new TextureRegionDrawable( new TextureRegion( Tradesong.getSliderHead() ) );
            this.sliderStyle.background = new TextureRegionDrawable( new TextureRegion( Tradesong.getSliderBG(), 100, 8 ) );


        }

        @Override
        public void layout() {
            this.clear();
            table.clearChildren();

            this.addActor(this.table);

            this.table.add();
            this.table.add(newSettingsHeader("Music Volume"));
            this.table.row();
            this.table.add(newStringReferencePoint("0% "));
            this.table.add(newSlider("music_volume"));
            this.table.add(newStringReferencePoint(" 100%"));
            this.table.row();
            this.table.add();
            this.table.add(newNumericalIndicator(preferences.getInteger("music_volume", 50)));

            this.table.row();
            this.table.row();

            this.table.add();
            this.table.add(newSettingsHeader("SFX Volume"));
            this.table.row();
            this.table.add(newStringReferencePoint("0% "));
            this.table.add(newSlider("sfx_volume"));
            this.table.add(newStringReferencePoint(" 100%"));
            this.table.row();
            this.table.add();
            this.table.add(newNumericalIndicator(preferences.getInteger("sfx_volume", 50)));


            // Save and Exit button, Discard button
            addAcceptChangesButton();
            addDiscardButton();
        }


        private void addAcceptChangesButton() {
            TextButton saveChangesButton = new TextButton("Accept changes", buttonStyle);

            saveChangesButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goBackAScreen();
                    //TODO actually save them
                }
            });

            Gdx.app.log("acceptChangesButton", "width: " + this.getWidth());
            Gdx.app.log("acceptChangesButton", "theButton: " + saveChangesButton.getWidth());

            saveChangesButton.setPosition(this.getWidth() - saveChangesButton.getWidth(), 0);

            this.addActor(saveChangesButton);
        }

        private void addDiscardButton() {
            TextButton discardChangesButton = new TextButton("Discard changes", buttonStyle);

            discardChangesButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goBackAScreen();
                }
            });

            this.addActor(discardChangesButton);
        }

        private Label newNumericalIndicator(int indicatorStartingValue) {
            // TODO update this somehow!
            return new Label(String.valueOf(indicatorStartingValue), labelStyle);
        }

        private Label newStringReferencePoint(String referenceValue) {
            return new Label(referenceValue, labelStyle);
        }


        private Label newSettingsHeader(String headerText) {
            return new Label(headerText, labelStyle);
        }

        private Slider newSlider(String settingKey) {
            Slider settingSlider = new Slider(0, 100, 5, false, sliderStyle);

            settingSlider.setWidth(this.getWidth()/2);

            // TODO set starting value
            // TODO listener

            return settingSlider;
        }
    }
}
