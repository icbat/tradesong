package com.tradesonggame.gameObjects.craftingStations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tradesonggame.Tradesong;
import com.tradesonggame.screens.LinkedChestsScreen;

public class Storage extends BaseCraftingStation {

    public Storage() {}
    public Storage(String name) {
        this();
        this.stationName = name;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return true;
    }

    @Override
    public String process(String processedItem) {
        this.readyForOutput.addAll(this.inputs);
        this.inputs.clear();
        return processedItem;
    }

    public void add(String itemName) {
        this.readyForOutput.add(itemName);
    }

    @Override
    public CraftingStationActor getActor() {
        return super.getActor();
    }

    @Override
    public ChestListener getClickListener() {
        return new ChestListener(this);
    }

    @Override
    public Table getProcessDisplayTable() {
        return new Table();
    }

    private class ChestListener extends ClickListener {
        private final Storage owner;

        public ChestListener(Storage owner) {
            this.owner = owner;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            Gdx.app.debug(owner.getStationName(), "clicked!");
            Tradesong.screenManager.goToScreen(new LinkedChestsScreen(owner));
        }
    }
}
