package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.listeners.InventoryClickListener;
import com.icbat.game.tradesong.utils.SpacingActor;

import java.util.List;

/**
 * Basic, modular piece of just the inventory. Consider description area here or on its own stage (probably here)
 */
public class InventoryStage extends BaseStage {
    private Label description = new Label("", Tradesong.uiStyles.getLabelStyle());
    private Label itemName = new Label("", Tradesong.uiStyles.getLabelStyle());

    public InventoryStage() {
        layout();
    }

    @Override
    public void layout() {
        super.layout();

        Table holdingTable = makeHoldingTable();
        holdingTable.add(makeInventoryTable());
        holdingTable.add(new SpacingActor());
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
    }

    private Table makeHoldingTable() {
        Table layout = new Table();
        layout.setFillParent(true);
        return layout;
    }

    private Table makeInventoryTable() {
        Table inventory = new Table();
        List<Item> inventoryCopy = Tradesong.inventory.getCopyOfInventory();

        for (int i = 1; i <= Tradesong.inventory.getMaxSize(); ++i) {
            Image frame = new Image(Tradesong.getTexture(TextureAssets.FRAME));
            if (i - 1 < inventoryCopy.size() && inventoryCopy.get(i - 1) != null) {
                Item item = inventoryCopy.get(i - 1);
                item.addListener(new InventoryClickListener(item) {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        setItemName(this.owner.getName());
                        setDescription(this.owner.getDescription());
                    }
                });
                inventory.stack(frame, item);
            } else {
                inventory.add(frame);
            }

            inventory.add(new SpacingActor());

            if (i % 6 == 0) {
                inventory.row();
                inventory.add(new SpacingActor());
                inventory.row();
            }

        }
        return inventory;
    }

    private Table makeItemInfoTable() {
        Table itemInfoTable = new Table();
        float tableWidth = 100;
        itemName = makeItemName(tableWidth);
        description = makeDescription(tableWidth);

        itemInfoTable.setWidth(tableWidth);
        itemInfoTable.stack(itemName);
        itemInfoTable.row();
        itemInfoTable.stack(description);

        return itemInfoTable;
    }

    private Label makeItemName(float tableWidth) {
        Label itemNameField = new Label("", Tradesong.uiStyles.getLabelStyle());
        itemNameField.setWidth(tableWidth);
        return itemNameField;
    }

    private Label makeDescription(float tableWidth) {
        Label descriptionField = new Label("", Tradesong.uiStyles.getLabelStyle());
        descriptionField.setWidth(tableWidth);
        descriptionField.setHeight(3*tableWidth);
        descriptionField.setWrap(true);
        return descriptionField;
    }

//    private void addDescriptionArea() {
//
//        float height = 300;
//
//        description.setHeight(height);
////        description.setWidth(descriptionWidth - 8);
//        description.setWrap(true);
//
//        Stack stack = new Stack();
//
//        float x = 8;
//        float y = 100;
//        stack.setPosition(x, y);
//
//        stack.add(description);
//
//        this.addActor(stack);
//
//    }
//
//    private void addItemName() {
//        float x = description.getX();
////        itemName.setWidth(descriptionWidth);
//        float y = (Gdx.graphics.getHeight() - 100);
//
//        itemName.setPosition(x, y);
//
//        Stack stack = new Stack();
//        stack.setPosition(x, y);
//        stack.add(itemName);
//        this.addActor(stack);
//    }

    void setDescription(String newDesc) {
        description.setText(newDesc);
        Gdx.app.debug("description dimensions",description.getWidth() + ", " + description.getHeight());
    }

    void setItemName(String newName) {
        itemName.setText(newName);
        Gdx.app.debug("itemName dimensions", itemName.getWidth() + ", " + itemName.getHeight());
    }


}
