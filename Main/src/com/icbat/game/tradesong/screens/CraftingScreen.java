package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Workshop;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.screens.components.CraftingStationActor;

public class CraftingScreen extends AbstractScreen {

    public CraftingScreen() {
        Stage craftingStage = new Stage();

        Table layout = new Table();
        layout.setFillParent(true);
        craftingStage.addActor(layout);

        layout.add(new WorkshopTable(Tradesong.state.getWorkshops().get(0)));

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

    private class WorkshopTable extends Table {
        public WorkshopTable(Workshop workshop) {
            this.setFillParent(true);
            for (BaseCraftingStation station : workshop.getOrderedNodes()) {
                this.add(new CraftingStationActor(station)).row();
            }
        }
    }
}
