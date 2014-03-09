package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.screens.stages.ShippingStage;

/***/
public class ShippingScreen extends AbstractScreen {

    public ShippingScreen() {
        stages.add(new ShippingStage());
        finishMakingScreen();
    }

    @Override
    public String getScreenName() {
        return "shippingScreen";
    }

    @Override
    public void render(float delta) {
        drawBackground(0,0,0.3f,1);
        renderStages(delta);
    }
}
