package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Workshop;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;

import java.util.List;

public class CraftingScreen extends BaseInGameScreen {

    protected final Stage craftingStage;
    protected final HolderTable holdingTable;

    public CraftingScreen() {
        craftingStage = new Stage();
        setupStages(craftingStage);
        holdingTable = new HolderTable();
        craftingStage.addActor(holdingTable);
    }

    @Override
    protected void doRenderWork() {
        drawBgColor(0.2f, 0.2f, 0.8f, 1);
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }

    private class HolderTable extends Table {
        private HolderTable() {
            super(Tradesong.uiStyles);
            this.setFillParent(true);
            this.add("Worskhop List").colspan(2).row();
            this.add(new StationListing("Current Setup", new Workshop().getOrderedNodes())).align(Align.left + Align.top).pad(5);
            this.add(new StationListing("Available Stations", Tradesong.craftingStations.getNodesCopy())).align(Align.left + Align.top).pad(5);
            this.pad(20);
        }
    }

    private class StationListing extends Table {
        private StationListing(String name, List<BaseCraftingStation> stations) {
            super(Tradesong.uiStyles);
            this.add(name).row();
            for (BaseCraftingStation station : stations) {
                BaseCraftingStation.CraftingStationActor actor = station.getActor();
                this.add(actor).align(Align.left).space(10).prefHeight(64).prefWidth(200).row();
            }
        }
    }
}
