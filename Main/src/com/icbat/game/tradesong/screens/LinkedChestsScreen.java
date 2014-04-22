package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;
import com.icbat.game.tradesong.screens.components.ItemBox;

public class LinkedChestsScreen extends BaseInGameScreen {

    private final ItemBox inventory = ItemBox.makeInventoryBox();
    private ItemBox linkedBox = null;
    protected Stage mainStage;

    public LinkedChestsScreen() {
        this(null);
    }

    public LinkedChestsScreen(Storage linkedTo) {
        mainStage = new Stage();
        displayInventory();
        if (linkedTo != null) {
            this.linkedBox = ItemBox.make(linkedTo.getReadyForOutput());
            displayLinkedBox();
            linkBoxes(inventory, this.linkedBox);
        }

        setupStages(mainStage);
    }

    private void displayInventory() {

    }

    private void displayLinkedBox() {

    }

    private void linkBoxes(ItemBox inventory, ItemBox linkedBox) {

    }

    @Override
    public String getScreenName() {
        return "linkedChests";
    }
}
