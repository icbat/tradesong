package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.screens.components.ItemBox;

/***/
public class StorageScreen extends BaseInGameScreen {

    protected final ItemBox inventoryBox;
    protected final ItemBox storageBox;

    @Override
    public String getScreenName() {
        return "storage-screen";
    }

    protected final Table layout;

    public StorageScreen(BaseCraftingStation station) {
        Stage inventoryStage = new Stage();
        layout = new Table(Tradesong.uiStyles);

        layout.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        inventoryBox = ItemBox.makeInventoryBox(station.getReadyForOutput());
        layout.add(inventoryBox).row();
        storageBox = ItemBox.make(station.getStationName(), station.getReadyForOutput(), Tradesong.state.inventory().getEditableInventory());
        layout.add(storageBox);

        inventoryStage.addActor(layout);
        setupStages(inventoryStage);
    }

    @Override
    protected void doRenderWork() {
        drawBgColor(0, 0, 0, 1);
        inventoryBox.forceLayout();
        storageBox.forceLayout();
    }
}
