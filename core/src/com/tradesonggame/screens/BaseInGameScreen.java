package com.tradesonggame.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tradesonggame.screens.components.HUD;

public abstract class BaseInGameScreen extends AbstractScreen {
    @Override
    public void setupStages(Stage... extraStages) {
        super.setupStages(extraStages);
        stages.add(new HUD());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        Tradesong.state.getWorkshopManager().actOnEachWorkshop(delta);
    }
}
