package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.utils.SpacingActor;

import java.util.List;

/**
 * Basic, modular piece of just the inventory. Consider description area here or on its own stage (probably here)
 * */
public class InventoryStage extends ResizableStage {
    private Table framesAndItems = new Table();

    public InventoryStage() {
        setupTable();
        addFramesAndItems();
        addDescriptionArea();
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
                framesAndItems.stack(frame, inventoryCopy.get(i - 1));
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


    }

    @Override
    public void layout() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
