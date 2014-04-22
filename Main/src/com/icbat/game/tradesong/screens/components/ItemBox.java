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

    private ItemBox(List<String> backingList) {
        super(Tradesong.uiStyles);
        this.backingList = backingList;
        NinePatchDrawable background = new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2));
        this.setBackground(background);
        this.pad(5);
    }

    public static ItemBox makeInventoryBox() {
        return make(Tradesong.state.inventory().getEditableInventory());
    }

    public static ItemBox make(List<String> itemList) {
        return new ItemBox(itemList);
    }

    public Table makeTable() {
        ArrayList<Items.Item> itemsByName = Tradesong.items.getItemsByName(this.backingList);
        int i=0;
        for (Items.Item itemInBox : itemsByName) {
            itemInBox.addListener(new NameDisplayListener(itemInBox));
            this.add(itemInBox).space(5);
            ++i;
            if (i==6) {
                this.row();
                i = 0;
            }
        }

        return this;
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
