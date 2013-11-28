package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;
import com.icbat.game.tradesong.utils.Settings;

public class SettingsScreen extends AbstractScreen {

    public static final String PREFERENCES = "general_prefs";

    public SettingsScreen(Tradesong gameInstance) {
        stages.add(new SettingsStage(gameInstance));
    }


    class SettingsStage extends AbstractStage {

        Tradesong gameInstance;
        Preferences preferences = Gdx.app.getPreferences(PREFERENCES);

        Table table = new Table();
        Group indicators = new Group();

        final Slider musicSlider;
        final Label musicIndicator;
        final Slider SFXSlider;
        final Label SFXIndicator;


        SettingsStage(Tradesong gameInstance) {

            this.gameInstance = gameInstance;

            this.table.setFillParent(true);


            // Set up some caching in case things are cancelled
            int cachedMusicVol = preferences.getInteger(Settings.MUSIC_VOLUME.name(), 50);
            int cachedSFXVol = preferences.getInteger(Settings.SFX_VOLUME.name(), 50);

            this.musicIndicator = newNumericalIndicator(Settings.MUSIC_VOLUME.name(), cachedMusicVol);
            this.musicSlider = newSlider(cachedMusicVol);
            this.musicSlider.addListener(new SliderListener(musicSlider, musicIndicator));

            this.SFXIndicator = newNumericalIndicator(Settings.SFX_VOLUME.name(), cachedSFXVol);
            this.SFXSlider = newSlider(cachedSFXVol);
            this.SFXSlider.addListener(new SliderListener(SFXSlider, SFXIndicator));

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
            this.table.add(musicSlider);
            this.table.add(newStringReferencePoint(" 100%"));
            this.table.row();
            this.table.add();
            this.table.add(musicIndicator);

            this.table.row();
            this.table.row();

            this.table.add();
            this.table.add(newSettingsHeader("SFX Volume"));
            this.table.row();
            this.table.add(newStringReferencePoint("0% "));
            this.table.add(SFXSlider);
            this.table.add(newStringReferencePoint(" 100%"));
            this.table.row();
            this.table.add();
            this.table.add(SFXIndicator);

            // Save and Exit button, Discard button
            addAcceptChangesButton();
            addDiscardButton();
        }


        private void addAcceptChangesButton() {
            TextButton saveChangesButton = new TextButton("Accept changes", Tradesong.uiStyles.getTextButtonStyle());

            saveChangesButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    preferences.putInteger(Settings.MUSIC_VOLUME.name(), (int) musicSlider.getValue());
                    preferences.putInteger(Settings.SFX_VOLUME.name(), (int) SFXSlider.getValue());

                    gameInstance.goBack();

                }
            });

            saveChangesButton.setPosition(this.getWidth() - saveChangesButton.getWidth(), 0);

            this.addActor(saveChangesButton);
        }

        private void addDiscardButton() {
            TextButton discardChangesButton = new TextButton("Discard changes", Tradesong.uiStyles.getTextButtonStyle());

            discardChangesButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goBack();
                }
            });

            this.addActor(discardChangesButton);
        }

        private Label newNumericalIndicator(String linkedKey, int indicatorStartingValue) {
            Label indicator = new Label(String.valueOf(indicatorStartingValue), Tradesong.uiStyles.getLabelStyle());
            indicator.setName(linkedKey);
            indicators.addActor(indicator);
            return indicator;
        }

        /***/
        private Slider newSlider(int startingVal) {
            Slider settingSlider = new Slider(0, 100, 5, false, Tradesong.uiStyles.getSliderStyle());
            settingSlider.setWidth(this.getWidth() / 2);
            settingSlider.setValue(startingVal);

            return settingSlider;
        }

        private Label newStringReferencePoint(String referenceValue) {
            return new Label(referenceValue, Tradesong.uiStyles.getLabelStyle());
        }

        private Label newSettingsHeader(String headerText) {
            return new Label(headerText, Tradesong.uiStyles.getLabelStyle());
        }


        class SliderListener extends ChangeListener {

            Slider owner;
            Label linkedTarget;

            SliderListener(Slider owner, Label linkedTarget) {
                this.linkedTarget = linkedTarget;
                this.owner = owner;
            }

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                linkedTarget.setText(String.valueOf((int) (owner.getValue())));
            }
        }
    }
}
