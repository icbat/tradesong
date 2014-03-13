package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.gameObjects.Workshop;
import com.icbat.game.tradesong.observation.notifications.CraftAttemptedNotification;
import com.icbat.game.tradesong.observation.notifications.CraftingBenchSwappedNotification;
import com.icbat.game.tradesong.observation.watchers.CraftAttemptedWatcher;
import com.icbat.game.tradesong.observation.watchers.CraftingBenchSwapWatcher;
import com.icbat.game.tradesong.screens.dragAndDrop.ItemSource;
import com.icbat.game.tradesong.utils.SpacedTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CraftingStage extends InventoryStage {

    protected int craftingTableCapacity = 3;
    protected List<Item> craftingTableContents = new ArrayList<Item>(craftingTableCapacity);
    protected Workshop currentWorkshop = Tradesong.workshopListing.getWorkshop("Blacksmith");
    private Label workshopNameLabel;

    @Override
    public void layout() {
        super.layout();
        notificationCenter.addWatcher(new CraftingBenchSwapWatcher());
        notificationCenter.addWatcher(new CraftAttemptedWatcher());
        this.clear();
        SpacedTable holdingTable = makeHoldingTable();
        holdingTable.add(makeInventoryTable());
        holdingTable.spacedRows(3);
        holdingTable.add(makeCraftingTable());
        holdingTable.spacedRows(3);
        holdingTable.add(makeCraftButton());
        holdingTable.spacedRows(3);
        holdingTable.add(makeWorkshopNameDisplay());
        holdingTable.spacedRows(3);
        holdingTable.add(makeWorkshopToggles());
        holdingTable.spacedRows(3);
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
    }

    private Actor makeWorkshopToggles() {
        SpacedTable table = new SpacedTable();

        for (Map.Entry<String, Workshop> entry : Tradesong.workshopListing.getWorkshops().entrySet()) {
            Workshop workshop = entry.getValue();
            Image workshopButton = new Image(workshop.getSprite());
            workshopButton.addListener(new WorkshopSwapperListener(workshop));
            workshopButton.setTouchable(Touchable.enabled);
            table.spacedAdd(workshopButton);
            Label workshopLabel = new Label(workshop.getName(), Tradesong.uiStyles.getLabelStyle());
            workshopLabel.addListener(new WorkshopSwapperListener(workshop));
            table.spacedAdd(workshopLabel);
        }

        return table;
    }

    @Override
    public void hide() {
        super.hide();
        for (Item itemOnTable : craftingTableContents) {
            Tradesong.inventory.addItem(itemOnTable);
        }
    }

    private Actor makeCraftButton() {
        TextButton craftButton = new TextButton("Craft!", Tradesong.uiStyles.getTextButtonStyle());
        craftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Trying to craft with", craftingTableContents.toString());
                Item output = currentWorkshop.getOutput(craftingTableContents);
                notificationCenter.notifyWatchers(new CraftAttemptedNotification(output));
                if (output != null) {
                    Gdx.app.log("crafted", output.getName());
                    craftingTableContents.clear();
                    Tradesong.inventory.addItem(output);
                    layout();
                }
            }
        });
        return craftButton;
    }

    private Actor makeWorkshopNameDisplay() {
        workshopNameLabel = new Label("", Tradesong.uiStyles.getLabelStyle());
        updateWorkshopName();
        return workshopNameLabel;
    }

    private void updateWorkshopName() {
        workshopNameLabel.setText(currentWorkshop.getName());
    }

    private SpacedTable makeCraftingTable() {
        SpacedTable craftingTable = new SpacedTable();

        for (int i = 0; i < craftingTableCapacity; ++i) {
            if (i < craftingTableContents.size()) {
                Item item = craftingTableContents.get(i);
                craftingTable.spacedStack(makeFrame(false), item);
                dragAndDrop.addSource(new ItemSource(item, this, craftingTableContents));
            } else {
                craftingTable.spacedAdd(makeFrame(true, craftingTableContents));
            }

        }

        return craftingTable;
    }

    private class WorkshopSwapperListener extends ClickListener {
        private final Workshop owner;

        public WorkshopSwapperListener(Workshop owner) {
            this.owner = owner;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            currentWorkshop = owner;
            updateWorkshopName();
            notificationCenter.notifyWatchers(new CraftingBenchSwappedNotification());
        }
    }
}
