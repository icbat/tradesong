package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

import java.util.List;

public class ItemBox extends Table {

    TextureRegionDrawable background = new TextureRegionDrawable(new TextureRegion(Tradesong.getTexture(TextureAssets.POPUP_BG)));

    private ItemBox(List<Items.Item> backingList) {
        this.setBackground(background);
        this.setSize(Gdx.graphics.getWidth() / 2 , Gdx.graphics.getHeight());

        int columnCount = 0;
        for (Items.Item item : backingList) {
            item.addListener(new NameDisplayListener(item));
            this.add(item);
            columnCount++;
            if (columnCount == 6) {
                this.row();
                columnCount = 0;
            }
        }
    }

    public static ItemBox makeInventoryBox() {
        return new ItemBox(Tradesong.state.inventory().getCopyOfInventory());
    }

    public static ItemBox make(List<Items.Item> itemList) {
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
