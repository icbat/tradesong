package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Items;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.components.ItemBox;

import java.util.ArrayList;

/***/
public class InventoryScreen extends AbstractScreen {

    protected final Table layout;

    public InventoryScreen() {
        layout = new Table(Tradesong.uiStyles);

        layout.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        layout.add("Inventory");
        ItemBox inventory = ItemBox.makeInventoryBox();
        layout.add(inventory).row();

        layout.add("Discard");
        ItemBox discardBox = ItemBox.make(new ArrayList<Items.Item>());
        layout.add(discardBox);
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
