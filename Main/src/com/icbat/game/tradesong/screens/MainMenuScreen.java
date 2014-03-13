package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.MusicAssets;
import com.icbat.game.tradesong.screens.stages.MainMenuStage;

public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen() {
        stages.add(new MainMenuStage());
        Tradesong.playLoopingMusic(MusicAssets.MYSTERIOUS);
    }

    @Override
    public String getScreenName() {
        return "mainMenuScreen";
    }
}
