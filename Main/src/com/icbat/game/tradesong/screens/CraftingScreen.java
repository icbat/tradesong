package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CraftingScreen extends AbstractScreen {

    public CraftingScreen() {
        Stage craftingStage = new Stage();

        Table layout = new Table();
        layout.setFillParent(true);
        craftingStage.addActor(layout);



        setupStages(craftingStage);

    }

    @Override
    public void render(float delta) {
        drawBackground(0,0,0.2f,1);
        renderStages(delta);
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }
}
