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
        ItemBox linkedBox = ItemBox.make(linkedTo.getReadyForOutput());
        addBox(linkedTo.getStationName(), linkedBox);
        linkBoxes(inventory, linkedBox);
    }

    public void addBox(String boxName, ItemBox box) {
        addBoxTitle(boxName);
        displayBox(box);
    }

    private void displayBox(ItemBox box) {
        layoutTable.add(box.makeTable()).row();
    }

    public void addBoxTitle(String boxName) {
        layoutTable.add(boxName).space(10).row();
    }


    private void linkBoxes(ItemBox inventory, ItemBox linkedBox) {
//        inventory.
    }

    @Override
    public String getScreenName() {
        return "linkedChests";
    }
}
