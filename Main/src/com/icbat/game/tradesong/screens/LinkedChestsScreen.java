package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;
import com.icbat.game.tradesong.screens.components.ItemBox;

public class LinkedChestsScreen extends BaseInGameScreen {

    private Table layoutTable;
    protected ItemBox inventory;
    protected Stage mainStage = new Stage();
    protected ItemBox linkedBox;
    private String linkedBoxName;

    public LinkedChestsScreen() {
        setupStages(mainStage);
        this.linkedBox = null;
        this.linkedBoxName = null;
        relayout();
    }

    public LinkedChestsScreen(Storage linkedTo) {
        this();
        this.linkedBox = ItemBox.make(linkedTo.getReadyForOutput());
        this.linkedBoxName = linkedTo.getStationName();
        relayout();
    }

    private void relayout() {
        mainStage.clear();
        layoutTable = new Table(Tradesong.uiStyles);
        layoutTable.setFillParent(true);
        inventory = ItemBox.makeInventoryBox();
        addBox("Inventory", inventory);
        mainStage.addActor(layoutTable);

        if (linkedBox != null) {
            addBox(linkedBoxName, linkedBox);
            linkBoxes(inventory, linkedBox);
        }
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
        linkLeftToRight(inventory, linkedBox);
        linkLeftToRight(linkedBox, inventory);
    }

    private void linkLeftToRight(ItemBox inventory, ItemBox linkedBox) {
        for (Actor actor : inventory.getChildren()) {
            actor.addListener(new LinkingListener((Items.Item) actor, inventory, linkedBox));
        }
    }

    @Override
    public String getScreenName() {
        return "linkedChests";
    }

    private class LinkingListener extends ClickListener {
        private final Items.Item owner;
        private final ItemBox owningBox;
        private final ItemBox linkedBox;

        public LinkingListener(Items.Item owner, ItemBox owningBox, ItemBox linkedBox) {
            this.owner = owner;
            this.owningBox = owningBox;
            this.linkedBox = linkedBox;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            owningBox.removeItem(owner.getName());
            owner.remove();
            linkedBox.addItem(owner.getName());
            Gdx.app.debug("linkedBox Before layout", linkedBox.toString());
            relayout();
            Gdx.app.debug("linkedBox after layout", linkedBox.toString());
        }
    }


}
