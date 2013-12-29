package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import gameObjects.Item;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.dragAndDrop.FrameTarget;
import com.icbat.game.tradesong.utils.SpacedTable;
import gameObjects.Workshop;

import java.util.ArrayList;
import java.util.List;

/***/
public class CraftingStage extends InventoryStage {

    // These two are here to let the Drag and Drop listeners know how to deal with them
    protected int craftingTableCapacity = 3;
    protected List<Item> craftingTableContents = new ArrayList<Item>(craftingTableCapacity);
    protected Workshop currentWorkshop = Tradesong.workshopListing.getWorkshop("Blacksmith");
    private Label workshopNameLabel;

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
        holdingTable.add(makeCraftButton());
        holdingTable.spacedRows(3);
        holdingTable.add(makeWorkshopNameDisplay());
        holdingTable.spacedRows(3);
        holdingTable.add(makeItemInfoTable());
        this.addActor(holdingTable);
    }

    private Actor makeCraftButton() {
        TextButton craftButton = new TextButton("Craft!", Tradesong.uiStyles.getTextButtonStyle());
        craftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Trying to craft with", craftingTableContents.toString());
                Item output = currentWorkshop.getOutput(craftingTableContents);
                if (output != null) {
                    // TODO lock, timer
                    Gdx.app.log("crafted", output.getName());
                    craftingTableContents.clear();
                    Tradesong.inventory.addItem(output);
                    layout();

                }
            }
        });
        return craftButton;
    }

    private Actor makeWorkshopNameDisplay() {
        workshopNameLabel = new Label("", Tradesong.uiStyles.getLabelStyle());
        updateWorkshopName();
        return workshopNameLabel;
    }

    private void updateWorkshopName() {
        workshopNameLabel.setText(currentWorkshop.getName());
    }

    private SpacedTable makeWorkshopSwitchingTable() {
        SpacedTable workshopSwitcher = new SpacedTable();

        Texture iconSheet = Tradesong.getTexture(TextureAssets.ITEMS);
        final int ICON_DIMENSIONS = 32;

        // TODO clean up this ish
        Image blacksmith = new Image(new TextureRegion(iconSheet, 14 * ICON_DIMENSIONS, 3 * ICON_DIMENSIONS,ICON_DIMENSIONS,ICON_DIMENSIONS));
        Image tinker = new Image(new TextureRegion(iconSheet, 14 * ICON_DIMENSIONS, 4 * ICON_DIMENSIONS,ICON_DIMENSIONS,ICON_DIMENSIONS));

        blacksmith.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setWorkshop("Blacksmith");
            }
        });

        tinker.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setWorkshop("Tinker");
            }
        });

        workshopSwitcher.spacedAdd(blacksmith);
        workshopSwitcher.spacedAdd(tinker);

        return workshopSwitcher;
    }

    private void setWorkshop(String workshopName) {
        currentWorkshop = Tradesong.workshopListing.getWorkshop(workshopName);
        updateWorkshopName();
        Gdx.app.debug(currentWorkshop.getName() + " recipes", currentWorkshop.getRecipes().toString());
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

        return craftingTable;
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
