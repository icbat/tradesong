package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.listeners.InventoryClickListener;
import com.icbat.game.tradesong.utils.SpacingActor;

import java.util.List;

/**
 * Basic, modular piece of just the inventory. Consider description area here or on its own stage (probably here)
 * */
public class InventoryStage extends BaseStage {
    private Table framesAndItems = new Table();
    private Label description = new Label("", Tradesong.uiStyles.getLabelStyle());
    private Label itemName = new Label("", Tradesong.uiStyles.getLabelStyle());
    private int descriptionWidth;

    public InventoryStage() {
        setupTable();
        addFramesAndItems();
        addDescriptionArea();
        addItemName();
    }

    private void setupTable() {
        framesAndItems.setFillParent(true);
        framesAndItems.align(Align.left);
        framesAndItems.padLeft(32);

        this.addActor(framesAndItems);
    }

    private void addFramesAndItems() {
        List<Item> inventoryCopy = Tradesong.inventory.getCopyOfInventory();
        Gdx.app.debug("inventorySize", inventoryCopy.size() + "");

        for (int i=1; i <= Tradesong.inventory.getMaxSize(); ++i) {
            Gdx.app.debug("i", i + "");
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
                framesAndItems.stack(frame, item);
            } else {
                framesAndItems.add(frame);
            }

            framesAndItems.add(new SpacingActor());

            if (i % 6 == 0) {
                framesAndItems.row();
                framesAndItems.add(new SpacingActor());
                framesAndItems.row();
            }

        }

    }


    private void addDescriptionArea() {
        descriptionWidth = 100;
        description.setWidth(descriptionWidth);
        int x = Gdx.graphics.getWidth() - descriptionWidth;
        description.setPosition(x, 100);
        description.setWrap(true);
        this.addActor(description);

    }

    private void addItemName() {
        int x = (int) description.getX();
        itemName.setWidth(descriptionWidth);
        int y = (int) (Gdx.graphics.getHeight() - itemName.getHeight() * 2);

        itemName.setPosition(x, y);
        this.addActor(itemName);
    }

    void setDescription(String newDesc) {
        description.setText(newDesc);
    }

    void setItemName(String newName) {
        itemName.setText(newName);
    }

    @Override
    public void layout() {
        super.layout();
        description.remove();
        addDescriptionArea();

        itemName.remove();
        addItemName();
    }
}