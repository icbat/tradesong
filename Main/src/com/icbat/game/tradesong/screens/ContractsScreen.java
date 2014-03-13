package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.screens.stages.ContractsStage;

/***/
public class ContractsScreen extends AbstractScreen {

    public ContractsScreen() {
        super(new ContractsStage());
    }

    @Override
    public String getScreenName() {
        return "contractsScreen";
    }

    @Override
    public void render(float delta) {
        drawBackground(0.3f, 0, 0, 1);
        renderStages(delta);
    }
}
