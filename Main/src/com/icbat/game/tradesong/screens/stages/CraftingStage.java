package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.dragAndDrop.FrameTarget;
import com.icbat.game.tradesong.utils.SpacedTable;

import java.util.ArrayList;
import java.util.List;

/***/
public class CraftingStage extends InventoryStage {

    // These two are here to let the Drag and Drop listeners know how to deal with them
    protected int craftingTableCapacity = 3;
    protected List<Item> craftingTableContents = new ArrayList<Item>(craftingTableCapacity);

    @Override
    public void layout() {
        this.clear();
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
        dragAndDrop.addTarget(new CraftingTarget(frame, isDropTarget, this));

        return frame;
    }

    @Override
    protected Image makeFrame(boolean isDropTarget) {
        Image frame = new Image(Tradesong.getTexture(TextureAssets.FRAME));
        dragAndDrop.addTarget(new CraftingInventoryTarget(frame, isDropTarget, this));
        return frame;
    }

    class CraftingInventoryTarget extends FrameTarget {
        public CraftingInventoryTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget, owner);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            craftingTableContents.remove((Item) payload.getObject());
            Item removed = Tradesong.inventory.takeOutItem((Item) payload.getObject());
            Tradesong.inventory.addItem(removed);

            super.drop(source, payload, x, y, pointer);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    class CraftingTarget extends FrameTarget {
        public CraftingTarget(Actor actor, boolean validTarget, BaseStage owner) {
            super(actor, validTarget, owner);
        }

        @Override
        public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
            craftingTableContents.remove((Item) payload.getObject());
            Tradesong.inventory.takeOutItem((Item) payload.getObject());
            craftingTableContents.add((Item) payload.getObject());
            super.drop(source, payload, x, y, pointer);
        }
    }
}
