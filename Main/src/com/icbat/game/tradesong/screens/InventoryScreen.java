package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.components.ItemBox;

/***/
public class InventoryScreen extends AbstractScreen {

    protected final Table layout;

    public InventoryScreen() {
        layout = new Table(Tradesong.uiStyles);

        layout.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        layout.add("Inventory");

        ItemBox inventory = ItemBox.makeInventoryBox();
        Label itemText = new Label("", Tradesong.uiStyles);
        itemText.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        layout.add(inventory).row();
        layout.add(itemText);
        setupStages(StageFactory.makeStage(layout));
    }

    @Override
    public void render(float delta) {
        drawBackground(0,0.8f,0,1);
        renderStages(delta);
        layout.debug();
        layout.debugTable();
    }

    @Override
    public String getScreenName() {
        return "inventoryScreen";
    }
}
