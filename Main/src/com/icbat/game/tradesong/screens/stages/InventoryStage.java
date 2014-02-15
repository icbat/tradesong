package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.dragAndDrop.FrameTarget;
import com.icbat.game.tradesong.screens.dragAndDrop.ItemSource;
import com.icbat.game.tradesong.screens.listeners.InventoryClickListener;
import com.icbat.game.tradesong.utils.SpacedTable;

import java.util.List;

/**
 * The most basic inventory-showing stage. Also has description/name labels.
 */
public class InventoryStage extends BaseStage {
    private Label description;
    private Label itemName;
    Table inventoryTable; // Will likely need to findActor on this from somewhere else.
    DragAndDrop dragAndDrop = new DragAndDrop();

    @Override
    public void layout() {
        super.layout();

        this.clear();

        Table holdingTable = makeHoldingTable();
        holdingTable = holdingTable.debugTable();

        this.inventoryTable = makeInventoryTable();
        holdingTable.add(inventoryTable);
        holdingTable.row();
        holdingTable.add(makeSortButton());
        holdingTable.row();
        holdingTable.add(makeItemInfoTable());


        this.addActor(holdingTable);
    }

    private Actor makeSortButton() {
        TextButton sortButton = new TextButton("Sort!", Tradesong.uiStyles.getTextButtonStyle());
        sortButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Tradesong.inventory.sort();
                layout();
            }
        });
        return sortButton;
    }

    protected SpacedTable makeHoldingTable() {
        SpacedTable layout = new SpacedTable();
        layout.setFillParent(true);
        layout.top();
        layout.pad(62);
        return layout;
    }

    protected Table makeInventoryTable() { // TODO this is kind of big and clumsy, could it be rewritten?
        SpacedTable inventory = new SpacedTable();
        List<Item> inventoryCopy = Tradesong.inventory.getCopyOfInventory();

        for (int i = 1; i <= Tradesong.inventory.getMaxSize(); ++i) {
            if (i - 1 < inventoryCopy.size() && inventoryCopy.get(i - 1) != null) {
                Item item = inventoryCopy.get(i - 1);
                dragAndDrop.addSource(new ItemSource(item, this));
                item.addListener(new InventoryClickListener(item) {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        setItemName(this.owner.getName());
                        setDescription(this.owner.getDescription());
                    }
                });
                inventory.spacedStack(makeFrame(false), item);
            } else {
                inventory.spacedAdd(makeFrame(true));
            }

            if (i % 6 == 0) {
                inventory.spacedRow();
            }

        }
        return inventory;
    }

    protected Image makeFrame(boolean isDropTarget) {
        Image frame =  new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new InventoryTarget(frame, isDropTarget, this));
        return frame;
    }

    protected Table makeItemInfoTable() {
        Table itemInfoTable = new Table();
        itemName = makeItemName();
        description = makeDescription();

        itemInfoTable.stack(itemName);
        itemInfoTable.row();
        itemInfoTable.stack(description);

        return itemInfoTable;
    }

    private Label makeItemName() {
        return new Label("", Tradesong.uiStyles.getLabelStyle());
    }

    private Label makeDescription() {
        Label descriptionField = new Label("", Tradesong.uiStyles.getLabelStyle());
        descriptionField.setWrap(true);
        return descriptionField;
    }

    protected void setDescription(String newDesc) {
        description.setText(newDesc);
        Gdx.app.debug("description dimensions",description.getWidth() + ", " + description.getHeight());
    }

    protected void setItemName(String newName) {
        itemName.setText(newName);
        Gdx.app.debug("itemName dimensions", itemName.getWidth() + ", " + itemName.getHeight());
    }

    /**
     * This is for reordering inventory on that screen. Make a new one of these to transfer between lists.
     * */
    class InventoryTarget extends FrameTarget {
        public InventoryTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget, owner);
        }

        @Override
        /**
         * Yes, future-self; this _should_ remove and then re-add, because it's reordering.
         * */
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            Item item = Tradesong.inventory.takeOutItem(new Item((Item) payload.getObject()));
            Tradesong.inventory.addItem(item);
            super.drop(source, payload, x, y, pointer);
        }
    }

}