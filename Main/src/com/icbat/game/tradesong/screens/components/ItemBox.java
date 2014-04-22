package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.collections.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemBox extends Table {

    private final List<String> backingList;
    private List<String> linkedBackingList;

    private ItemBox(List<String> backingList) {
        super(Tradesong.uiStyles);
        this.backingList = backingList;
        NinePatchDrawable background = new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2));
        this.setBackground(background);
    }

    private ItemBox(List<String> backingList, List<String> linkedBackingList) {
        this(backingList);
        this.linkedBackingList = linkedBackingList;
    }

    public void forceLayout() {
        this.clear();
        int columnCount = 0;

        ArrayList<Items.Item> itemsByName = Tradesong.items.getItemsByName(backingList);

        for (Items.Item item : itemsByName) {
            item.addListener(new NameDisplayListener(item));
            if (linkedBackingList != null) {
            }
            this.add(item);
            columnCount++;
            if (columnCount == 6) {
                this.row();
                columnCount = 0;
            }
        }
    }

    public static ItemBox makeInventoryBox() {
        return make(Tradesong.state.inventory().getEditableInventory());
    }

    public static ItemBox makeInventoryBox(List<String> linkedBackingList) {
        return make(Tradesong.state.inventory().getEditableInventory(), linkedBackingList);
    }

    public static ItemBox make(List<String> backingList, List<String> linkedBackingList) {
        return new ItemBox(backingList, linkedBackingList);
    }

    public static ItemBox make(List<String> itemList) {
        return new ItemBox(itemList);
    }

    private class NameDisplayListener extends ClickListener {
        private final Items.Item item;

        public NameDisplayListener(Items.Item item) {
            this.item = item;
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.enter(event, x, y, pointer, fromActor);
            item.setColor(Color.YELLOW);
            Tradesong.focusedItem = new ItemDescriptionPopup(item);
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            super.exit(event, x, y, pointer, toActor);
            item.setColor(Color.WHITE);
            Tradesong.focusedItem = null;
        }
    }
}
