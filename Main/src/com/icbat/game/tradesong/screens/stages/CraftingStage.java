package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.utils.SpacedTable;

import java.util.ArrayList;
import java.util.List;

/***/
public class CraftingStage extends InventoryStage {
    private List<Item> itemsOnCraftingStage = new ArrayList<Item>();

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

    @Override
    protected Image makeFrame(boolean isDropTarget) {
        Image frame =  new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new CraftingFrameTarget(frame, isDropTarget));

        return frame;
    }

    class CraftingFrameTarget extends InventoryFrameTarget {


        public CraftingFrameTarget(Actor actor, boolean validTarget) {
            super(actor, validTarget);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

        }
    }
}
