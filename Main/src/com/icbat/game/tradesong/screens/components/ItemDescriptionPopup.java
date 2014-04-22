package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.collections.Items;

public class ItemDescriptionPopup extends Table {

    public ItemDescriptionPopup(Items.Item item) {
        super(Tradesong.uiStyles);
        Label description = new Label(item.getDescription(), Tradesong.uiStyles);
        description.setWrap(true);

        NinePatch bgPatch = new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2);
        bgPatch.setColor(item.getRarity().getColor());
        this.setBackground(new NinePatchDrawable(bgPatch));
        this.add(new Image(item.getIcon())).prefWidth(32).space(5);
        this.add(item.getName()).prefWidth(125).space(5).row();
        this.add(description).colspan(2).prefWidth(125).space(5).row();
        this.add(item.getRarity().toString()).colspan(2).prefWidth(125).space(5).row();
    }
}
