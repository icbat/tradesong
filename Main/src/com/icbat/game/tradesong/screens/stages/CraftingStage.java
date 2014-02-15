package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.dragAndDrop.FrameTarget;
import com.icbat.game.tradesong.screens.dragAndDrop.ItemSource;
import com.icbat.game.tradesong.utils.SpacedTable;
import com.icbat.game.tradesong.gameObjects.Workshop;

import java.util.ArrayList;
import java.util.List;

/***/
public class CraftingStage extends InventoryStage {

    // These two are here to let the Drag and Drop listeners know how to deal with them
    protected int craftingTableCapacity = 3;
    protected List<Item> craftingTableContents = new ArrayList<Item>(craftingTableCapacity);
    protected Workshop currentWorkshop = Tradesong.workshopListing.getWorkshop("Blacksmith");
    private Label workshopNameLabel;

    @Override
    public void layout() {
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
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
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
                craftingTable.spacedStack(makeCraftingFrame(false), item);
                dragAndDrop.addSource(new ItemSource(item, this));
            } else {
                craftingTable.spacedAdd(makeCraftingFrame(true));
            }

        }

        return craftingTable;
    }

    protected Image makeCraftingFrame(boolean isDropTarget) {
        Image frame =  new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new CraftingTarget(frame, isDropTarget, this));

        return frame;
    }

    @Override
    protected Image makeFrame(boolean isDropTarget) {
        Image frame = new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new CraftingInventoryTarget(frame, isDropTarget, this));
        return frame;
    }

    /**
     *  "overridden" way of interacting with the Inventory box on this screen.
     * */
    class CraftingInventoryTarget extends FrameTarget {
        public CraftingInventoryTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget, owner);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            Item item = (Item) payload.getObject();
            craftingTableContents.remove(item);
            Tradesong.inventory.addItem(item);

            super.drop(source, payload, x, y, pointer);
        }
    }

    class CraftingTarget extends FrameTarget {
        public CraftingTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget, owner);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            Tradesong.inventory.takeOutItem((Item) payload.getObject());
            craftingTableContents.add(new Item((Item) payload.getObject()));
            super.drop(source, payload, x, y, pointer);
        }
    }
}
