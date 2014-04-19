package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.collections.Items;

/***/
public class ItemDescriptionPopup extends Table {

    public ItemDescriptionPopup(Items.Item item) {
        super(Tradesong.uiStyles);
        this.setBackground( new TextureRegionDrawable(new TextureRegion(Tradesong.getTexture(TextureAssets.POPUP_BG))));

        this.add(item.getName()).row();
        this.add(item.getDescription()).row();
        this.add(item.getRarity().toString()).row();
    }
}
