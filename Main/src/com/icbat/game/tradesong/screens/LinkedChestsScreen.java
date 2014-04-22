package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;
import com.icbat.game.tradesong.screens.components.ItemBox;

public class LinkedChestsScreen extends BaseInGameScreen {

    private Table layoutTable;
    protected ItemBox inventory;

    public LinkedChestsScreen() {
        Stage mainStage = new Stage();
        layoutTable = new Table(Tradesong.uiStyles);
        layoutTable.setFillParent(true);
        inventory = ItemBox.makeInventoryBox();
        addBox("Inventory", inventory);
        mainStage.addActor(layoutTable);
        setupStages(mainStage);
    }

    public LinkedChestsScreen(Storage linkedTo) {
        this();
        if (linkedTo != null) {
            ItemBox linkedBox = ItemBox.make(linkedTo.getReadyForOutput());
            addBox(linkedTo.getStationName(), linkedBox);
            linkBoxes(inventory, linkedBox);
        }
    }

    public void addBox(String boxName, ItemBox box) {
        addBoxTitle(boxName);
        displayBox(box);
    }

    private void displayBox(ItemBox box) {

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
