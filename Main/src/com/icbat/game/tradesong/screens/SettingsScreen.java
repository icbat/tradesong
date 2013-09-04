package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.icbat.game.tradesong.stages.AbstractStage;

public class SettingsScreen extends AbstractScreen {
    public SettingsScreen() {
        stages.add(new SettingsStage());
    }


    class SettingsStage extends AbstractStage {
        Preferences preferences = Gdx.app.getPreferences("General_Preferences");

        @Override
        public void layout() {


        }


    }
}
