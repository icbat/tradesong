package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.screens.dragAndDrop.FrameTarget;
import com.icbat.game.tradesong.screens.dragAndDrop.ItemSource;
import com.icbat.game.tradesong.utils.SpacedTable;

import java.util.LinkedList;
import java.util.List;

/***/
public class ShippingStage extends InventoryStage {
    protected Label contractLabel = new Label("", Tradesong.uiStyles.getLabelStyle());
    private static final int SHIPMENT_BOX_CAPACITY = 6;
    private List<Item> shipmentBoxContents = new LinkedList<Item>();
    private TextButton shipButton = new TextButton("Ship it!", Tradesong.uiStyles.getDisabledButtonStyle());
    private Contract contractMatched;

    @Override
    public void layout() {
        this.clear();

        SpacedTable layoutTable = new SpacedTable();

        layoutTable.add(new Label("Outgoing shipments", Tradesong.uiStyles.getLabelStyle()));
        layoutTable.spacedRows(3);
        layoutTable.add(makeInventoryTable());
        layoutTable.spacedRows(4);
        layoutTable.add(makeShipmentTable());
        layoutTable.spacedRow();
        layoutTable.add(makeCheckButton());
        layoutTable.spacedRow();
        layoutTable.add(shipButton);
        layoutTable.spacedRow();
        layoutTable.add(contractLabel);

        layoutTable.setFillParent(true);
        this.addActor(layoutTable);
    }

    @Override
    public void hide() {
        super.hide();
        for (Item itemOnTable : shipmentBoxContents) {
            Tradesong.inventory.addItem(itemOnTable);
        }
    }

    private Actor makeShipmentTable() {
        SpacedTable shipmentTable = new SpacedTable();

        for (int i = 0; i < SHIPMENT_BOX_CAPACITY; ++i) {
            if (i < shipmentBoxContents.size()) {
                Item item = shipmentBoxContents.get(i);
                shipmentTable.spacedStack(makeShipmentFrame(false), item);
                dragAndDrop.addSource(new ItemSource(item, this, this.shipmentBoxContents));
            } else {
                shipmentTable.spacedAdd(makeShipmentFrame(true));
            }

        }

        return shipmentTable;
    }

    protected Image makeShipmentFrame(boolean isDropTarget) {
        Image frame =  new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new ShipmentTarget(frame, isDropTarget, this));
        return frame;
    }

    private Actor makeCheckButton() {
        TextButton checkButton = new TextButton("Check for a match", Tradesong.uiStyles.getTextButtonStyle());
        checkButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                for (Contract contract : Tradesong.contractList) {
                    if (contract.canComplete(shipmentBoxContents)) {
                        toggleCompleteButton(true);
                        contractMatched = contract;
                        updateContractCheck(contractMatched.toString());
                        return;
                    }
                }
                toggleCompleteButton(false);
            }
        });
        return checkButton;
    }

    /**
     * Turns the complet button shouldBeOn or off depending shouldBeOn input.
     *
     * @param shouldBeOn Whether the button should turn shouldBeOn
     * */
    private void toggleCompleteButton(boolean shouldBeOn) {
        if (shouldBeOn) {
            shipButton.setStyle(Tradesong.uiStyles.getTextButtonStyle());
            shipButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if (contractMatched.completeContract(shipmentBoxContents))
                        shipmentBoxContents.clear();

                    layout();
                }
            });
        } else {
            shipButton.setStyle(Tradesong.uiStyles.getDisabledButtonStyle());
        }
    }

    public void updateContractCheck(String whatHits) {
        contractLabel.setText(whatHits);
    }

    @Override
    protected Image makeFrame(boolean isDropTarget) {
        Image frame = new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new ShippingInventoryTarget(frame, isDropTarget, this));
        return frame;
    }

    /**
     *  "overridden" way of interacting with the Inventory box on this screen.
     * */
    class ShippingInventoryTarget extends FrameTarget {
        public ShippingInventoryTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            Item item = (Item) payload.getObject();
            shipmentBoxContents.remove(item);
            Tradesong.inventory.addItem(item);

            super.drop(source, payload, x, y, pointer);
        }
    }

    class ShipmentTarget extends FrameTarget {
        public ShipmentTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            Tradesong.inventory.takeOutItem((Item) payload.getObject());
            Item itemAdded = new Item((Item) payload.getObject());
            shipmentBoxContents.add(itemAdded);
            super.drop(source, payload, x, y, pointer);
        }
    }
}
