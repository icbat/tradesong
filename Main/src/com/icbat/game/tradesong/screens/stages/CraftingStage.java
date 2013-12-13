package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.utils.SpacingActor;

/***/
public class CraftingStage extends InventoryStage {
    @Override
    public void layout() {
        Table holdingTable = makeHoldingTable();
        holdingTable.add(makeInventoryTable());
        holdingTable.row();
        for (int i=0; i<3; ++i) { // TODO feels dirty, clean up.
            holdingTable.add(new SpacingActor());
            holdingTable.row();
        }
        holdingTable.add(makeCraftingTable());
        holdingTable.row();
        for (int i=0; i<3; ++i) { // TODO feels dirty, clean up.
            holdingTable.add(new SpacingActor());
            holdingTable.row();
        }
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
    }

    private Table makeCraftingTable() {
        Table craftingTable = new Table();

        craftingTable.add(makeFrame());
        craftingTable.add(new SpacingActor());
        craftingTable.add(makeFrame());
        craftingTable.add(new SpacingActor());
        craftingTable.add(makeArrow());
        craftingTable.add(new SpacingActor());
        craftingTable.add(makeFrame());



        return craftingTable;
    }

    private Image makeArrow() {
        return new Image(Tradesong.getTexture(TextureAssets.WORKSHOP_ARROW));
    }
}
