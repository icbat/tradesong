package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.screens.components.HUD;

public abstract class BaseInGameScreen extends AbstractScreen {
    @Override
    public void setupStages(Stage... extraStages) {
        super.setupStages(extraStages);
        stages.add(new HUD());
    }
}
