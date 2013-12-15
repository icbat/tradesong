package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
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
            Image frame = makeFrame();
            frame.setName(String.valueOf(i));
            if (i - 1 < inventoryCopy.size() && inventoryCopy.get(i - 1) != null) {
                Item item = inventoryCopy.get(i - 1);
                dragAndDrop.addSource(new ItemSource(item));
                item.addListener(new InventoryClickListener(item) {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        setItemName(this.owner.getName());
                        setDescription(this.owner.getDescription());
                    }
                });
                inventory.spacedStack(frame, item);
            } else {
                inventory.spacedAdd(frame);
            }

            if (i % 6 == 0) {
                inventory.spacedRow();
            }

        }
        return inventory;
    }

    protected Image makeFrame() {
        return makeFrame(true);
    }

    protected Image makeFrame(boolean isDropTarget) {
        Image frame =  new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new FrameTarget(frame, isDropTarget));

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

    class FrameTarget extends DragAndDrop.Target {

        private boolean validTarget;

        FrameTarget(Actor actor, boolean isValidTarget) {
            super(actor);
            validTarget = isValidTarget;
        }

        @Override
        /**
         * What happens when you drag something over this.
         * */
        public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            if (validTarget) {
                getActor().setColor(Color.GREEN);
            } else {
                getActor().setColor(Color.RED);
            }
            return validTarget;
        }

        @Override
        public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
            super.reset(source, payload);
            getActor().setColor(Color.BLACK);
        }

        @Override
        /**
         * What happens when you drop something here.
         * */
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            // TODO impl
        }
    }

    /**
     * Class for items that can be picked up.
     * */
    class ItemSource extends DragAndDrop.Source {

        public ItemSource(Actor actor) {
            super(actor);
        }

        @Override
        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
            DragAndDrop.Payload payload = new DragAndDrop.Payload();

            payload.setObject("some payload"); // This is probably actually the Item, but let's try
            payload.setDragActor(getActor());

            return payload;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }


}
