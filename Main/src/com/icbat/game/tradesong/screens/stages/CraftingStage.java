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
    private int craftingTableCapacity = 2;
    private List<Item> craftingTableContents = new ArrayList<Item>(craftingTableCapacity);

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

        for (int i = 0; i < craftingTableCapacity; ++i) {
            if (i < craftingTableContents.size()) {
                craftingTable.spacedStack(makeCraftingFrame(false), craftingTableContents.get(i));
            } else {
                craftingTable.spacedAdd(makeCraftingFrame(true));
            }

        }
        craftingTable.spacedAdd(makeArrow());
        craftingTable.spacedAdd(makeCraftingFrame(false));

        return craftingTable;
    }

    private Image makeArrow() {
        return new Image(Tradesong.getTexture(TextureAssets.WORKSHOP_ARROW));
    }

    protected Image makeCraftingFrame(boolean isDropTarget) {
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
            craftingTableContents.add((Item) payload.getObject());
            layout();
        }
    }
}
