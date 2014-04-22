package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;
import com.icbat.game.tradesong.screens.components.ItemBox;

public class LinkedChestsScreen extends BaseInGameScreen {

    private Table layoutTable;

    public LinkedChestsScreen() {
        this(null);
    }

    public LinkedChestsScreen(Storage linkedTo) {
        Stage mainStage = new Stage();
        layoutTable = new Table(Tradesong.uiStyles);
        layoutTable.setFillParent(true);
        ItemBox inventory = ItemBox.makeInventoryBox();
        addBox("Inventory", inventory);
        if (linkedTo != null) {
            ItemBox linkedBox = ItemBox.make(linkedTo.getReadyForOutput());
            addBox(linkedTo.getStationName(), linkedBox);
            linkBoxes(inventory, linkedBox);
        }

        mainStage.addActor(layoutTable);
        setupStages(mainStage);
    }

    public void addBox(String boxName, ItemBox box) {
        addBoxTitle(boxName);
        displayBox(box);
    }

    private void displayBox(ItemBox inventory) {

    }

    public void addBoxTitle(String boxName) {
        layoutTable.add(boxName).spaceBottom(10).row();
    }


    private void linkBoxes(ItemBox inventory, ItemBox linkedBox) {

    }

    @Override
    public String getScreenName() {
        return "linkedChests";
    }
}
