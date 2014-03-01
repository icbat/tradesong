package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.MusicAssets;
import com.icbat.game.tradesong.screens.stages.MainMenuStage;

public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen() {
        super(false);
        stages.add(new MainMenuStage());
        Tradesong.playLoopingMusic(MusicAssets.TITLE_THEME);
    }

    @Override
    public String getScreenName() {
        return "mainMenuScreen";
    }
}
