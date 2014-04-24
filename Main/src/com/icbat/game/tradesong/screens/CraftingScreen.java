package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.components.ColorOnHoverListener;
import com.icbat.game.tradesong.gameObjects.Workshop;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;

import java.util.List;

public class CraftingScreen extends BaseInGameScreen {

    private final Stage craftingStage;
    private final DragAndDrop dragAndDrop = new DragAndDrop();

    public CraftingScreen() {
        craftingStage = new Stage();
        setupStages(craftingStage);
        forceReload();
    }

    public void forceReload() {
        craftingStage.clear();
        craftingStage.addActor(new HolderTable());
    }

    @Override
    protected void doRenderWork() {
        drawBgColor(0.2f, 0.2f, 0.8f, 1);
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }

    private class HolderTable extends Table {
        private HolderTable() {
            super(Tradesong.uiStyles);
            this.setFillParent(true);
            this.add("Worskhop List").colspan(2).row();
            StationListing currentWorkshop = new StationListing("Current Setup", new Workshop().getOrderedNodes());
            StationListing possibleStations = new StationListing("Available Stations", Tradesong.craftingStations.getNodesCopy());
            currentWorkshop.linkTo(possibleStations, true);
            possibleStations.linkTo(currentWorkshop, false);

            this.add(currentWorkshop).align(Align.left + Align.top).pad(5);
            this.add(possibleStations).align(Align.left + Align.top).pad(5);
            this.pad(20);
        }
    }

    private class StationListing extends Table {
        private StationListing(String name, List<BaseCraftingStation> stations) {
            super(Tradesong.uiStyles);
            this.add(name).row();
            for (BaseCraftingStation station : stations) {
                Actor actor = station.getActor();
                this.add(actor).align(Align.left).space(10).prefHeight(64).prefWidth(200).row();
            }
        }

        public void linkTo(StationListing otherStations, boolean shouldRemoveWhenAdding) {
            for(Actor actor : this.getChildren()) {
                actor.addListener(new ColorOnHoverListener(actor, Color.PINK));
                dragAndDrop.addSource(new StationSource(actor));
                dragAndDrop.addTarget(new StationListingTarget(otherStations));
            }
        }


        private class StationSource extends DragAndDrop.Source {
            public StationSource(Actor actor) {
                super(actor);
            }

            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Gdx.app.debug("you picked me up!", "PUT ME DOWN!");
                return null;
            }
        }

        private class StationListingTarget extends DragAndDrop.Target {
            public StationListingTarget(StationListing otherStations) {
                super(otherStations);
            }

            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return false;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {

            }

            @Override
            public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                super.reset(source, payload);
                source.getActor().setColor(Color.WHITE);
            }
        }

    }
}
