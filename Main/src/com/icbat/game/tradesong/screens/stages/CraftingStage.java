package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.utils.SpacedTable;

/***/
public class CraftingStage extends InventoryStage {
    @Override
    public void layout() {
        SpacedTable holdingTable = makeHoldingTable();
        holdingTable.add(makeInventoryTable());
        holdingTable.spacedRows(3);
        holdingTable.add(makeWorkshopSwitchingTable());
        holdingTable.spacedRows(3);
        holdingTable.add(makeCraftingTable());
        holdingTable.spacedRows(3);
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
    }

    private SpacedTable makeWorkshopSwitchingTable() {
        SpacedTable workshopSwitcher = new SpacedTable();

        Image blacksmith = new Image(Tradesong.getTexture(TextureAssets.ICON_HAMMER));
        Image tinker = new Image(Tradesong.getTexture(TextureAssets.ICON_WRENCH));
        Image scribe = new Image(Tradesong.getTexture(TextureAssets.ICON_BOOK));

        workshopSwitcher.spacedAdd(blacksmith);
        workshopSwitcher.spacedAdd(tinker);
        workshopSwitcher.spacedAdd(scribe);


        return workshopSwitcher;
    }

    private SpacedTable makeCraftingTable() {
        SpacedTable craftingTable = new SpacedTable();

        craftingTable.spacedAdd(makeFrame());
        craftingTable.spacedAdd(makeFrame());
        craftingTable.spacedAdd(makeArrow());
        craftingTable.spacedAdd(makeFrame(false));

        return craftingTable;
    }

    private Image makeArrow() {
        return new Image(Tradesong.getTexture(TextureAssets.WORKSHOP_ARROW));
    }

}
