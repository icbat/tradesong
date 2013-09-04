package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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


        SettingsStage(Tradesong gameInstance) {
            this.gameInstance = gameInstance;

            this.table.setFillParent(true);
        }

        @Override
        public void layout() {
            this.table.clear();

            this.table.add(newSettingsHeader("Music Volume"));
            this.table.row();
            this.table.add(newStringReferencePoint("0%"));
            this.table.add(newMusicVolumeBar());
            this.table.add(newStringReferencePoint("100%"));
            this.table.row();
            this.table.add(newNumericalIndicator(preferences.getInteger("music_volume", 50)));


            // TODO cleaner way to say this?
            this.table.row();
            this.table.row();

            this.table.add(newSettingsHeader("SFX Volume"));
            this.table.row();
            this.table.add(newStringReferencePoint("0%"));
            this.table.add(newSFXVolumeBar());
            this.table.add(newStringReferencePoint("100%"));
            this.table.row();
            this.table.add(newNumericalIndicator(preferences.getInteger("sfx_volume", 50)));






        }

        private Actor newNumericalIndicator(int indicatorStartingValue) {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }

        private Actor newStringReferencePoint(String referenceValue) {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }


        private Actor newSettingsHeader(String headerText) {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }

        private Actor newMusicVolumeBar() {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }

        private Actor newSFXVolumeBar() {
            return null;  //To change body of created methods use File | Settings | File Templates.
        }


    }
}
