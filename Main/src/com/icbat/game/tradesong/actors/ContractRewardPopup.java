package com.icbat.game.tradesong.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Contract;

/***/
public class ContractRewardPopup extends Table {

    public ContractRewardPopup(Contract contractCompleted) {
        this.add(makeContentTable());
        this.setFillParent(true);
    }

    private Table makeContentTable() {
        Table contentTable = new Table();

        contentTable.add(new Label("Contract completed!", Tradesong.uiStyles.getLabelStyle()));
        contentTable.setBackground(new TextureRegionDrawable(new TextureRegion(Tradesong.getTexture(TextureAssets.POPUP_BG))));
        contentTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                removeThis();
            }
        });

        contentTable.setSize(512, 256); // BG dimensions
        return contentTable;
    }

    private Actor makeDescriptionText() {
        return null;
    }



    protected void removeThis() {

    }
}
