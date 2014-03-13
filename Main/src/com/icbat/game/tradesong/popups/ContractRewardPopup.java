package com.icbat.game.tradesong.popups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.utils.SpacedTable;

/***/
public class ContractRewardPopup extends Table {

    public ContractRewardPopup(Contract contractCompleted) {
        this.add(makeContentTable(contractCompleted));
        this.setFillParent(true);
    }

    private Table makeContentTable(Contract contractCompleted) {
        SpacedTable contentTable = new SpacedTable();

        contentTable.add(new Label("Contract completed!", Tradesong.uiStyles.getLabelStyle()));
        contentTable.spacedRows(2);
        contentTable.add(new Label("Rewards:  " + contractCompleted.getRewardMoney(), Tradesong.uiStyles.getLabelStyle()));
        contentTable.spacedRow();
        for (Item item : contractCompleted.getRewardItems()) {
            contentTable.spacedAdd(new Item(item));
        }

        contentTable.setBackground(new TextureRegionDrawable(new TextureRegion(Tradesong.getTexture(TextureAssets.POPUP_BG))));
        contentTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                removeThis();
            }
        });
        contentTable.setTouchable(Touchable.enabled);

        contentTable.setSize(512, 256); // BG dimensions
        return contentTable;
    }

    protected void removeThis() {
        this.remove();
    }
}
