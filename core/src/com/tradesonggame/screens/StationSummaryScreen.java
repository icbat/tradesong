package com.tradesonggame.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tradesonggame.Tradesong;
import com.tradesonggame.gameObjects.craftingStations.BaseCraftingStation;

public class StationSummaryScreen extends BaseInGameScreen {
    public StationSummaryScreen(BaseCraftingStation station) {
        Stage stage = new Stage();
        Table layout = new Table(Tradesong.uiStyles);


        layout.add(station.getIcon());
        layout.add(station.getStationName()).row();

        layout.add(station.getDescription()).colspan(2).row();

        layout.add(station.getProcessDisplayTable()).colspan(2);

        stage.addActor(layout);
        layout.setFillParent(true);

        setupStages(stage);

    }

    @Override
    public String getScreenName() {
        return "stationSummary";
    }
}
