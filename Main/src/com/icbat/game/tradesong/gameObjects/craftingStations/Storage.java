package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.LinkedChestsScreen;

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
    public Actor getActor() {
        Actor actor = super.getActor();
        actor.addListener(new ChestListener(this));
        return actor;
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
